package com.gutengmorgen.ShzTy.services;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoCreateTrack;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoReturnTrack;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoUpdateTrack;
import com.gutengmorgen.ShzTy.repositories.AlbumRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;
import com.gutengmorgen.ShzTy.repositories.PlayListRepository;
import com.gutengmorgen.ShzTy.repositories.TrackRepository;

public class TrackService {
    TrackRepository trackRepository = new TrackRepository();
    GenreRepository genreRepository = new GenreRepository();
    AlbumRepository albumRepository = new AlbumRepository();
    PlayListRepository playListRepository = new PlayListRepository();

    public static void main(String[] args) {
	TrackService service = new TrackService();

//	service.saveTrack();
	service.updateTrack(4L);
	for (Object object : service.getAllTracks()) {
	    System.out.println(object.toString());
	}
//	System.out.println(service.getTrackById(2L).toString());
    }

    public Track getTrackById(Long id) {
	return validTrack(id);
    }

    public List<Track> getAllTracks() {
	return trackRepository.findAll();
    }

    public List<DtoReturnTrack> getSimpleList() {
	return null;
    }

    public void saveTrack() {
	DtoCreateTrack dto = new DtoCreateTrack("Stand by me", new Date(34324), 200, "esta es una nota", Set.of(1L, 2L), 1L, 1L);
	
	Album al = validAlbum(dto.albumId());
	PlayList pl = validPlayList(dto.playListId());

	validTitleInAlbum(dto.title(), al);
	Track t = new Track(dto);
	
	t.setAlbum(al);
	t.setPlayList(pl);
	associateGenres(dto.genreIDs(), t);
	trackRepository.save(t);
    }

    public void updateTrack(Long id) {
	DtoUpdateTrack dto = new DtoUpdateTrack("Piano Man", null, 0, null, null, null, null);
	
	Track t = validTrack(id);
	
	if(dto.albumId() != null) {
	    t.setAlbum(validAlbum(dto.albumId()));
	}
	
	if(dto.playListId() != null) {
	    t.setPlayList(validPlayList(dto.playListId()));
	}
	
	if(dto.genreIDs() != null) {
	    t.getGenres().clear();
	    associateGenres(dto.genreIDs(), t);
	}
	
	if (dto.title() != null) {
	    if (dto.albumId() != null) {
		Album al = validAlbum(dto.albumId());
		t.setTitle(validTitleInAlbum(dto.title(), al));
	    } else {
		t.setTitle(validTitleInAlbum(dto.title(), t.getAlbum()));
	    }
	}
	
	t.update(dto);
	trackRepository.update(t);
    }

    public void deleteTrack(Long id) {
	Track t = validTrack(id);
	//NOTE: tambien eliminar de la tabla tracks_genres y todos los relacionados
	trackRepository.delete(t);
    }

    private void associateGenres(Set<Long> genreIDs, Track track) {
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null)
		throw new RuntimeException("Genre with id <" + genreID + "> not found");

	    track.getGenres().add(genre);
	}
    }
    
    private Track validTrack(Long id) {
	Track t = trackRepository.findById(id);
	if (t == null) {
	    throw new RuntimeException("Track with id <" + id + "> doesnt exists or something else happened");
	} else {
	    return t;
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
    
    private PlayList validPlayList(Long id) {
	PlayList pl = playListRepository.findById(id);
	if (pl == null)
	    throw new RuntimeException("PlayList with id <" + id + "> doesnt exists or something else happened");
	else {
	    return pl;
	}
    }
    
    private String validTitleInAlbum(String name, Album al) {
	if (trackRepository.existsByNameInAlbum(name, al))
	    throw new RuntimeException(
		    "Track with title <" + name + "> already exists in Album with id <" + al.getId() + ">");
	else
	    return name;
    }
}
