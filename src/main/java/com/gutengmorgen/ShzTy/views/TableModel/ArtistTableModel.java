package com.gutengmorgen.ShzTy.views.TableModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.models.Artists.DtoArtists.ArtistSimpleReturnDTO;
import com.gutengmorgen.ShzTy.services.ArtistService;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;

public class ArtistTableModel extends AbstractTableModel implements MainTableModel<ArtistSimpleReturnDTO> {
    private static final long serialVersionUID = 2144749428898020008L;

    private List<ArtistSimpleReturnDTO> list = new ArrayList<>();
    private List<String> columnNames = readProccess();
    private Field[] fields;

    public ArtistTableModel(boolean initModel) {
	if (initModel)
	    connection();
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
	return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
	return columnNames.get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	ArtistSimpleReturnDTO dto = list.get(rowIndex);

	return setProccess(dto, columnIndex);
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
	connection();
	fireTableDataChanged();
    }

    public void connection() {
	ArtistService s = new ArtistService();
	this.list = new ArrayList<>(s.simpleList());
    }
}
