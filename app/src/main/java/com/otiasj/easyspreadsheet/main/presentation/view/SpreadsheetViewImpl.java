package com.otiasj.easyspreadsheet.main.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.otiasj.easyspreadsheet.MainActivity;
import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.main.domain.MockSpreadSheetDelegate;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;
import com.otiasj.easyspreadsheet.main.presentation.presenter.SpreadsheetPresenter;
import com.otiasj.easyspreadsheet.main.presentation.presenter.SpreadsheetPresenterImpl;

/**
 * The main view logic, a wrapper around the main activity
 */
public class SpreadsheetViewImpl implements SpreadsheetView {

    private SpreadsheetPresenter spreadsheetPresenter;
    private GridLayoutManager gridLayoutManager;
    private EditText cellEditText;


    @Override
    public void onCreate(final Context context, final View parent) {
        // presenter
        spreadsheetPresenter = new SpreadsheetPresenterImpl(this, new MockSpreadSheetDelegate());
        RecyclerView spreadSheetRecyclerView = (RecyclerView) parent.findViewById(R.id.spreadsheetRV);
        spreadSheetRecyclerView.setAdapter(spreadsheetPresenter.getAdapter());

        //grid layout
        gridLayoutManager = new GridLayoutManager(context, context.getResources().getInteger(R.integer.default_spreadsheet_size));
        spreadSheetRecyclerView.addItemDecoration(new CellDecoration(context));

        spreadSheetRecyclerView.setLayoutManager(gridLayoutManager);

        //button listeners
        Button addRowButton = (Button) parent.findViewById(R.id.addRowButton);
        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                spreadsheetPresenter.addRow();
            }
        });
        Button addColButton = (Button) parent.findViewById(R.id.addColumnButton);
        addColButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                spreadsheetPresenter.addColumn();
            }
        });
        cellEditText = (EditText) parent.findViewById(R.id.cellEditText);

        spreadsheetPresenter.load("Empty spreadsheet");
    }

    public void onStart() {
        cellEditText.addTextChangedListener(textWatcher);
    }

    public void onStop() {
        cellEditText.removeTextChangedListener(textWatcher);
    }

    @Override
    public void save(final String spreadsheetName) {
        spreadsheetPresenter.save(spreadsheetName);
    }

    @Override
    public void load(final String spreadsheetName) {
        spreadsheetPresenter.load(spreadsheetName);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

        }

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

        }

        @Override
        public void afterTextChanged(final Editable editable) {
            spreadsheetPresenter.editSelectedCellText(editable.toString());
        }
    };

    @Override
    public void refreshSpanCount(int colCount) {
        gridLayoutManager.setSpanCount(colCount);
    }

    @Override
    public void setCurrentEditText(final String text) {
        cellEditText.setText(text);
    }

}
