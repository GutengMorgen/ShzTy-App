package com.gutengmorgen.ShzTy.views.Extras;

import javax.swing.table.TableModel;

import com.gutengmorgen.ShzTy.services.extras.ReturnDTO;

public interface MainTableModel<T extends ReturnDTO> extends TableModel {

    void updateRow(int rowIndex, T dto);

    void insertRow(T dto);

    void deleteRow(int rowIndex);

    void refreshModel();
    
}
