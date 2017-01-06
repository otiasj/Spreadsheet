package com.otiasj.easyspreadsheet.main.domain;

import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;

import rx.Observable;

/**
 * A fake spreadsheet loader
 * Created by julien on 1/6/2017.
 * All rights reserved
 */

public class MockSpreadSheetDelegate implements SpreadsheetDelegate {
    @Override
    public Observable<SpreadSheet> loadSpreadsheet(final String spreadsheetName) {
        return Observable.just(new SpreadSheet(6, 6));
    }

    @Override
    public void saveSpreadsheet(final String spreadsheetName, final SpreadSheet spreadSheet) {

    }
}
