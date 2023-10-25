package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class TestingModel extends TotalModel<ArtistSimpleReturnDTO> {
    private static final long serialVersionUID = 2428176272307987141L;

    public TestingModel(boolean initModel) {
	super(ArtistSimpleReturnDTO.class);
	if (initModel) {
	    connection();
	}
    }

    @Override
    public void connection() {
	super.list = new ArtistService().simpleList();
    }
}
