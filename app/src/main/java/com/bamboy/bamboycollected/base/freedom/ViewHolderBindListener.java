package com.bamboy.bamboycollected.base.freedom;

import android.content.Context;

/**
 * 将数据填充到ViewHolder上
 * <p/>
 * Created by Bamboy on 2017/4/27.
 */
public interface ViewHolderBindListener {
    /**
     * 将数据填充到ViewHolder上
     *
     * @param context
     * @param viewHolder ViewHolder
     * @param position   索引值
     */
    void onBindViewHolder(Context context, ViewHolderManager.ViewHolder viewHolder, int position);
}
