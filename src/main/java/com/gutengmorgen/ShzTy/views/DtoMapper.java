package com.gutengmorgen.ShzTy.views;

import java.util.Set;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoCreateArtist;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoUpdateArtist;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.TrackService;

public class DtoMapper {

    public static void mapper(String name, Long rowId, CustomDialog dialog, DTO_MODEL model) {

	if (name.contains("Ar")) {
	    ArtistService se = new ArtistService();
	    if (model == DTO_MODEL.RETURN)
		dialog.autoFillV2(se.getReturnArtistById(rowId));
	    else if (model == DTO_MODEL.UPDATE) {
		DtoUpdateArtist dto = (DtoUpdateArtist) dialog.autoFillToInsert(DtoUpdateArtist.class);
//		se.updateArtist(dto, rowId);
	    }
	    else if(model == DTO_MODEL.CREATE) {
		DtoCreateArtist dto = (DtoCreateArtist) dialog.autoFillToInsert(DtoCreateArtist.class);
		DtoCreateArtist dto2 = new DtoCreateArtist("Down & dirty", null, "male", "US", "hello down and dirty", Set.of(3L), Set.of(3L));
//		se.saveArtist(dto);
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
