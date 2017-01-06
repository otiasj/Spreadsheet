package com.otiasj.easyspreadsheet.main.domain;

import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;

import rx.Observable;

/**
 * A simple delegate to load and save a spreadsheet
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public interface SpreadsheetDelegate {

    /**
     * Load the spreadsheet with given name,
     * if it does not exist a new empty spreadsheet is created
     *
     * @param spreadsheetName
     * @return
     */
    Observable<SpreadSheet> loadSpreadsheet(final String spreadsheetName);

    /**
     * Save the given spreadsheet overwriting the existing one if any.
     * @param spreadsheetName
     * @param spreadSheet
     */
    void saveSpreadsheet(final String spreadsheetName, final SpreadSheet spreadSheet);
}
