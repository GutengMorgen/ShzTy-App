package com.gutengmorgen.ShzTy.views.TableModel;

import java.util.ArrayList;
import java.util.List;

import com.gutengmorgen.ShzTy.controller.MainController;
import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistViewModel;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.views.AllEntities;

public class ArtistTableModel extends TotalModel<ArtistViewModel> {
	private static final long serialVersionUID = 2144749428898020008L;

	public ArtistTableModel(boolean initModel) {
		
		super(ArtistViewModel.class);
		if (initModel)
			connection();
	}

	public void connection() {
		list = new ArrayList<>(new ArtistService().viewList());
		//setList((List<ArtistViewModel>) MainController.search(AllEntities.Artist).viewList());
	}
}