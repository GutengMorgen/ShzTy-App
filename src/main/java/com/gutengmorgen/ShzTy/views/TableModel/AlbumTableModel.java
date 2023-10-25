package com.gutengmorgen.ShzTy.views.TableModel;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.AlbumSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.AlbumService;

public class AlbumTableModel extends TotalModel<AlbumSimpleReturnDTO> {
    private static final long serialVersionUID = -4718798982770369958L;

    public AlbumTableModel(boolean initModel) {
	super(AlbumSimpleReturnDTO.class);
	if(initModel) {
	    connection();
	}
    }

    @Override
    public void connection() {
	super.list = new AlbumService().simpleList();
    }
}
