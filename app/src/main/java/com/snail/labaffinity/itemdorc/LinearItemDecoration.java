package com.snail.labaffinity.itemdorc;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:45
 * Des:
 * version:
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation;
    private int mSpanSpace = 20;

    public LinearItemDecoration(int orientation) {
        setOrientation(orientation);
        mOrientation = LinearLayoutManager.VERTICAL;
    }

    public LinearItemDecoration(int orientation, int span) {
        this(orientation);
        mSpanSpace = span;
    }

    private void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            if (parent.getChildAdapterPosition(view) < parent.getAdapter().getItemCount() - 1) {
                outRect.set(0, 0, 0, mSpanSpace);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (parent.getChildAdapterPosition(view) < parent.getAdapter().getItemCount() - 1) {
                outRect.set(0, 0, mSpanSpace, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}