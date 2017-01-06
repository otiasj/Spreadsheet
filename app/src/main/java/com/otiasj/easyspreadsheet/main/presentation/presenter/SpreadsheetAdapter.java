package com.otiasj.easyspreadsheet.main.presentation.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.main.domain.model.SpreadSheet;
import com.otiasj.easyspreadsheet.main.presentation.model.SpreadsheetCellHolder;

/**
 * Created by julien on 1/6/2017.
 * All rights reserved
 */

public class SpreadsheetAdapter extends RecyclerView.Adapter<SpreadsheetCellHolder> {

    private SpreadSheet spreadSheet;

    @Override
    public SpreadsheetCellHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cell_view, viewGroup, false);

        return new SpreadsheetCellHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SpreadsheetCellHolder holder, final int gridPosition) {
        if (spreadSheet == null) {
            return;
        } else {
            holder.bind(spreadSheet.getCellAt(gridPosition));
        }
    }

    @Override
    public int getItemCount() {
        if (spreadSheet == null) {
            return 0;
        } else {
            return spreadSheet.getNumberOfColumn() * spreadSheet.getNumberOfRows();
        }
    }

    public void setSpreadSheet(final SpreadSheet spreadSheet) {
        this.spreadSheet = spreadSheet;
        notifyDataSetChanged();
    }
}
