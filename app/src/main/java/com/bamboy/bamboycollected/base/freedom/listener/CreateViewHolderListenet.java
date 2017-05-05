package com.bamboy.bamboycollected.base.freedom.listener;

import android.view.View;

import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;

/**
 * Created by Bamboy on 2017/5/5.
 */
public interface CreateViewHolderListenet {

    /**
     * 回调 用于item与Activity之间点击事件交互
     *
     * @param view 点击的View
     * @param position 列表的第N个条目
     * @param holder 所使用的的ViewHolder
     */
    void createViewHolder(View view, int position, ViewHolderManager.ViewHolder holder);
}
