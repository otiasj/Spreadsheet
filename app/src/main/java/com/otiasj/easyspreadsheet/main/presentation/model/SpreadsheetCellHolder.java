package com.otiasj.easyspreadsheet.main.presentation.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadsheetCell;

/**
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public class SpreadsheetCellHolder extends RecyclerView.ViewHolder  {

    private TextView cellTextView;

    public SpreadsheetCellHolder(final View itemView) {
        super(itemView);
        cellTextView = (TextView) itemView.findViewById(R.id.cellTextView);
    }

    public void bind(final SpreadsheetCell cellAt) {
        if (cellAt != null) {
            String data = cellAt.getCellData();
            cellTextView.setText(data);
        }else {
            cellTextView.setText("EMPTY");
        }
    }
}
