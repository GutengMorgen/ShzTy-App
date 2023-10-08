package com.gutengmorgen.ShzTy.services;

import java.util.List;

import com.gutengmorgen.ShzTy.models.Tracks.Track;
import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoReturnTrack;
import com.gutengmorgen.ShzTy.repositories.TrackRepository;

public class TrackService {
    TrackRepository trackRepository = new TrackRepository();

    public static void main(String[] args) {
	TrackService service = new TrackService();

//	for (Object object : service.getAllTracks()) {
//	    System.out.println(object.toString());
//	}
	System.out.println(service.getTrackById(2L).toString());
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

    }

    public void updateTrack() {

    }

    public void deleteTrack() {

    }

    private Track validTrack(Long id) {
	Track t = trackRepository.findById(id);
	if (t == null) {
	    throw new RuntimeException("Track with id <" + id + "> doesnt exists or something else happened");
	} else {
	    return t;
	}
    }
}
