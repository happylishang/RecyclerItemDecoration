package com.snail.labaffinity.itemdorc;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:45
 * Des:
 * version:
 */
public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;
    private int mHorizonSpan;
    private int mVerticalSpan;
    private GridLayoutManager mGridLayoutManager;

    public GridLayoutItemDecoration(int count) {
        mSpanCount = count;
        setMargin(20, 20);
    }

    public GridLayoutItemDecoration(int count, int horizonSpan, int verticalSpan) {
        this(count);
        setMargin(horizonSpan, verticalSpan);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);
        final int totalCount = parent.getAdapter().getItemCount();
        int everyCharge = mHorizonSpan * (mSpanCount - 1) / (mSpanCount);
        int modValue = position % mSpanCount;
        final int left = Math.round(modValue * mHorizonSpan / mSpanCount);
        final int right = everyCharge - left;

        if (!isLastRaw(parent, position, mSpanCount, totalCount)) {
            outRect.set(left, 0, right, mVerticalSpan);
        } else {
            outRect.set(left, 0, right, 0);
        }
    }

    public void setMargin(int horizion, int vertical) {
        mHorizonSpan = horizion;
        mVerticalSpan = vertical;
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
}