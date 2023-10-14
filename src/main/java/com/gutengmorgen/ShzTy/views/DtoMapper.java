package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {

    public static void map(String name, Long rowId, CustomDialog dialog) {
	
	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    dialog.autoFillReturn(se.getReturnArtistById(rowId));
	} else if (name.contains("Al")) {
	    AlbumService se = new AlbumService();
	    se.getAlbumById(rowId);
	} else if (name.contains("Tr")) {
	    TrackService se = new TrackService();
	    se.getTrackById(rowId);
	}
    }
}
