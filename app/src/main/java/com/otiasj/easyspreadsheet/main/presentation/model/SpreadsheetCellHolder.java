package com.otiasj.easyspreadsheet.main.presentation.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadsheetCell;
import com.otiasj.easyspreadsheet.main.presentation.presenter.OnCellClickListener;

/**
 * Created by julien on 1/6/2017.
 * All rights reserved
 */
public class SpreadsheetCellHolder extends RecyclerView.ViewHolder  {

    private final OnCellClickListener onClickListener;
    private TextView cellTextView;
    private int cellPosition;
    private SpreadsheetCell cell;

    public SpreadsheetCellHolder(final View itemView, final OnCellClickListener listener) {
        super(itemView);
        cellTextView = (TextView) itemView.findViewById(R.id.cellTextView);
        onClickListener = listener;
    }

    public void bind(final int gridPosition, final boolean isSelected, final SpreadsheetCell cellAt) {
        cellPosition = gridPosition;
        cell = cellAt;
        cellTextView.setOnClickListener(viewClickListener);
        if (cellAt != null) {
            String data = cellAt.getCellData();
            cellTextView.setText(data);
        }else {
            cellTextView.setText("");
        }
        if (isSelected) {
            cellTextView.setBackgroundResource(R.drawable.cell_selector);
        } else {
            cellTextView.setBackground(null);
        }
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            onClickListener.onCellClick(cellPosition, cell);
        }
    };
}
