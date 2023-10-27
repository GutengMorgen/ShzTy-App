package com.gutengmorgen.ShzTy.views.TableModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;
import com.gutengmorgen.ShzTy.views.Extras.ForGUI;
import com.gutengmorgen.ShzTy.views.Extras.MainTableModel;
import lombok.Getter;
import lombok.Setter;

public class TotalModel<T extends ReturnDTO> extends AbstractTableModel implements MainTableModel<T> {
	private static final long serialVersionUID = -4419491484057441990L;

	public List<T> list = new ArrayList<>();
	private final List<String> columnName;
	private Field[] fields;

	public TotalModel(Class<T> cl) {
		columnName = readProccess(cl);
	}

	private List<String> readProccess(Class<T> cl) {
		this.fields = cl.getDeclaredFields();
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

	private Object setProccess(T dto, int columnIndex) {
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
		return columnName.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnName.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		T dto = list.get(rowIndex);
		return setProccess(dto, columnIndex);
	}

	@Override
	public void updateRow(int rowIndex, T dto) {
		//System.out.println(getList().toString());
		try{
			list.set(rowIndex, dto);
			fireTableRowsUpdated(rowIndex, rowIndex);
		} catch (Exception e){
			System.out.println("ERRORR:::::::::::");
			e.printStackTrace();
		}
	}

	@Override
	public void insertRow(T dto) {
		list.add(dto);
		int lastRow = list.size() - 1;
		fireTableRowsInserted(lastRow, lastRow);
	}

	@Override
	public void deleteRow(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < list.size()) {
			list.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}
	}

	@Override
	public void refreshModel() {
		connection();
		fireTableDataChanged();
	}

	public void connection() {
		System.out.println("implement Connection");
	}

}
