package com.snail.labaffinity.itemdorc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:45
 * Des:
 * version:
 */
public class HeaderFooterGridLayoutItemDecoration extends RecyclerView.ItemDecoration {


    private int mHeaderSpanSpace;
    private int mFooterSpanSpace;
    private Drawable mHeaderDivider;
    private Drawable mFooterDivider;
    private int mSpanCount;

    private HeaderFooterGridLayoutItemDecoration() {
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
        if (parent == null || parent.getAdapter() == null) {
            return;
        }

        int totalCount = parent.getAdapter().getItemCount();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int position = parent.getChildAdapterPosition(child);
            if (isLastRaw(parent, position, mSpanCount, totalCount) && position % mSpanCount == 0) {
                final int top = child.getBottom() + params.bottomMargin +
                        Math.round(ViewCompat.getTranslationY(child));
                final int bottom = top + mFooterSpanSpace;
                mFooterDivider.setBounds(0, top, parent.getMeasuredWidth(), bottom);
                mFooterDivider.draw(c);
            }
            if (i == 0) {
                final int bottom = child.getTop() - params.topMargin +
                        Math.round(ViewCompat.getTranslationY(child));
                final int top = bottom - mHeaderSpanSpace;
                mHeaderDivider.setBounds(0, top, parent.getMeasuredWidth(), bottom);
                mHeaderDivider.draw(c);
            }
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        if (parent == null || parent.getAdapter() == null) {
            return;
        }

        final int childCount = parent.getChildCount();
        final int totalCount = parent.getAdapter().getItemCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            final int top = child.getTop() + params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int position = parent.getChildAdapterPosition(child);
            if (isLastColum(parent, position, mSpanCount, totalCount)) {
                final int left = child.getRight() + params.rightMargin +
                        Math.round(ViewCompat.getTranslationX(child));
                final int right = left + mFooterSpanSpace;
                mFooterDivider.setBounds(left, top, right, bottom);
                mFooterDivider.draw(c);
            }
            if (i < mSpanCount) {
                final int right = child.getLeft() - params.leftMargin + Math.round(ViewCompat.getTranslationX(child));
                final int left = right - mHeaderSpanSpace;
                mHeaderDivider.setBounds(left, top, right, bottom);
                mHeaderDivider.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);
        final int totalCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, position, mSpanCount, totalCount)) {
            outRect.set(0, 0, 0, mFooterSpanSpace);
        }
        if (position < mSpanCount) {
            outRect.set(0, mHeaderSpanSpace, 0, 0);
        }
    }


    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos - pos % spanCount) + spanCount >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos - pos % spanCount) + spanCount >= childCount)
                    return true;
            } else {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static class Builder {
        private int mHeaderSpanSpace;
        private int mFooterSpanSpace;
        private int mHeaderDividerColor;
        private int mFooterDividerColor;
        private int mSpanCount;

        public HeaderFooterGridLayoutItemDecoration.Builder headerSpanSpace(int span) {
            mHeaderSpanSpace = span;
            return this;
        }

        public HeaderFooterGridLayoutItemDecoration.Builder footerSpanSpace(int span) {
            mFooterSpanSpace = span;
            return this;
        }

        public HeaderFooterGridLayoutItemDecoration.Builder headerDividerColor(int color) {
            mHeaderDividerColor = color;
            return this;
        }

        public HeaderFooterGridLayoutItemDecoration.Builder footerDividerColor(int color) {
            mFooterDividerColor = color;
            return this;
        }

        public HeaderFooterGridLayoutItemDecoration.Builder spanCount(int count) {
            mSpanCount = count;
            return this;
        }


        public HeaderFooterGridLayoutItemDecoration build() {
            HeaderFooterGridLayoutItemDecoration decoration = new HeaderFooterGridLayoutItemDecoration();
            decoration.mHeaderSpanSpace = mHeaderSpanSpace;
            decoration.mFooterSpanSpace = mFooterSpanSpace;
            decoration.mHeaderDivider = new ColorDrawable(mHeaderDividerColor);
            decoration.mFooterDivider = new ColorDrawable(mFooterDividerColor);
            decoration.mSpanCount = mSpanCount;
            return decoration;
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

}