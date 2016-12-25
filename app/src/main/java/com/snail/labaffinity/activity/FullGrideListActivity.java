package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseAdapter;
import com.snail.labaffinity.itemdorc.ExpandedGridLayoutManager;
import com.snail.labaffinity.itemdorc.GridLayoutItemDecoration;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class FullGrideListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        ExpandedGridLayoutManager layoutManager = new ExpandedGridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(false);
        GridLayoutItemDecoration grideItemDorcration = new GridLayoutItemDecoration( 4);
        grideItemDorcration.setMargin(10, 10);
        mRecyclerView.addItemDecoration(grideItemDorcration);
        mRecyclerView.setAdapter(new BaseAdapter(100));
        mRecyclerView.setNestedScrollingEnabled(false);
//        recyclerview获取焦点自动滚动
        mRecyclerView.setFocusable(false);
    }
}
