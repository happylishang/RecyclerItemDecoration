package com.snail.labaffinity.SimpleOnItemTouchListener;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author: lishang
 * Data: 16/5/25 下午8:35
 * Des:
 * version:
 */
abstract public class SimpleOnItemClickLister<T extends RecyclerView.ViewHolder> extends RecyclerView.SimpleOnItemTouchListener {

    private GestureDetector mGestureDetector;

    public SimpleOnItemClickLister(RecyclerView rv) {
        mGestureDetector = new GestureDetector(rv.getContext(), new OnGestureListerner(rv));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }
    class OnGestureListerner extends GestureDetector.SimpleOnGestureListener {
        private RecyclerView mRecyclerView;

        public OnGestureListerner(RecyclerView rv) {
            mRecyclerView = rv;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            int pos = mRecyclerView.getChildAdapterPosition(child);
            if (child != null) {
                T _vh = (T) mRecyclerView.getChildViewHolder(child);
                onItemClick(_vh, pos);
            }
            return true;
        }
    }

    public abstract void onItemClick(T vh, int postion);
}
