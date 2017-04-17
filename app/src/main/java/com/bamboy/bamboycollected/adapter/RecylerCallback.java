package com.bamboy.bamboycollected.adapter;

import android.view.View;

/**
 * RecylerView回调
 * 用于item与Activity之间的事件交互
 *
 * Created by Bamboy on 2017/4/14.
 */
public interface RecylerCallback {

    /**
     * 回调 用于item与Activity之间点击事件交互
     *
     * @param view 点击的View
     * @param position 列表的第N个条目
     * @param holder 所使用的的ViewHolder
     */
    void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder);
}
