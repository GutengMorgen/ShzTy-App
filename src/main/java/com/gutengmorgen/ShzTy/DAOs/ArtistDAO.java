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
//	artistDAO.getSimpleArtistList();
//	artistDAO.getId(1L);
	artistDAO.saveArtist();
	artistDAO.getSimpleList();
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
	DtoCreateArtist dto = new DtoCreateArtist("Mabbel Pines", new Date(3434423), "Female", "Spain",
		"usa una gorra", Set.of(2L, 3L), Set.of(2L, 3L));

	ValidName(dto.Name());

	Artist artist = new Artist(dto);
	associateGenres(dto.GenreIDs(), artist);
	associateLanguages(dto.LanguageIDs(), artist);
	artistRepository.save(artist);
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

    private void ValidName(String name) {
	if (artistRepository.existsByName(name))
	    throw new RuntimeException(String.format("Artist with name <%s> already exists", name));
    }
}
