package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.services.AlbumService;

public class AlbumTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -4718798982770369958L;

    private List<DtoReturnAlbum> list;
    private String[] columnNames = { "title", "releaseDate", "albumFormat", "count Tracks", "genres" };
    
    
    public AlbumTableModel() {
	AlbumService albumService = new AlbumService();
	this.list = albumService.getSimpleList();
    }

    @Override
    public int getRowCount() {
	return list.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
	return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	DtoReturnAlbum dto = list.get(rowIndex);
	
	switch (columnIndex) {
	case 0:
	    return dto.title();
	case 1:
	    return dto.releaseDate();
	case 2:
	    return dto.albumFormat();
	case 3:
	    return dto.countTracks();
	case 4:
	    return dto.genres();
	default:
	    return null;
	}
    }

}
