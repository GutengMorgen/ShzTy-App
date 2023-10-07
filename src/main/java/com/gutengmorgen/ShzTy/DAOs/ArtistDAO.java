package com.gutengmorgen.ShzTy.DAOs;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.Repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.Repositories.GenreRepository;
import com.gutengmorgen.ShzTy.Repositories.LanguageRepository;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;

public class ArtistDAO {
    ArtistRepository artistRepository = new ArtistRepository();
    LanguageRepository languageRepository = new LanguageRepository();
    GenreRepository genreRepository = new GenreRepository();

    public static void main(String[] args) {
	ArtistDAO artistDAO = new ArtistDAO();
	artistDAO.getSimpleList();
    }

    public Artist getArtistById(Long id) {
	return artistRepository.findById(id);
    }

    public List<Artist> getAllArtists() {
	List<Artist> list = artistRepository.findAll();

	for (Artist artist : list) {
	    System.out.println(artist.getId());
	}

	return list;
    }

    public List<DtoSimpleReturnArtist> getSimpleList() {
	List<DtoSimpleReturnArtist> result = artistRepository.findAll().stream()
		.map(artist -> new DtoSimpleReturnArtist(artist)).toList();

	for (DtoSimpleReturnArtist dtoSimpleReturnArtist : result) {
	    System.out.println(dtoSimpleReturnArtist.toString());
	}

	return result;
    }

    public void saveArtist() {
	DtoCreateArtist dto = new DtoCreateArtist("Mabbel Pines", new Date(3434423), "Female", "Spain", "usa una gorra",
		Set.of(2L, 3L), Set.of(2L, 3L));

	validName(dto.Name());

	Artist artist = new Artist(dto);
	associateGenres(dto.GenreIDs(), artist);
	associateLanguages(dto.LanguageIDs(), artist);
	artistRepository.save(artist);
    }

    public void updateArtist(Long id) {
	DtoUpdateArtist dto = new DtoUpdateArtist(null, null, null, "USA", null, null, null);

	Artist a = artistRepository.findById(id);

	if (a == null)
	    throw new RuntimeException(
		    String.format("Artist with id <%d> doesnt exists or something else happened", id));

	if (dto.Name() != null)
	    validName(dto.Name());

	a.update(dto);

	if (dto.GenreIDs() != null) {
	    a.getGenres().clear();
	    associateGenres(dto.GenreIDs(), a);
	}

	if (dto.LanguageIDs() != null) {
	    a.getLanguages().clear();
	    associateLanguages(dto.LanguageIDs(), a);
	}

	artistRepository.update(a);
    }

    public void deleteArtist(Long id) {
	Artist a = artistRepository.findById(id);
	if (a == null)
	    throw new RuntimeException(
		    String.format("Artist with id <%d> doesnt exists or something else happened", id));
	if (a.albumsCount() != 0) {
	    throw new RuntimeException(String.format("This artist with id <%d> " + 
		    "cannot be deleted because has related albums, " +
		    "first delete all albums by this artist", id));
	}
	artistRepository.delete(a);
    }

    private void associateLanguages(Set<Long> languageIDs, Artist artist) {
	for (Long languageID : languageIDs) {
	    Language language = languageRepository.findById(languageID);
	    if (language == null)
		throw new RuntimeException(String.format("Language with id <%d> not found", languageID));

	    artist.getLanguages().add(language);
	}
    }

    private void associateGenres(Set<Long> genreIDs, Artist artist) {
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null)
		throw new RuntimeException(String.format("Genre with id <%d> not found", genreID));

	    artist.getGenres().add(genre);
	}
    }

    private void validName(String name) {
	if (artistRepository.existsByName(name))
	    throw new RuntimeException(String.format("Artist with name <%s> already exists", name));
    }
}
