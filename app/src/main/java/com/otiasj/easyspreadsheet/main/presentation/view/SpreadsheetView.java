package com.otiasj.easyspreadsheet.main.presentation.view;

import android.content.Context;
import android.view.View;

/**
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public interface SpreadsheetView {

    void refreshSpanCount(int colCount);

    void setCurrentEditText(String text);

    void onCreate(Context context, View parent);

    void onStart();

    void onStop();

    void save(String spreadsheetName);

    void load(String spreadsheetName);
}
