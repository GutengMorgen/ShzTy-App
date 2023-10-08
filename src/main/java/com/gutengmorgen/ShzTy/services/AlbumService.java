package com.gutengmorgen.ShzTy.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.models.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoCreateAlbum;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoUpdateAlbum;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.AlbumFormatRepository;
import com.gutengmorgen.ShzTy.repositories.AlbumRepository;
import com.gutengmorgen.ShzTy.repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;

public class AlbumService {
    AlbumRepository albumRepository = new AlbumRepository();
    ArtistRepository artistRepository = new ArtistRepository();
    GenreRepository genreRepository = new GenreRepository();
    AlbumFormatRepository albumFormatRepository = new AlbumFormatRepository();

    public static void main(String[] args) {
	AlbumService service = new AlbumService();
	
//	service.saveArtist();
	service.updateAlbum(1L);
	for (Object object : service.getAllAlbums()) {
	    System.out.println(object.toString());
	}
    }

    public Album getAlbumById(Long id) {
	return albumRepository.findById(id);
    }

    public List<Album> getAllAlbums() {
	return albumRepository.findAll();
    }

    public List<DtoReturnAlbum> getSimpleList() {
	return albumRepository.findAll().stream().map(album -> new DtoReturnAlbum(album)).toList();
    }

    public void saveArtist() {
	DtoCreateAlbum dto = new DtoCreateAlbum("Nightmare", new Date(3434423), 1L, 1L, Set.of(2L, 3L));

	Artist a = validArtist(dto.artistId());

	validNameInArtist(dto.title(), a);
	Album alb = new Album(dto);

	AlbumFormat af = validAlbumFormat(dto.albumFormatId());

	alb.setArtist(a);
	alb.setAlbumFormat(af);
	associateGenres(dto.genresId(), alb);
	albumRepository.save(alb);
    }

    public void updateAlbum(Long id) {
	DtoUpdateAlbum dto = new DtoUpdateAlbum("Love and Thunder", null, null, null, null);

	Album al = albumRepository.findById(id);

	if (al == null)
	    throw new RuntimeException("Album with id <" + id + "> doesnt exists or something else happened");
	
	if(dto.artistId() != null) {
	    al.setArtist(validArtist(dto.artistId()));
	}
	
	if(dto.albumFormatId() != null) {
	    al.setAlbumFormat(validAlbumFormat(dto.albumFormatId()));
	}
	
	if(dto.genresId() != null) {
	    al.getGenres().clear();
	    associateGenres(dto.genresId(), al);
	}
	
	al.update(dto);
	albumRepository.update(al);
    }

    private void associateGenres(Set<Long> genreIDs, Album album) {
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null)
		throw new RuntimeException("Genre with id <" + genreID + "> not found");

	    album.getGenres().add(genre);
	}
    }

    private Artist validArtist(Long id) {
	Artist a = artistRepository.findById(id);
	if (a == null)
	    throw new RuntimeException(
		    "Artist with id <" + id + "> doesnt exists or something else happened");
	else {
	    return a;
	}
    }
    
    private AlbumFormat validAlbumFormat(Long id) {
	AlbumFormat af = albumFormatRepository.findById(id);
	if (af == null)
	    throw new RuntimeException(
		    "AlbumFormat with id <" + id + "> doesnt exists or something else happened");
	else {
	    return af;
	}
    }
    
    private void validNameInArtist(String name, Artist a) {
	if (albumRepository.existsByNameInArtist(name, a))
	    throw new RuntimeException(
		    "Album with title <" + name + "> already exists in Artist with id <" + a.getId() + ">");
    }
}
