package com.bamboy.bamboycollected.base.freedom.listener;

import android.app.Activity;

import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;

/**
 * 将数据填充到ViewHolder上
 * <p/>
 * Created by Bamboy on 2017/4/27.
 */
public interface ViewHolderBindListener {
    /**
     * 将数据填充到ViewHolder上
     *
     * @param activity
     * @param viewHolder ViewHolder
     * @param position 索引值
     */
    void onBindViewHolder(Activity activity, ViewHolderManager.ViewHolder viewHolder, int position);
}
