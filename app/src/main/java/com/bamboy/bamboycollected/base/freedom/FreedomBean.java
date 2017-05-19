package com.bamboy.bamboycollected.base.freedom;

import android.app.Activity;

import com.bamboy.bamboycollected.utils.UtilBox;

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

    public ViewHolderBindListener getViewHolderBindListener() {
        return viewHolderBindListener;
    }

    public void setViewHolderBindListener(ViewHolderBindListener viewHolderBindListener) {
        this.viewHolderBindListener = viewHolderBindListener;
    }

    public FreedomCallback getCallback(Activity activity) {
        FreedomCallback callback = null;
        if (activity instanceof FreedomCallback) {
            callback = (FreedomCallback) activity;
        } else {
            UtilBox.getBox().log.e("Activity:[" + activity.getClass() + "]未实现【FreedomCallback】接口！\n" +
                    "java.lang.NullPointerException:\n" +
                    "\t[" + activity.getClass() + "]未实现【FreedomCallback】接口！\n");
        }
        return callback;
    }

    public void bindViewHolder(Activity activity, ViewHolderManager.ViewHolder viewHolder, int position) {
        if (viewHolderBindListener != null) {
            viewHolderBindListener.onBindViewHolder(activity, viewHolder, position);
        }
    }

    protected abstract void initBindView(List list);

    protected abstract void initItemType();
}
