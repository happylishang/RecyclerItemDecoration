package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;

import com.snail.labaffinity.R;
import com.snail.labaffinity.SimpleOnItemTouchListener.SimpleOnItemClickLister;
import com.snail.labaffinity.adapter.BaseAdapter;
import com.snail.labaffinity.itemdorc.FullLinearlayoutManager;
import com.snail.labaffinity.itemdorc.LinearItemDecorationration;
import com.snail.labaffinity.viewholder.ItemViewHolder;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:51
 * Des:
 * version:
 */
public class FullLinearListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private ScrollView mScrollView;

    //    线性的没多大用途
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_list);

        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        ButterKnife.bind(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        FullLinearlayoutManager layoutManager = new FullLinearlayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
        mRecyclerView.addOnItemTouchListener(new SimpleOnItemClickLister<ItemViewHolder>(mRecyclerView) {

            @Override
            public void onItemClick(ItemViewHolder vh, int postion) {
                Toast.makeText(FullLinearListActivity.this, "" + postion, Toast.LENGTH_SHORT).show();
            }
        });

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setNestedScrollingEnabled(false);
        LinearItemDecorationration grideItemDorcration = new LinearItemDecorationration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(grideItemDorcration);
        mRecyclerView.setAdapter(new BaseAdapter(40));
        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                Log.v("lishang", "mRecyclerView s  " + mRecyclerView.getMeasuredHeight());
            }
        });
//        mRecyclerView.setHasFixedSize(false);


        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Log.v("lishang", " s " + mScrollView.getScrollY());
            }
        });

    }

    @OnClick(R.id.btn)
    void btn() {
        int i = 0;
        Log.v("lishang", "" + mRecyclerView.getMeasuredHeight());
        i++;
    }
}
