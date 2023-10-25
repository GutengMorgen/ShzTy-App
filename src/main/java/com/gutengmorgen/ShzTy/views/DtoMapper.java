package com.gutengmorgen.ShzTy.views;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistCreateDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistUpdateDTO;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.services.MainServices;
import com.gutengmorgen.ShzTy.services.ReturnDTO;
import com.gutengmorgen.ShzTy.services.TrackService;
import com.gutengmorgen.ShzTy.views.Components.CustomDialog;
import com.gutengmorgen.ShzTy.views.Components.CustomTable;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
import com.gutengmorgen.ShzTy.views.Extras.ModelDTO;

public class DtoMapper {

    public static void search(CustomTable<?> table, CustomDialog dialog, ModelDTO model) {
	if (table.getName().contains("Ar")) {
	    impact(new ArtistService(), table, dialog, model, new ArtistCreateDTO(), new ArtistUpdateDTO());
	} else if (table.getName().contains("Al")) {
//	    impact(new AlbumService(), table, dialog, model, new AlbumCreateDTO(), new AlbumUpdateDTO());
	} else if (table.getName().contains("Tr")) {
//	    impact(new TrackService(), table, dialog, model, idEntity, new TrackCreateDTO(), new TrackUpdateDTO());
	}

	dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    public static <R extends ReturnDTO> void impact(MainServices<R> s, CustomTable<?> t, CustomDialog d, ModelDTO md,
	    Object save, Object update) {

	if (md == ModelDTO.RETURN) {
	    d.autoFillReturn(s.getById(t.getIdEntity()));
	} else if (md == ModelDTO.CREATE) {
	    d.autoFillToInsert(save);
	    d.okAction((MainTableModel<R>) t.getCustomModel(), s);
	} else if (md == ModelDTO.UPDATE) {
	    d.autoFillToInsert(update);
	    d.okAction((MainTableModel<R>) t.getCustomModel(), s, t.getSelectedRow(), t.getIdEntity());
	}
    }
}
