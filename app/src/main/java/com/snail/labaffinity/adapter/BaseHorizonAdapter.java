package com.snail.labaffinity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snail.labaffinity.R;
import com.snail.labaffinity.viewholder.ItemViewHolder;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:53
 * Des:
 * version:
 */
public class BaseHorizonAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizion,parent,false);
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

   public BaseHorizonAdapter(int size){
        mCount =size;
    }
}
