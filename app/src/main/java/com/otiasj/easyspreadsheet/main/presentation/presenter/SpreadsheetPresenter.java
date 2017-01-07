package com.otiasj.easyspreadsheet.main.presentation.presenter;

import android.support.v7.widget.RecyclerView;

/**
 * Handles the logic for the main spreadsheet
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public interface SpreadsheetPresenter {
    RecyclerView.Adapter getAdapter();
    void addRow();
    void addColumn();
    void editText(String text);
    void selectCell(int gridIndex);
    void undo();
    void load(String spreadsheetName);
    void save(String spreadsheetName);
    void reload();
}
