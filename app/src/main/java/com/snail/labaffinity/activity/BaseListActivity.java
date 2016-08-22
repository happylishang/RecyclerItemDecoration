package com.snail.labaffinity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.snail.labaffinity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:49
 * Des:
 * version:
 */
public class BaseListActivity extends AppCompatActivity {

    @BindView(R.id.list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
    }
}
