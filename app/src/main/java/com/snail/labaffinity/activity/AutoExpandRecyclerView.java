package com.snail.labaffinity.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Author: lishang
 * Data: 16/5/24 下午2:29
 * Des:
 * version:
 */
public class AutoExpandRecyclerView extends RecyclerView {

    private int distance = 30;
    private int x;
    private int y;

    public AutoExpandRecyclerView(Context context) {
        super(context);
    }

    public AutoExpandRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoExpandRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                x = (int) ev.getX();
                y = (int) ev.getY();
                return false;
            case MotionEvent.ACTION_MOVE:
                if ((Math.sqrt((ev.getX() - x) * (ev.getX() - x) + (ev.getY() - y) * (ev.getY() - y)) > 30 && Math.abs(getX() - x) < Math.abs(getY() - y))) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
