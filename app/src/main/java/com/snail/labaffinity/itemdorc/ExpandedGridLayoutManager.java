package com.snail.labaffinity.itemdorc;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class ExpandedGridLayoutManager extends GridLayoutManager {
    public ExpandedGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public ExpandedGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    private int[] measuredDimension = new int[2];

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        int measureWidth = 0;
        int measureHeight = 0;
        int count = getItemCount();
        int span = getSpanCount();
        for (int i = 0; i < count; i++) {
            measuredDimension = getChildDimension(recycler, i);
            if (getOrientation() == HORIZONTAL) {
                if (i % span == 0 ) {
                    measureWidth = measureWidth + measuredDimension[0];
                }
                measureHeight = Math.max(measureHeight, measuredDimension[1]);
            } else {
                if (i % span == 0) {
                    measureHeight = measureHeight + measuredDimension[1];
                }
                measureWidth = Math.max(measureWidth, measuredDimension[0]);
            }
        }
        measureHeight = measureHeight + getPaddingBottom() + getPaddingTop();
        measureWidth = measureWidth + getPaddingLeft() + getPaddingRight();
        measureHeight = heightMode == View.MeasureSpec.EXACTLY ? heightSize : measureHeight;
        measureWidth = widthMode == View.MeasureSpec.EXACTLY ? widthSize : measureWidth;
        if (getOrientation() == HORIZONTAL && measureWidth > widthSize) {
            if (widthMode == View.MeasureSpec.UNSPECIFIED) {
                setMeasuredDimension(measureWidth, measureHeight);
            } else {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        } else if (getOrientation() == VERTICAL && measureHeight > heightSize) {
            if (heightMode == View.MeasureSpec.UNSPECIFIED) {
                setMeasuredDimension(measureWidth, measureHeight);
            } else {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        } else {
            setMeasuredDimension(measureWidth, measureHeight);
        }
    }


    private int[] getChildDimension(RecyclerView.Recycler recycler, int position) {
        try {
            int[] measuredDimension = new int[2];
            View view = recycler.getViewForPosition(position);
            //测量childView，以便获得宽高（包括ItemDecoration的限制）
            super.measureChildWithMargins(view, 0, 0);
            //获取childView，以便获得宽高（包括ItemDecoration的限制），以及边距
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
            return measuredDimension;
        } catch (Exception e) {
            Log.d("LayoutManager", e.toString());
        }
        return null;
    }
}