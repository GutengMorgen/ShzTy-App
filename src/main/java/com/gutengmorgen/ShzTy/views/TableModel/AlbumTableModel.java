package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumViewModel;
import com.gutengmorgen.ShzTy.services.AlbumService;

import java.util.ArrayList;

public class AlbumTableModel extends TotalModel<AlbumViewModel> {
    private static final long serialVersionUID = -4718798982770369958L;

    public AlbumTableModel(boolean initModel) {
	super(AlbumViewModel.class);
	if(initModel) {
	    connection();
	}
    }

    @Override
    public void connection() {
        list = new ArrayList<>(new AlbumService().viewList());
    }
}
