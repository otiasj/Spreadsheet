package com.otiasj.easyspreadsheet.main.presentation.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.otiasj.easyspreadsheet.main.domain.SpreadsheetDelegate;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadsheetCell;
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

    private SpreadSheet spreadsheet;
    private SpreadsheetCell selectedCell;
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
        if (spreadsheet != null) {
            spreadsheet.addRow();
            spreadsheetAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addColumn() {
        if (spreadsheet != null) {
            spreadsheet.addColumn();
            view.refreshSpanCount(spreadsheet.getNumberOfColumn());
            spreadsheetAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void editText(String text) {
        if (selectedCell != null) {
            selectedCell.setCellData(text);
        }
    }

    @Override
    public void selectCell(final int gridIndex) {
        if (spreadsheet != null) {
            this.selectedCell = spreadsheet.getCellAt(gridIndex);
        }
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
        public void onNext(final SpreadSheet spreadsheet) {
            SpreadsheetPresenterImpl.this.spreadsheet = spreadsheet;
            spreadsheetAdapter.setSpreadSheet(spreadsheet);
            view.refreshSpanCount(spreadsheet.getNumberOfColumn());
        }
    };
}
