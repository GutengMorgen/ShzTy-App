package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class ArtistTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 2144749428898020008L;

    //NOTE: automatizar el proceso
    private List<DtoSimpleReturnArtist> simepleReturnArtists;
    private String[] columnNames = { "Name", "Gender", "Country", "Nº Albums", "Languages", "Genres" };

    public ArtistTableModel() {
	ArtistService artistService = new ArtistService();
	this.simepleReturnArtists = artistService.getSimpleList();
    }

    @Override
    public int getRowCount() {
	return simepleReturnArtists.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
	return columnNames[columnIndex];
    }

//    TableColumn
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	DtoSimpleReturnArtist simpleReturnArtist = simepleReturnArtists.get(rowIndex);

	switch (columnIndex) {
	case 0:
	    return simpleReturnArtist.Name();
	case 1:
	    return simpleReturnArtist.Gender();
	case 2:
	    return simpleReturnArtist.Country();
	case 3:
	    return simpleReturnArtist.CountAlbums();
	case 4:
	    return simpleReturnArtist.Languages();
	case 5:
	    return simpleReturnArtist.Genres();
	default:
	    return null;
	}
    }

}
