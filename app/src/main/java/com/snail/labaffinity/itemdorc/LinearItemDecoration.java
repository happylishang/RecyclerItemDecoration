package com.snail.labaffinity.itemdorc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.view.ViewCompat;
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

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;
    private int mSpanSpace = 30;

    public LinearItemDecoration(int orientation) {
        mDivider = new ShapeDrawable();
        setOrientation(orientation);
    }

    public LinearItemDecoration(int orientation, int span) {
        this(orientation);
        mSpanSpace = span;
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mSpanSpace;
            if (i < parent.getAdapter().getItemCount() - 1) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            } else {
                mDivider.setBounds(left, top, right, top);
                mDivider.draw(c);
            }
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mSpanSpace;
            if (i < parent.getAdapter().getItemCount() - 1) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
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