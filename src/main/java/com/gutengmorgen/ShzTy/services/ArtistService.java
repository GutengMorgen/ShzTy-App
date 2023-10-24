package com.gutengmorgen.ShzTy.services;

import java.util.List;
import java.util.Set;

import com.gutengmorgen.ShzTy.Exceptions.UnsupportedDtoTypeException;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistReturnDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.models.Languages.Language;
import com.gutengmorgen.ShzTy.repositories.ArtistRepository;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;
import com.gutengmorgen.ShzTy.repositories.LanguageRepository;

public class ArtistService implements MainServices<ArtistSimpleReturnDTO> {
    ArtistRepository artistRepository = new ArtistRepository();
    LanguageRepository languageRepository = new LanguageRepository();
    GenreRepository genreRepository = new GenreRepository();

    public static void main(String[] args) {
	ArtistService artistService = new ArtistService();
	artistService.simpleList();
//	Artist a = artistService.getArtistById(1L);
//	System.out.println(a.toString());
    }

    public Artist getArtistById(Long id) {
	return artistRepository.findById(id);
    }

    public List<Artist> getAllArtists() {
	return artistRepository.findAll();
    }

    @Override
    public List<ArtistSimpleReturnDTO> simpleList() {
	return artistRepository.findAll().stream().map(artist -> new ArtistSimpleReturnDTO(artist)).toList();
    }

    @Override
    public ArtistSimpleReturnDTO save(InsertDTO dtoType) {
	if (dtoType instanceof ArtistCreateDTO) {
	    ArtistCreateDTO dto = (ArtistCreateDTO) dtoType;
	    validName(dto.getName());

	    Artist artist = new Artist(dto);
	    if (dto.getGenreIDs() != null)
		associateGenres(dto.getGenreIDs(), artist);
	    if (dto.getLanguageIDs() != null)
		associateLanguages(dto.getLanguageIDs(), artist);

	    artistRepository.save(artist);

	    return new ArtistSimpleReturnDTO(artist);
	} else
	    throw new UnsupportedDtoTypeException(ArtistCreateDTO.class, dtoType.getClass());
    }

    @Override
    public ArtistSimpleReturnDTO update(InsertDTO dtoType, Long id) {
	if (dtoType instanceof ArtistUpdateDTO) {
	    ArtistUpdateDTO dto = (ArtistUpdateDTO) dtoType;
	    Artist a = validArtist(id);

	    if (dto.getName() != null)
		validName(dto.getName());

	    if (dto.getGenreIDs() != null)
		associateGenres(dto.getGenreIDs(), a);

	    if (dto.getLanguageIDs() != null)
		associateLanguages(dto.getLanguageIDs(), a);

	    a.update(dto);
	    artistRepository.update(a);
	    return new ArtistSimpleReturnDTO(a);

	} else
	    throw new UnsupportedDtoTypeException(ArtistUpdateDTO.class, dtoType.getClass());
    }

    public void deleteArtist(Long id) {
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
	artist.getLanguages().clear(); //NOTE: podria causar una excepcion porque este metodo se usa para save y update
	for (Long languageID : languageIDs) {
	    Language language = languageRepository.findById(languageID);
	    if (language == null)
		throw new RuntimeException("Language with id <" + languageID + "> not found");

	    artist.getLanguages().add(language);
	}
    }

    private void associateGenres(Set<Long> genreIDs, Artist artist) {
	artist.getGenres().clear();
	for (Long genreID : genreIDs) {
	    Genre genre = genreRepository.findById(genreID);
	    if (genre == null)
		throw new RuntimeException("Genre with id <" + genreID + "> not found");

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
}
