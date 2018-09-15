package com.snail.labaffinity.itemdorc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Data: 17-7-3 下午8:27
 * Des:
 * version:
 */

public class HeaderFooterLinearItemDecoration extends RecyclerView.ItemDecoration {


    private int mHeaderSpanSpace;
    private int mFooterSpanSpace;
    private Drawable mHeaderDivider;
    private Drawable mFooterDivider;

    private HeaderFooterLinearItemDecoration() {
    }

    public void setHeaderSpan(int span) {
        mHeaderSpanSpace = span;
    }

    public void setFooterSpan(int span) {
        mFooterSpanSpace = span;
    }


    public void setHeaderColor(int color) {
        mHeaderDivider = new ColorDrawable(color);
    }

    public void setFooterColor(int color) {
        mFooterDivider = new ColorDrawable(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int count = parent.getAdapter().getItemCount() - 1;
        if (isVertical(parent)) {
            //  如果顶部展示一个占位
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.set(0, mHeaderSpanSpace, 0, 0);
            }
            if (parent.getChildAdapterPosition(view) == count) {
                outRect.set(0, 0, 0, mFooterSpanSpace);
            }
        } else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.set(mHeaderSpanSpace, 0, 0, 0);
            }
            if (parent.getChildAdapterPosition(view) == count) {
                outRect.set(0, 0, mFooterSpanSpace, 0);
            }
        }
    }

    private boolean isVertical(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int orientation = ((LinearLayoutManager) layoutManager)
                    .getOrientation();
            return orientation == LinearLayoutManager.VERTICAL;
        }
        return false;
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (isVertical(parent)) {
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

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + mFooterSpanSpace;
            if (i == 0) {
                bottom = child.getTop() - params.topMargin +
                        Math.round(ViewCompat.getTranslationY(child));
                top = bottom - mHeaderSpanSpace;
                mHeaderDivider.setBounds(left, top, right, bottom);
                mHeaderDivider.draw(c);
            }

            if (i == childCount - 1) {
                mFooterDivider.setBounds(left, top, right, bottom);
                mFooterDivider.draw(c);
            }
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (i == 0) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                int right = child.getLeft() - params.leftMargin +
                        Math.round(ViewCompat.getTranslationX(child));
                int left = right - mHeaderSpanSpace;
                mHeaderDivider.setBounds(left, top, right, bottom);
                mHeaderDivider.draw(c);
            }

            if (i == childCount - 1) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                int left = child.getRight() + params.rightMargin +
                        Math.round(ViewCompat.getTranslationX(child));
                int right = left + mFooterSpanSpace;
                mFooterDivider.setBounds(left, top, right, bottom);
                mFooterDivider.draw(c);
            }
        }
    }

    public static class Builder {
        private int mHeaderSpanSpace;
        private int mFooterSpanSpace;
        private int mHeaderDividerColor;
        private int mFooterDividerColor;

        public Builder headerSpanSpace(int span) {
            mHeaderSpanSpace = span;
            return this;
        }

        public Builder footerSpanSpace(int span) {
            mFooterSpanSpace = span;
            return this;
        }

        public Builder headerDividerColor(int color) {
            mHeaderDividerColor = color;
            return this;
        }

        public Builder footerDividerColor(int color) {
            mFooterDividerColor = color;
            return this;
        }

        public HeaderFooterLinearItemDecoration build() {
            HeaderFooterLinearItemDecoration decoration = new HeaderFooterLinearItemDecoration();
            decoration.setFooterColor(mFooterDividerColor);
            decoration.setHeaderColor(mHeaderDividerColor);
            decoration.setHeaderSpan(mHeaderSpanSpace);
            decoration.setFooterSpan(mFooterSpanSpace);
            return decoration;
        }

    }
}
