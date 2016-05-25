package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.snail.labaffinity.adapter.BaseAdapter;
import com.snail.labaffinity.itemdorc.LinearItemDecorationration;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class LinearListActivity extends BaseListActivity {

//    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new LinearItemDecorationration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new BaseAdapter(60));
    }
}
