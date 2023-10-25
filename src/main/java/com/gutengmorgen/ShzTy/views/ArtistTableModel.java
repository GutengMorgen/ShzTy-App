package com.gutengmorgen.ShzTy.views;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.ArtistService;

public class ArtistTableModel extends AbstractTableModel implements MainTableModel<ArtistSimpleReturnDTO> {
    private static final long serialVersionUID = 2144749428898020008L;

    // NOTE: automatizar el proceso
    // TODO: optimizar esto
    private List<ArtistSimpleReturnDTO> list;
    private List<String> testNames;
    private Field[] fields;
//    private String[] columnNames = { "Id", "Name", "Country", "Languages", "Genres" };

    public ArtistTableModel(boolean initModel) {
	list = new ArrayList<>();
	testNames = readProccess();
	if (initModel) {
	    ArtistService artistService = new ArtistService();
	    this.list = new ArrayList<>(artistService.simpleList());
	}
    }

    private List<String> readProccess() {
	Class<?> c = ArtistSimpleReturnDTO.class;
	Field[] fields = c.getDeclaredFields();
	this.fields = fields;
	List<String> result = new ArrayList<>();

	for (Field field : fields) {
	    field.setAccessible(true);

	    if (!field.isAnnotationPresent(ForGUI.class))
		throw new RuntimeException("Todos los parametros de la clase deben tener la anotacion ForGUI");

	    ForGUI forGUI = field.getAnnotation(ForGUI.class);
	    result.add(forGUI.name());
	}

	return result;
    }

    private Object setProccess(Object dto, int columnIndex) {
	try {
	    Field field = this.fields[columnIndex];
	    field.setAccessible(true);
	    return field.get(dto);
	} catch (IllegalArgumentException | IllegalAccessException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public int getRowCount() {
	return list.size();
    }

    @Override
    public int getColumnCount() {
//	return columnNames.length;
	return testNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
	return testNames.get(columnIndex);
//	return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	ArtistSimpleReturnDTO dto = list.get(rowIndex);

	return setProccess(dto, columnIndex);
//	switch (columnIndex) {
//	case 0:
//	    return dto.getId();
//	case 1:
//	    return dto.getName();
//	case 2:
//	    return dto.getCountry();
//	case 3:
//	    return dto.getLanguages();
//	case 4:
//	    return dto.getGenres();
//	default:
//	    return null;
//	}
    }

    @Override
    public void updateRow(int rowIndex, ArtistSimpleReturnDTO dto) {
	list.set(rowIndex, dto);
	fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public void insertRow(ArtistSimpleReturnDTO dto) {
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
	ArtistService artistService = new ArtistService();
	this.list = new ArrayList<>(artistService.simpleList());
	fireTableDataChanged();
    }

}
