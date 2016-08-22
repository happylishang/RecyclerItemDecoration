package com.snail.labaffinity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.snail.labaffinity.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.linear)
    void linear() {

        Intent intent = new Intent(this, LinearListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.gird)
    void gride() {
        Intent intent = new Intent(this, GrideListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.full_gide)
    void fullGride() {
        Intent intent = new Intent(this, FullGrideListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.full_linear)
    void fullLinear() {
        Intent intent = new Intent(this, FullLinearListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.wrap_linear)
    void wrap() {
        Intent intent = new Intent(this, WrapRecycleViewActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.stage)
    void stage() {

    }
}
