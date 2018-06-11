package com.snail.labaffinity.itemdorc;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Data: 17-7-3 下午8:27
 * Des:
 * version:
 */

public class LinearItemDecoration extends RecyclerView.ItemDecoration {


    private int mSpanSpace = 20;

    public void setSpanSpace(int span) {
        mSpanSpace = span;
    }

    public LinearItemDecoration() {
        mDivider = new ShapeDrawable();
    }

    public LinearItemDecoration(int span) {
        mSpanSpace = span;
        mDivider = new ShapeDrawable();
    }

    public void setColor(int color) {
        mDivider = new ColorDrawable(color);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int count = parent.getAdapter().getItemCount() - 1;
        count += mShowLastLine ? 1 : 0;
        count += mShowFirstLine ? 1 : 0;

        if (isVertical(parent)) {
            //  如果顶部展示一个占位
            if (mShowFirstLine) {
                if (parent.getChildAdapterPosition(view) < count) {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(0, mSpanSpace, 0, mSpanSpace);
                    } else {
                        outRect.set(0, 0, 0, mSpanSpace);
                    }
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            } else {
                if (parent.getChildAdapterPosition(view) < count) {
                    outRect.set(0, 0, 0, mSpanSpace);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            }
        } else {
            //  如果左边展示一个占位
            if (mShowFirstLine) {
                if (parent.getChildAdapterPosition(view) < count) {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(mSpanSpace, 0, mSpanSpace, 0);
                    } else {
                        outRect.set(0, 0, mSpanSpace, 0);
                    }
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            } else {
                if (parent.getChildAdapterPosition(view) < count) {
                    outRect.set(0, 0, mSpanSpace, 0);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
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
            int bottom = top + mSpanSpace;
            if (mShowLastLine || i < childCount - 1) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mShowFirstLine && i == 0) {
                bottom = child.getTop()  +
                        Math.round(ViewCompat.getTranslationY(child));
                top = bottom - mSpanSpace;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            int right = left + mSpanSpace;
            if (mShowLastLine || i < childCount - 1) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
            if (mShowFirstLine && i == 0) {
                left = child.getLeft() - params.leftMargin +
                        Math.round(ViewCompat.getTranslationX(child));
                right = left + mSpanSpace;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    private Drawable mDivider;
    private boolean mShowLastLine;

    public void setShowLastDivideLine(boolean show) {
        mShowLastLine = show;
    }

    private boolean mShowFirstLine;

    public void setShowFirstDivideLine(boolean show) {
        mShowFirstLine = show;
    }
}
