package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseHorizionAdapter;
import com.snail.labaffinity.itemdorc.ExpandedLinearLayoutManager;
import com.snail.labaffinity.itemdorc.LinearItemDecoration;

import butterknife.ButterKnife;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class HoriziontFullLinearListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_full_list);

        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        ExpandedLinearLayoutManager layoutManager = new ExpandedLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setAutoMeasureEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
//        防止滑动不流畅
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearItemDecoration itemDecoration = new LinearItemDecoration();
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(new BaseHorizionAdapter(40));
    }

}
