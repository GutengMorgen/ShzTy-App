package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {

    public static void mapReturn(String name, Long rowId, CustomDialog dialog, DTO_MODEL model) {

	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    if (model == DTO_MODEL.RETURN)
		dialog.autoFillV2(se.getReturnArtistById(rowId));
	    else if (model == DTO_MODEL.UPDATE) {
		DtoUpdateArtist dto = (DtoUpdateArtist) dialog.autoFillMineV2(DtoUpdateArtist.class);
//		se.updateArtist(dto, rowId);
	    }
	} else if (name.contains("Al")) {
	    AlbumService se = new AlbumService();
	    se.getAlbumById(rowId);
	} else if (name.contains("Tr")) {
	    TrackService se = new TrackService();
	    se.getTrackById(rowId);
	}
    }
}
