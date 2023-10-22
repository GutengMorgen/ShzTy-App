package com.gutengmorgen.ShzTy.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.DtoSimpleReturnArtist;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class ArtistTableModel extends AbstractTableModel implements MainTableModel<DtoSimpleReturnArtist> {
    private static final long serialVersionUID = 2144749428898020008L;

    // NOTE: automatizar el proceso
    private List<DtoSimpleReturnArtist> list;
    private String[] columnNames = { "Id", "Name", "Country", "Languages", "Genres" };

    public ArtistTableModel() {
	list = new ArrayList<>();
	ArtistService artistService = new ArtistService();
	this.list = new ArrayList<>(artistService.getSimpleList());
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

    @Override
    public void UpdateRow(int rowIndex, DtoSimpleReturnArtist dto) {
	list.set(rowIndex, dto);
	fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public void InsertRow(DtoSimpleReturnArtist dto) {
	list.add(dto);
	int lastRow = list.size() - 1;
	fireTableRowsInserted(lastRow, lastRow);
    }

    @Override
    public void DeleteRow(int rowIndex) {
	if (rowIndex >= 0 && rowIndex < list.size()) {
	    list.remove(rowIndex);
	    fireTableRowsUpdated(rowIndex, rowIndex);
	}
    }

}
