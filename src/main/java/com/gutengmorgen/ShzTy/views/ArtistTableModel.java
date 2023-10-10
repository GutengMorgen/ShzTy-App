package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class ArtistTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 2144749428898020008L;

    // NOTE: automatizar el proceso
    private List<DtoSimpleReturnArtist> list;
    private String[] columnNames = { "Id", "Name", "Country", "Languages", "Genres" };

    public ArtistTableModel() {
	ArtistService artistService = new ArtistService();
	this.list = artistService.getSimpleList();
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

//    TableColumn

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	DtoSimpleReturnArtist dto = list.get(rowIndex);

	switch (columnIndex) {
	case 0:
	    return dto.id();
	case 1:
	    return dto.Name();
	case 2:
	    return dto.Country();
	case 3:
	    return dto.Languages();
	case 4:
	    return dto.Genres();
	default:
	    return null;
	}
    }

    public String getUniqueKeyForRow(int row) {
	return (String) getValueAt(row, 0); // Assuming the first column contains the unique keys
    }
}
