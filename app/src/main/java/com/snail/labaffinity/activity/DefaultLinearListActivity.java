package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.snail.labaffinity.SimpleOnItemTouchListener.SimpleOnItemClickLister;
import com.snail.labaffinity.adapter.BaseVerticalAdapter;
import com.snail.labaffinity.itemdorc.HeaderFooterLinearItemDecoration;
import com.snail.labaffinity.viewholder.ItemViewHolder;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class DefaultLinearListActivity extends BaseListActivity {

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new HeaderFooterLinearItemDecoration.Builder().headerSpanSpace(100).footerSpanSpace(100).headerDividerColor(0xffff0000).build());
        mRecyclerView.addItemDecoration(new HeaderFooterLinearItemDecoration.Builder().headerSpanSpace(100).footerSpanSpace(100).headerDividerColor(0xffff0000).footerDividerColor(0xffff0000).build());
        mRecyclerView.addItemDecoration(new HeaderFooterLinearItemDecoration.Builder().headerSpanSpace(100).footerSpanSpace(100).headerDividerColor(0xffff0000).build());
        mRecyclerView.setAdapter(new BaseVerticalAdapter(30));
        mRecyclerView.addOnItemTouchListener(new SimpleOnItemClickLister<ItemViewHolder>(mRecyclerView) {

            @Override
            public void onItemClick(ItemViewHolder vh, int postion) {
                Toast.makeText(DefaultLinearListActivity.this, "" + postion, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
