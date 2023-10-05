package com.gutengmorgen.ShzTy.DAOs;

import java.util.List;

import com.gutengmorgen.ShzTy.Services.ArtistService;
import com.gutengmorgen.ShzTy.models.Artists.Artist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;

public class ArtistDAO {
    ArtistService service = new ArtistService();
    
    public static void main(String[] args) {
	ArtistDAO artistDAO = new ArtistDAO();
	artistDAO.getSimpleList();
    }
    
    public void getList(){
	List<Artist> artists = service.getAllArtist();
	
	for (Artist artist : artists) {
	    System.out.println(artist.toString());
	}
	
	service.close();
    }
    
    public void getSimpleList() {
	List<DtoSimpleReturnArtist> simpleArtist = service.getAllSimpleArtist();
	
	for (DtoSimpleReturnArtist dtoSimpleReturnArtist : simpleArtist) {
	    System.out.println(dtoSimpleReturnArtist.toString());
	}
	
	service.close();
    }
    
    public List<DtoSimpleReturnArtist> getSimpleArtistList() {
	List<DtoSimpleReturnArtist> result = service.getAllSimpleArtist();
	service.close();
	return result;
    }
}
