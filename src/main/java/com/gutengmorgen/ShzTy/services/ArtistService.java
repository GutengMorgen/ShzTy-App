package com.gutengmorgen.ShzTy.services;

import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.Exceptions.NotFoundEntityId;
import com.gutengmorgen.ShzTy.Exceptions.UnsupportedDtoTypeException;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistReturnDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistViewModel;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;
import com.gutengmorgen.ShzTy.services.extras.InsertDTO;
import com.gutengmorgen.ShzTy.services.extras.MainServices;
import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public class ArtistService implements MainServices<ArtistViewModel> {
	ArtistRepository artistRepository = new ArtistRepository();
	LanguageRepository languageRepository = new LanguageRepository();
	GenreRepository genreRepository = new GenreRepository();

	@Override
	public List<ArtistViewModel> viewList() {
		return artistRepository.findAll().stream().map(ArtistViewModel::new).toList();
	}

	@Override
	public ArtistViewModel save(InsertDTO dtoType) {
		if (dtoType instanceof ArtistCreateDTO dto) {
			validName(dto.getName());

			Artist artist = new Artist(dto);
			if (dto.getGenreIDs() != null)
				associateGenres(dto.getGenreIDs(), artist);
			if (dto.getLanguageIDs() != null)
				associateLanguages(dto.getLanguageIDs(), artist);

			artistRepository.save(artist);

			return new ArtistViewModel(artist);
		} else
			throw new UnsupportedDtoTypeException(ArtistCreateDTO.class, dtoType.getClass());
	}

	@Override
	public ArtistViewModel update(InsertDTO dtoType, Long id) {
		if (dtoType instanceof ArtistUpdateDTO dto) {
			Artist a = validArtist(id);

			if (dto.getName() != null)
				validName(dto.getName());

			if (dto.getGenreIDs() != null)
				associateGenres(dto.getGenreIDs(), a);

			if (dto.getLanguageIDs() != null)
				associateLanguages(dto.getLanguageIDs(), a);

			a.update(dto);
			artistRepository.update(a);
			return new ArtistViewModel(a);

		} else
			throw new UnsupportedDtoTypeException(ArtistUpdateDTO.class, dtoType.getClass());
	}

	@Override
	public void delete(Long id) {
		Artist a = validArtist(id);

		if (a.albumsCount() != 0) {
			throw new RuntimeException("This artist with id <" + id + "> "
					+ "cannot be deleted because has related albums, " + "first delete all albums by this artist");
		} else {
			a.getGenres().clear();
			a.getLanguages().clear();

			artistRepository.delete(a);
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

	private void associateLanguages(Set<Long> languageIDs, Artist artist) {
		artist.getLanguages().clear();
		for (Long id : languageIDs) {
			Language language = languageRepository.findById(id);
			if (language == null)
				throw new NotFoundEntityId(Language.class, id);

			artist.getLanguages().add(language);
		}
	}

	private void associateGenres(Set<Long> genreIDs, Artist artist) {
		artist.getGenres().clear();
		for (Long id : genreIDs) {
			Genre genre = genreRepository.findById(id);
			if (genre == null)
				throw new NotFoundEntityId(Genre.class, id);

			artist.getGenres().add(genre);
		}
	}

	private void validName(String name) {
		if (artistRepository.existsByName(name))
			throw new RuntimeException("Artist with name <" + name + "> already exists");
	}

	@Override
	public ReturnDTO getById(Long id) {
		return new ArtistReturnDTO(artistRepository.findById(id));
	}

	@Override
	public Long getIdByName(String name) {
		return artistRepository.findIdByName("name", name);
	}

	@Override
	public List<String> getAllName() {
		return artistRepository.findAllByName("name");
	}
}
