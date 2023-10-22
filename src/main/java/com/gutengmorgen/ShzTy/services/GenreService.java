package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.Genres.Genre;
import com.gutengmorgen.ShzTy.repositories.GenreRepository;

public class GenreService {
    private final GenreRepository genreRepository = new GenreRepository();
    
    public static void main(String[] args) {
	GenreService service = new GenreService();
	
	for (Object object : service.getAllGenres()) {
	    System.out.println(object.toString());
	}
    }
    
    public List<Genre> getAllGenres(){
	return genreRepository.findAll();
    }
    
    public void saveGenre(String name) {
	Genre g = new Genre();
	
	g.setName(validName(name));
	genreRepository.save(null);
    }
    
    public void updateGenre(Long id, String name) {
	Genre g = validGenre(id);
	
	g.setName(name);
	genreRepository.update(g);
    }
    
    public void deleteGenre(Long id) {
	Genre g = validGenre(id);
	
	g.getAlbums().clear();
	g.getArtists().clear();
	g.getTracks().clear();
	genreRepository.delete(g);
    }
    
    private Genre validGenre(Long id) {
	Genre g = genreRepository.findById(id);
	if(g == null)
	    throw new RuntimeException("Genre with id <" + id + "> doesnt exists or something else happened");
	else
	    return g;
    }
    
    private String validName(String name) {
	if(genreRepository.existsByName(name)) {
	    throw new RuntimeException("Genre with name <" + name + "> already exists");
	}
	else {
	    return name;
	}
    }

    public Long getIdByName(String t) {
	return genreRepository.findIdByName(t);
    }
}
