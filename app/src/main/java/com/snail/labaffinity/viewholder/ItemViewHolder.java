package com.snail.labaffinity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.snail.labaffinity.R;

/**
 * Author: lishang
 * Data: 16/5/23 下午6:53
 * Des:
 * version:
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.id_num);
    }

    public void renderView(String item) {
        mTextView.getLayoutParams().width = (mTextView.getResources().getDisplayMetrics().widthPixels - 5 * 20) / 6;
        mTextView.setText(item);
    }
}
