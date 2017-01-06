package com.otiasj.easyspreadsheet.main.presentation.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.otiasj.easyspreadsheet.main.domain.SpreadsheetDelegate;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;
import com.otiasj.easyspreadsheet.main.presentation.view.SpreadsheetView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * This class holds all the logic for a single spreadsheet manipulation
 *
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public class SpreadsheetPresenterImpl implements SpreadsheetPresenter {

    private static final String TAG = SpreadsheetPresenterImpl.class.getCanonicalName();

    private SpreadsheetView view;
    private SpreadsheetAdapter spreadsheetAdapter = new SpreadsheetAdapter();
    private SpreadsheetDelegate spreadsheetDelegate;

    public SpreadsheetPresenterImpl(final SpreadsheetView view, final SpreadsheetDelegate spreadsheetDelegate) {
        this.view = view;
        this.spreadsheetDelegate = spreadsheetDelegate;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return spreadsheetAdapter;
    }

    @Override
    public void addRow() {

    }

    @Override
    public void addColumn() {

    }

    @Override
    public void editText() {

    }

    @Override
    public void selectCell(final int x, final int y) {

    }

    @Override
    public void undo() {

    }

    @Override
    public void load(final String spreadsheetName) {
        final Observable<SpreadSheet> spreadSheetObservable = spreadsheetDelegate.loadSpreadsheet(spreadsheetName);
        spreadSheetObservable.subscribeOn(AndroidSchedulers.mainThread()).subscribe(spreadSheetObserver);
    }

    @Override
    public void save(final String spreadsheetName) {

    }

    @Override
    public void reload() {

    }

    private Observer<SpreadSheet> spreadSheetObserver = new Observer<SpreadSheet>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(final Throwable e) {
            Log.e(TAG, "Failed to load spreadsheet", e);
        }

        @Override
        public void onNext(final SpreadSheet spreadSheet) {
            spreadsheetAdapter.setSpreadSheet(spreadSheet);
            view.refreshSpanCount(spreadSheet.getNumberOfColumn());
        }
    };
}
