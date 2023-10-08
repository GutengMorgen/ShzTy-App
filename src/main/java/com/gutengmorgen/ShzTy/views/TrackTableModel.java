package com.gutengmorgen.ShzTy.views;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Tracks.DtoTracks.DtoReturnTrack;
import com.gutengmorgen.ShzTy.services.TrackService;

public class TrackTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -264696072105143647L;

    private List<DtoReturnTrack> list;
    private String[] columnNames = { "title", "releaseDate", "playTime", "notes", "genreIDs", "albumId", "playListId" };
    
    public TrackTableModel() {
	TrackService trackService = new TrackService();
	this.list = trackService.getSimpleList();
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
	DtoReturnTrack dto = list.get(rowIndex);
	
	switch (columnIndex) {
	case 0:
	    return dto.title();
	case 1:
	    return dto.releaseDate();
	case 2:
	    return dto.playTime();
	case 3:
	    return dto.notes();
	case 4:
	    return dto.genreIDs();
	case 5:
	    return dto.albumId();
	case 6:
	    return dto.playListId();
	default:
	    return null;
	}
    }

}
