package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseVerticalAdapter;
import com.snail.labaffinity.itemdorc.GridLayoutItemDecoration;
import com.snail.labaffinity.itemdorc.HeaderFooterGridLayoutItemDecoration;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class GridListActivity extends BaseListActivity {

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        mRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutItemDecoration itemDecoration = new GridLayoutItemDecoration(layoutManager);
        itemDecoration.setPaddingColor(getResources().getColor(R.color.colorAccent));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.addItemDecoration(new HeaderFooterGridLayoutItemDecoration.Builder().
                footerDividerColor(0xffff00ff).
                footerSpanSpace(100).
                headerDividerColor(0xffffff00).
                headerSpanSpace(100).
                spanCount(6).
                build());
        mRecyclerView.setAdapter(new BaseVerticalAdapter(100));
    }
}
