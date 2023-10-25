package com.gutengmorgen.ShzTy.services;

import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.Exceptions.UnsupportedDtoTypeException;
import com.gutengmorgen.ShzTy.models.AlbumFormats.AlbumFormat;
import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumCreateDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumSimpleReturnDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumReturnDTO;
import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumUpdateDTO;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.AlbumFormatRepository;
import com.gutengmorgen.ShzTy.repositories.AlbumRepository;
import com.gutengmorgen.ShzTy.repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;

public class AlbumService implements MainServices<AlbumSimpleReturnDTO> {
    AlbumRepository albumRepository = new AlbumRepository();
    ArtistRepository artistRepository = new ArtistRepository();
    GenreRepository genreRepository = new GenreRepository();
    AlbumFormatRepository albumFormatRepository = new AlbumFormatRepository();

    public static void main(String[] args) {
	AlbumService service = new AlbumService();
	for (Object object : service.getAllAlbums()) {
	    System.out.println(object.toString());
	}
    }

    public List<Album> getAllAlbums() {
	return albumRepository.findAll();
    }

    public void deleteAlbum(Long id) {
	Album al = validAlbum(id);

	if (al.tracksCount() != 0) {
	    throw new RuntimeException("This album with id <" + id + "> "
		    + "cannot be deleted because has related tracks, " + "first delete all tracks by this album");
	} else {
	    al.getGenres().clear();
	    albumRepository.delete(al);
	}
    }

    private void associateGenres(Set<Long> genreIDs, Album album) {
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null)
		throw new RuntimeException("Genre with id <" + genreID + "> not found");

	    album.getGenres().add(genre);
	}
    }

    private Album validAlbum(Long id) {
	Album al = albumRepository.findById(id);
	if (al == null)
	    throw new RuntimeException("Album with id <" + id + "> doesnt exists or something else happened");
	else {
	    return al;
	}
    }

    private Artist validArtist(Long id) {
	Artist a = artistRepository.findById(id);
	if (a == null)
	    throw new RuntimeException("Artist with id <" + id + "> doesnt exists or something else happened");
	else {
	    return a;
	}
    }

    private AlbumFormat validAlbumFormat(Long id) {
	AlbumFormat af = albumFormatRepository.findById(id);
	if (af == null)
	    throw new RuntimeException("AlbumFormat with id <" + id + "> doesnt exists or something else happened");
	else {
	    return af;
	}
    }

    private String validNameInArtist(String name, Artist a) {
	if (albumRepository.existsByNameInArtist(name, a))
	    throw new RuntimeException(
		    "Album with title <" + name + "> already exists in Artist with id <" + a.getId() + ">");
	else
	    return name;
    }

    @Override
    public AlbumSimpleReturnDTO save(InsertDTO origin) {
	if (origin instanceof AlbumCreateDTO) {
	    AlbumCreateDTO dto = (AlbumCreateDTO) origin;

	    Artist a = validArtist(dto.getArtistId());
	    AlbumFormat af = validAlbumFormat(dto.getAlbumFormatId());

	    validNameInArtist(dto.getTitle(), a);
	    Album alb = new Album(dto);

	    alb.setArtist(a);
	    alb.setAlbumFormat(af);
	    associateGenres(dto.getGenreIDs(), alb);
	    albumRepository.save(alb);

	    return new AlbumSimpleReturnDTO(alb);
	} else
	    throw new UnsupportedDtoTypeException(AlbumCreateDTO.class, origin.getClass());
    }

    @Override
    public AlbumSimpleReturnDTO update(InsertDTO origin, Long id) {
	if (origin instanceof AlbumUpdateDTO) {
	    AlbumUpdateDTO dto = (AlbumUpdateDTO) origin;
	    Album al = validAlbum(id);

	    if (dto.getArtistId() != null) {
		al.setArtist(validArtist(dto.getArtistId()));
	    }

	    if (dto.getAlbumFormatId() != null) {
		al.setAlbumFormat(validAlbumFormat(dto.getAlbumFormatId()));
	    }

	    if (dto.getGenresId() != null) {
		al.getGenres().clear();
		associateGenres(dto.getGenresId(), al);
	    }

	    if (dto.getTitle() != null) {
		if (dto.getArtistId() != null) {
		    Artist artist = validArtist(dto.getArtistId());
		    al.setTitle(validNameInArtist(dto.getTitle(), artist));
		} else {
		    al.setTitle(validNameInArtist(dto.getTitle(), al.getArtist()));
		}
	    }

	    if (dto.getReleaseDate() != null) {
		al.setRelease_date(dto.getReleaseDate());
	    }

	    albumRepository.update(al);

	    return new AlbumSimpleReturnDTO(al);
	} else
	    throw new UnsupportedDtoTypeException(AlbumUpdateDTO.class, origin.getClass());
    }

    @Override
    public List<AlbumSimpleReturnDTO> simpleList() {
	return albumRepository.findAll().stream().map(a -> new AlbumSimpleReturnDTO(a)).toList();
    }

    @Override
    public ReturnDTO getById(Long id) {
	return new AlbumReturnDTO(albumRepository.findById(id));
    }
}
