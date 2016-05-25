package com.snail.labaffinity.itemdorc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
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
    private ShapeDrawable drawable;
    private Paint mPaint;

    public GridLayoutItemDecoration(int count) {
        mSpanCount = count;
        drawable = new ShapeDrawable();
        mPaint = new Paint();
        mPaint = drawable.getPaint();
        mPaint.setColor(0x00000000);
        setMargin(20, 20);
    }

    public GridLayoutItemDecoration(int count, int horizonSpan, int verticalSpan) {
        this(count);
        setMargin(horizonSpan, verticalSpan);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawVertical(Canvas c, RecyclerView parent) {

        int totalCount = parent.getAdapter().getItemCount();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mVerticalSpan;

            final int left = child.getLeft() + params.leftMargin;
            final int right = child.getRight() + params.rightMargin;

            if (!isLastRaw(parent, i, mSpanCount, totalCount))
                if (childCount - i > mSpanCount) {
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                }
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        final int totalCount = parent.getAdapter().getItemCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            final int top = child.getTop() + params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int right = left + mHorizonSpan;
            if (!isLastColum(parent, i, mSpanCount, totalCount)) {
                drawable.setBounds(left, top, right, bottom);
                drawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);
        final int totalCount = parent.getAdapter().getItemCount();
        int everyCharge = mHorizonSpan * 4 / 5;
        int modValue = position % mSpanCount;
        //(0 4/5)|(1/5 3/5)|(2/5 2/5)|(3/5 1/5)|(4/5 0)
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

    public void setPaddingColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        // 添加浮层
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
}