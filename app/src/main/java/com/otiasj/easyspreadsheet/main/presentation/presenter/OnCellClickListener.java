package com.otiasj.easyspreadsheet.main.presentation.presenter;

import com.otiasj.easyspreadsheet.main.domain.model.SpreadsheetCell;

/**
 * Created by julien on 1/7/2017.
 * All rights reserved
 */

public interface OnCellClickListener {
    void onCellClick(int gridIndex, SpreadsheetCell cell);
}
