package com.snail.labaffinity.itemdorc;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lishang on 16/5/11.
 */
public class ExpandedLinearLayoutManager extends LinearLayoutManager {
    public ExpandedLinearLayoutManager(Context context) {
        super(context);
    }

    public ExpandedLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ExpandedLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }


    /**
     * 最大展示的数目
     */
    private int mMaxItemCount = -1;

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);
        int width = 0;
        int height = 0;
        int count;
//
        if (mMaxItemCount < 0 || getItemCount() < mMaxItemCount) {
            count = getItemCount();
        } else {
            count = mMaxItemCount;
        }
        for (int i = 0; i < count; i++) {
            int[] measuredDimension = new int[2];
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    measuredDimension);

            if (getOrientation() == HORIZONTAL) {
                width = width + measuredDimension[0];
                if (i == 0) {
                    height = measuredDimension[1];
                }
            } else {
                height = height + measuredDimension[1];
                if (i == 0) {
                    width = measuredDimension[0];
                }
            }
        }
        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:
                width = widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }
        if (getOrientation() == VERTICAL && width > widthSize) {
            width = widthSize;
        } else if (getOrientation() == HORIZONTAL && height > heightSize) {
            height = heightSize;
        }
        setMeasuredDimension(width, height);
    }


    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        try {
            View view = recycler.getViewForPosition(position);
            //测量childView，以便获得宽高（包括ItemDecoration的限制）
            super.measureChildWithMargins(view, 0, 0);
            //获取childView，以便获得宽高（包括ItemDecoration的限制），以及边距
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
        } catch (Exception e) {
            Log.d("LayoutManager", e.toString());
        }
    }

    public void setMaxItemCount(int maxItemCount) {
        mMaxItemCount = maxItemCount;
    }
}
