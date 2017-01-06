package com.otiasj.easyspreadsheet.main.domain.model;

import java.util.HashMap;

/**
 * Created by julien on 1/6/2017.
 * holds the data for a single spreasheet
 * All rights reserved
 */

public class SpreadSheet {

    private int numberOfColumn;
    private int numberOfRows;

    // List of rows
    private HashMap<Integer, HashMap<Integer, SpreadsheetCell>> rows;

    public SpreadSheet(final int numberOfColumn, final int numberOfRows) {
        this.numberOfColumn = numberOfColumn;
        this.numberOfRows = numberOfRows;
        rows = new HashMap<>();
    }

    public SpreadsheetCell getCellAt(final int row, final int col) {
        HashMap<Integer, SpreadsheetCell> targetRow = rows.get(row);
        if (targetRow != null) {
            return targetRow.get(col);
        }
        return null;
    }

    public void setCellAt(final int row, final int col, final SpreadsheetCell spreadsheetCell) {
        HashMap<Integer, SpreadsheetCell> targetRow = rows.get(row);
        if (targetRow == null) {
            targetRow = new HashMap<>();
            rows.put(row, targetRow);
        }
        targetRow.put(col, spreadsheetCell);
    }

    public void addRow() {
        numberOfRows++;
    }

    public void addColumn() {
        numberOfColumn++;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * the grid position is the position in the gridview like this
     * 0 1 2 3
     * 4 5 6 7
     * 8 9 10 11
     *
     * @param gridPosition the position to retrieve data from
     */
    public SpreadsheetCell getCellAt(final int gridPosition) {
        int row = gridPosition / numberOfColumn;
        int col = gridPosition % numberOfColumn;
        return getCellAt(row, col);
    }

    //Todo: add save/load to parcel
}
