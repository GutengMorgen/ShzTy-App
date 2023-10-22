package com.gutengmorgen.ShzTy.views;

public interface MainTableModel<T> {

    void UpdateRow(int rowIndex, T dto);
    void InsertRow(T dto);
    void DeleteRow(int rowIndex);
}
