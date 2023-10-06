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
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;

public class ArtistDAO {
    ArtistRepository artistRepository = new ArtistRepository();
    LanguageRepository languageRepository = new LanguageRepository();
    GenreRepository genreRepository = new GenreRepository();

    public static void main(String[] args) {
	ArtistDAO artistDAO = new ArtistDAO();
//	artistDAO.ValidName("Moritz Sancraft");
//	artistDAO.getSimpleArtistList();
//	artistDAO.getId(1L);
	artistDAO.getSimpleList();
//	artistDAO.saveArtist();
    }

    public Artist getArtistById(Long id) {
	return artistRepository.findById(id);
    }

    public List<Artist> getAllArtists() {
	return artistRepository.findAll();
    }
    
    public List<DtoSimpleReturnArtist> getSimpleList() {
	List<DtoSimpleReturnArtist> result = artistRepository.findAll()
		.stream()
		.map(artist -> new DtoSimpleReturnArtist(artist))
		.toList();

	for (DtoSimpleReturnArtist dtoSimpleReturnArtist : result) {
	    System.out.println(dtoSimpleReturnArtist.toString());
	}
	
	return result;
    }

    public void saveArtist() {
	DtoCreateArtist dto = new DtoCreateArtist("Moritz Sancraft", new Date(3434423), "Male", "Spain",
		"usa una gorra", Set.of(1L, 2L), Set.of(1L, 2L));

	ValidName(dto.Name());

	Artist artist = new Artist(dto);
	artistRepository.save(artist);
    }

    private void associateLanguages(Set<Long> languageIDs, Artist artist) {
	for (Long languageID : languageIDs) {
	    Language language = languageRepository.findById(languageID);
	    if (language == null) {
		throw new RuntimeException(String.format("Language with id <%d> not found", languageID));
	    }
	    artist.addLanguage(language);
	    language.getArtists().add(artist);
	    languageRepository.save(language);
	}
    }

    private void associateGenres(Set<Long> genreIDs, Artist artist) {
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null) {
		throw new RuntimeException(String.format("Genre with id <%d> not found", genreID));
	    }
	    artist.addGenre(genre);
	    genre.getArtists().add(artist);
	    genreRepository.save(genre);
	}
    }

    private void ValidName(String name) {
	if (artistRepository.existsByName(name))
	    throw new RuntimeException(String.format("Artist with name <%s> already exists", name));
    }
}
