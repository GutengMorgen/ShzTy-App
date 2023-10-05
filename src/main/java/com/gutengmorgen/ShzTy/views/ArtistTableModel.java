package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;

public class ArtistTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 2144749428898020008L;

    //NOTE: automatizar el proceso
    private List<DtoSimpleReturnArtist> simepleReturnArtists;
    private String[] columnNames = { "Name", "Gender", "Country", "Count Tracks", "Count Albums", "Languages", "Genres" };

    public ArtistTableModel(List<DtoSimpleReturnArtist> simpleReturnArtists) {
	this.simepleReturnArtists = simpleReturnArtists;
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
	    return simpleReturnArtist.CountTracks();
	case 4:
	    return simpleReturnArtist.CountAlbums();
	case 5:
	    return simpleReturnArtist.Languages().toString();
	case 6:
	    return simpleReturnArtist.Genres();
	default:
	    return null;
	}
    }

}
