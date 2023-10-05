package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.Artist;

public class ArtistTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 2144749428898020008L;

    private List<Artist> artists;
    private String[] columnNames = { "ID", "Name", "Born Date", "Gender", "Country", "Biography" };

    public ArtistTableModel(List<Artist> artists) {
	this.artists = artists;
    }

    @Override
    public int getRowCount() {
	return artists.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
	return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Artist artist = artists.get(rowIndex);

	switch (columnIndex) {
	case 0:
	    return artist.getId();
	case 1:
	    return artist.getName();
	case 2:
	    return artist.getBorn_date();
	case 3:
	    return artist.getGender();
	case 4:
	    return artist.getCountry();
	case 5:
	    return artist.getBiography();
	default:
	    return null;
	}
    }

}
