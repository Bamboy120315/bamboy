package com.bamboy.bamboycollected.base.freedom;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * 列表Bean的基类
 * <p/>
 * 所有用于Item的Bean均要继承本类，
 * 以保证对插件式列表的支持
 * <p/>
 * Created by Bamboy on 2017/4/13.
 */
public abstract class FreedomBean {

    /**
     * 条目的类型
     */
    private int itemType;
    /**
     * 监听，用于将数据绘制到ViewHolder上
     */
    private ViewHolderBindListener viewHolderBindListener;

    /**
     * 构造函数
     */
    public FreedomBean() {
        initItemType();
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    /**
     * 当前条目占屏幕的几分之一
     * @param spanCount 分割总数
     * @return
     */
    public int getSpanSize(int spanCount) {
        return spanCount / 1;
    }

    public ViewHolderBindListener getViewHolderBindListener() {
        return viewHolderBindListener;
    }

    public void setViewHolderBindListener(ViewHolderBindListener viewHolderBindListener) {
        this.viewHolderBindListener = viewHolderBindListener;
    }

    public FreedomCallback getCallback(Context context) {
        FreedomCallback callback = null;
        if (context instanceof FreedomCallback) {
            callback = (FreedomCallback) context;
        } else {
            Log.e("FreedomError","Activity:[" + context.getClass() + "]未实现【FreedomCallback】接口！\n" +
                    "java.lang.NullPointerException:\n" +
                    "\t[" + context.getClass() + "]未实现【FreedomCallback】接口！\n");
        }
        return callback;
    }

    public void bindViewHolder(Context context, ViewHolderManager.ViewHolder viewHolder, int position) {
        if (viewHolderBindListener != null) {
            viewHolderBindListener.onBindViewHolder(context, viewHolder, position);
        }
    }

    protected abstract void initBindView(List list);

    protected abstract void initItemType();
}
