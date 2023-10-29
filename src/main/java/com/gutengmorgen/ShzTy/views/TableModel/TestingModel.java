package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistViewModel;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class TestingModel extends TotalModel<ArtistViewModel> {
    private static final long serialVersionUID = 2428176272307987141L;

    public TestingModel(boolean initModel) {
	super(ArtistViewModel.class);
	if (initModel) {
	    connection();
	}
    }

    @Override
    public void connection() {
	//setList(new ArtistService().viewList());
    }
}
