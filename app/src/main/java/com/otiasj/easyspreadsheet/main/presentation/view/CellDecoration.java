package com.otiasj.easyspreadsheet.main.presentation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.otiasj.easyspreadsheet.R;

/**
 * Created by julien on 1/7/2017.
 * All rights reserved
 */

public class CellDecoration extends RecyclerView.ItemDecoration {


    private static final int[] ATTRS = { android.R.attr.listDivider };

    private Drawable mDivider;
    private int mInsets;

    public CellDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.cell_insets);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
        drawBorder(c, parent);
    }

    private void drawBorder(final Canvas c, final RecyclerView parent) {
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int top = parent.getTop();
        final int bottom = parent.getBottom();
        final int size = mDivider.getIntrinsicHeight();

        //Top
        mDivider.setBounds(left, top, right, top + size);
        mDivider.draw(c);

        //Left
        mDivider.setBounds(left, top, left + size, bottom);
        mDivider.draw(c);

        //Right
        mDivider.setBounds(right - size, top, right, bottom);
        mDivider.draw(c);

        //Bottom
        mDivider.setBounds(left, bottom - size, right, bottom);
        mDivider.draw(c);
    }

    /** Draw dividers at each expected grid interval */
    public void drawVertical(Canvas c, RecyclerView parent) {
        if (parent.getChildCount() == 0) return;

        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getLeft() - params.leftMargin - mInsets;
            final int right = child.getRight() + params.rightMargin + mInsets;
            final int top = child.getBottom() + params.bottomMargin + mInsets;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /** Draw dividers to the right of each child view */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();

        int left;
        int right;
        int top = 0;
        int bottom = 0;

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            left = child.getRight() + params.rightMargin + mInsets;
            right = left + mDivider.getIntrinsicWidth();
            top = child.getTop() - params.topMargin - mInsets;
            bottom = child.getBottom() + params.bottomMargin + mInsets;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}