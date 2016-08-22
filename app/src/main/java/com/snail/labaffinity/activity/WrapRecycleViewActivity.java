package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.adapter.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: lishang
 * Data: 16/5/25 下午7:35
 * Des: 升级到新的 list就可以wrap
 * version:
 *
 *
 *
 * By Android Support Library 23.2.1 update, all WRAP_CONTENT should work correctly.

 Please update version of a library in gradle file :

 compile 'com.android.support:recyclerview-v7:23.2.1'
 solved some issue like Fixed bugs related to various measure-spec methods

 Check http://developer.android.com/tools/support-library/features.html#v7-recyclerview

 Currently, updated version is

 com.android.support:recyclerview-v7:23.3.0

 */
public class WrapRecycleViewActivity extends AppCompatActivity {

    @BindView(R.id.list) RecyclerView mRecyclerView;

//占用剩余的部分，不超过屏幕，
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_list );
        ButterKnife.bind(this);
//        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutmanger=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutmanger);
        mRecyclerView.setAdapter(new BaseAdapter(3));
    }
}
