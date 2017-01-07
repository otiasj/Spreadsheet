package com.otiasj.easyspreadsheet.main.presentation.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
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
 * quick and dirty, would need to be replaced by a fragment
 * to follow the MVP pattern.
 */
public class SpreadsheetActivity extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener, SpreadsheetView {

    private SpreadsheetPresenter spreadsheetPresenter;
    private GridLayoutManager gridLayoutManager;
    private EditText cellEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customTemporaryInit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cellEditText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cellEditText.removeTextChangedListener(textWatcher);
    }

    //Todo : initialization of the model and presenter, should probably use dependency injection here.
    // use annotations for binding etc...
    private void customTemporaryInit() {
        // presenter
        spreadsheetPresenter = new SpreadsheetPresenterImpl(this, new MockSpreadSheetDelegate());
        RecyclerView spreadSheetRecyclerView = (RecyclerView) findViewById(R.id.spreadsheetRV);
        spreadSheetRecyclerView.setAdapter(spreadsheetPresenter.getAdapter());

        //grid layout
        gridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.default_spreadsheet_size));
        spreadSheetRecyclerView.setLayoutManager(gridLayoutManager);

        //button listeners
        Button addRowButton = (Button) findViewById(R.id.addRowButton);
        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                spreadsheetPresenter.addRow();
            }
        });
        Button addColButton = (Button) findViewById(R.id.addColumnButton);
        addColButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                spreadsheetPresenter.addColumn();
            }
        });
        cellEditText = (EditText) findViewById(R.id.cellEditText);

        spreadsheetPresenter.load("Empty spreadsheet");
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
            spreadsheetPresenter.editText(editable.toString());
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
