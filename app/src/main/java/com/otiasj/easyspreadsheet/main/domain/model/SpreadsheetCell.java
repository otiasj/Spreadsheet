package com.otiasj.easyspreadsheet.main.domain.model;

/**
 * Created by julien on 1/6/2017.
 * A simple cell object to store each cell information,
 * All rights reserved
 */
public class SpreadsheetCell {

    private String cellData;

    public void setCellData(final String cellData) {
        this.cellData = cellData;
    }

    public String getCellData() {
        return cellData;
    }

    //Todo add cell manipulations
}
