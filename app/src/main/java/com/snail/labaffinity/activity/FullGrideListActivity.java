package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseAdapter;
import com.snail.labaffinity.itemdorc.FullyGridLayoutManager;
import com.snail.labaffinity.itemdorc.GridLayoutItemDecoration;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class FullGrideListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ScrollView mScrollView;

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        FullyGridLayoutManager layoutManager = new FullyGridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(false);
        GridLayoutItemDecoration grideItemDorcration = new GridLayoutItemDecoration(this, 5);
        grideItemDorcration.setMargin(40, 40);
        mRecyclerView.addItemDecoration(grideItemDorcration);
        mRecyclerView.setAdapter(new BaseAdapter(100));
        mRecyclerView.setNestedScrollingEnabled(false);


        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Log.v("lishang", " s " + mScrollView.getScrollY());
            }
        });
    }
}
