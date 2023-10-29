package com.gutengmorgen.ShzTy.services;

import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.Exceptions.UnsupportedDtoTypeException;
import com.gutengmorgen.ShzTy.models.Albums.Album;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.PlayLists.PlayList;
import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackCreateDTO;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackReturnDTO;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackViewModel;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.TrackUpdateDTO;
import com.gutengmorgen.ShzTy.repositories.AlbumRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;
import com.gutengmorgen.ShzTy.repositories.PlayListRepository;
import com.gutengmorgen.ShzTy.repositories.TrackRepository;
import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public class TrackService implements MainServices<TrackViewModel> {
	TrackRepository trackRepository = new TrackRepository();
	GenreRepository genreRepository = new GenreRepository();
	AlbumRepository albumRepository = new AlbumRepository();
	PlayListRepository playListRepository = new PlayListRepository();

	public void deleteTrack(Long id) {
		Track t = validTrack(id);
		t.getGenres().clear(); // NOTE: tambien eliminar de la tabla tracks_genres y todos los relacionados
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

	@Override
    public TrackViewModel save(InsertDTO origin) {
	if (origin instanceof TrackCreateDTO) {
	    TrackCreateDTO dto = (TrackCreateDTO) origin;

	    Album al = validAlbum(dto.getAlbumId());
	    PlayList pl = validPlayList(dto.getPlayListId());

	    validTitleInAlbum(dto.getTitle(), al);
	    Track t = new Track(dto);

	    t.setAlbum(al);
	    t.setPlayList(pl);
	    associateGenres(dto.getGenreIDs(), t);
	    trackRepository.save(t);

	    return new TrackViewModel(t);
	} else
	    throw new UnsupportedDtoTypeException(TrackCreateDTO.class, origin.getClass());
    }

	@Override
	public TrackViewModel update(InsertDTO origin, Long id) {
		if (origin instanceof TrackUpdateDTO) {
			TrackUpdateDTO dto = (TrackUpdateDTO) origin;
			Track t = validTrack(id);

			if (dto.getAlbumId() != null) {
				t.setAlbum(validAlbum(dto.getAlbumId()));
			}

			if (dto.getPlayListId() != null) {
				t.setPlayList(validPlayList(dto.getPlayListId()));
			}

			if (dto.getGenreIDs() != null) {
				t.getGenres().clear();
				associateGenres(dto.getGenreIDs(), t);
			}

			if (dto.getTitle() != null) {
				if (dto.getAlbumId() != null) {
					Album al = validAlbum(dto.getAlbumId());
					t.setTitle(validTitleInAlbum(dto.getTitle(), al));
				} else {
					t.setTitle(validTitleInAlbum(dto.getTitle(), t.getAlbum()));
				}
			}

			t.update(dto);
			trackRepository.update(t);

			return new TrackViewModel(t);
		} else
			throw new UnsupportedDtoTypeException(TrackUpdateDTO.class, origin.getClass());
	}

	@Override
	public List<TrackViewModel> viewList() {
		return trackRepository.findAll().stream().map(t -> new TrackViewModel(t)).toList();
	}

	@Override
	public ReturnDTO getById(Long id) {
		return new TrackReturnDTO(trackRepository.findById(id));
	}

	@Override
	public Long getIdByName(String name) {
		return trackRepository.findIdByName("title", name);
	}

	@Override
	public List<String> getAllName() {
		return trackRepository.findAllByName("title");
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
