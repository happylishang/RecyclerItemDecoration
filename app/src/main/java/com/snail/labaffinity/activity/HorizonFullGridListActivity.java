package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseHorizonAdapter;
import com.snail.labaffinity.itemdorc.ExpandedGridLayoutManager;
import com.snail.labaffinity.itemdorc.GridLayoutItemDecoration;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class HorizonFullGridListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_full_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        ExpandedGridLayoutManager layoutManager = new ExpandedGridLayoutManager(this, 6);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setAutoMeasureEnabled(false);
        GridLayoutItemDecoration itemDecoration = new GridLayoutItemDecoration(6);
        itemDecoration.setDivideParams(10, 10);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(new BaseHorizonAdapter(100));
        mRecyclerView.setNestedScrollingEnabled(false);
//        recyclerview获取焦点自动滚动
        mRecyclerView.setFocusable(false);
    }
}
