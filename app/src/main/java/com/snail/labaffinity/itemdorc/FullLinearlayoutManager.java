package com.snail.labaffinity.itemdorc;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lishang on 16/5/11.
 */
public class FullLinearlayoutManager extends LinearLayoutManager {
    public FullLinearlayoutManager(Context context) {
        super(context);
    }

    public FullLinearlayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FullLinearlayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }


    private int[] mMeasuredDimension = new int[2];

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
        if (mMaxtItemCount < 0 || getItemCount() < mMaxtItemCount) {
            count = getItemCount();
        } else {
            count = mMaxtItemCount;
        }
        for (int i = 0; i < count; i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);

            if (getOrientation() == HORIZONTAL) {
                width = width + mMeasuredDimension[0];
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                height = height + mMeasuredDimension[1];
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }
        // If child view is more than screen size, there is no need to make it wrap content. We can use original onMeasure() so we can scroll view.
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
        // For adding Item Decor Insets to view
        super.measureChildWithMargins(view, 0, 0);
        if (view != null) {

            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
//            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
//                    getPaddingLeft() + getPaddingRight() + getDecoratedLeft(view) + getDecoratedRight(view), p.width);
//            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
//                    getPaddingTop() + getPaddingBottom() + getPaddingBottom() + getDecoratedBottom(view), p.height);
//            view.measure(childWidthSpec, childHeightSpec);

            // Get decorated measurements
            measuredDimension[0] = getDecoratedMeasuredWidth(view) + p.leftMargin + p.rightMargin;
            measuredDimension[1] = getDecoratedMeasuredHeight(view) + p.bottomMargin + p.topMargin;
            recycler.recycleView(view);
        }    }catch (Exception e){

        }
    }

    private int mMaxtItemCount = -1;

    public void setMaxItemCount(int maxItemCount) {

        mMaxtItemCount = maxItemCount;
    }

}
