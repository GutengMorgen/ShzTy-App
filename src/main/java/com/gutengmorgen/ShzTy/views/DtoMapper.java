package com.gutengmorgen.ShzTy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
		dialog.autoFillReturn(se.getReturnArtistById(rowId));
	    else if (model == DTO_MODEL.UPDATE) {
		dialog.autoFillToInsert(DtoUpdateArtist.class);
		dialog.okButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
			List<Object> result = dialog.getResult();
			DtoUpdateArtist dto = new DtoUpdateArtist(
				(String) result.get(0), 
				(java.sql.Date) result.get(1), 
				(String) result.get(2),
				(String) result.get(3), 
				(String) result.get(4), 
				(Set<Long>) result.get(5), 
				(Set<Long>) result.get(6));
			System.out.println(dto);
			se.updateArtist(dto, rowId);
			dialog.dispose();
//			dialog.getResult();
		    }
		});
	    } else if (model == DTO_MODEL.CREATE) {
//		DtoCreateArtist dto = (DtoCreateArtist) dialog.autoFillToInsert(DtoCreateArtist.class);
//		DtoCreateArtist dto2 = new DtoCreateArtist("Down & dirty", null, "male", "US", "hello down and dirty",
//			Set.of(3L), Set.of(3L));
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
