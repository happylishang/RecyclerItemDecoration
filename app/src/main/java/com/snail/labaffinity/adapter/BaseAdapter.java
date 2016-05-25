package com.snail.labaffinity.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snail.labaffinity.R;
import com.snail.labaffinity.viewholder.ItemViewHolder;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:53
 * Des:
 * version:
 */
public class BaseAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView textView = new TextView(parent.getContext());
        textView.setHeight(180);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setBackground(parent.getContext().getDrawable(R.drawable.bg));
        }else {
            textView.setBackground(parent.getContext().getResources().getDrawable(R.drawable.bg));
        }
        textView.setGravity(Gravity.CENTER);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    private int mCount;
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.renderView("" + position);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

   public BaseAdapter(int size){
        mCount =size;
    }
}
