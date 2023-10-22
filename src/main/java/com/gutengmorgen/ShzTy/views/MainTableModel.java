package com.gutengmorgen.ShzTy.views;

public interface MainTableModel<T> {

    void updateRow(int rowIndex, T dto);
    void insertRow(T dto);
    void deleteRow(int rowIndex);
    void refreshModel();
}
