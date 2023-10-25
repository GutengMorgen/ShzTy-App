package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class ArtistTableModel extends TotalModel<ArtistSimpleReturnDTO> {
    private static final long serialVersionUID = 2144749428898020008L;

    public ArtistTableModel(boolean initModel) {
	super(ArtistSimpleReturnDTO.class);
	if (initModel)
	    connection();
    }

    public void connection() {
	super.list = new ArtistService().simpleList();
    }
}
