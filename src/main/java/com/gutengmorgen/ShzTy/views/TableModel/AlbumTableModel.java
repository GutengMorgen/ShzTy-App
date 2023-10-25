package com.gutengmorgen.ShzTy.views.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Albums.DtoAlbums.DtoReturnAlbum;
import com.gutengmorgen.ShzTy.services.AlbumService;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;

public class AlbumTableModel extends AbstractTableModel implements MainTableModel<DtoReturnAlbum> {
    private static final long serialVersionUID = -4718798982770369958L;

    private List<DtoReturnAlbum> list;
    private String[] columnNames = { "title", "releaseDate", "albumFormat", "count Tracks", "genres" };

    public AlbumTableModel(boolean initModel) {
	list = new ArrayList<>();
	if (initModel) {
	    AlbumService albumService = new AlbumService();
	    this.list = new ArrayList<>(albumService.getSimpleList());
	}
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

    @Override
    public void updateRow(int rowIndex, DtoReturnAlbum dto) {
	list.set(rowIndex, dto);
	fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public void insertRow(DtoReturnAlbum dto) {
	list.add(dto);
	int lastRow = list.size() - 1;
	fireTableRowsInserted(lastRow, lastRow);
    }

    @Override
    public void deleteRow(int rowIndex) {
	if (rowIndex >= 0 && rowIndex < list.size()) {
	    list.remove(rowIndex);
	    fireTableRowsUpdated(rowIndex, rowIndex);
	}
    }

    @Override
    public void refreshModel() {
	AlbumService albumService = new AlbumService();
	this.list = new ArrayList<>(albumService.getSimpleList());
    }

}
