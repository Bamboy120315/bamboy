package com.bamboy.bamboycollected.adapter;

import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.utils.UtilBox;
import com.bamboy.bamboycollected.views.BamToast;

import java.util.List;

/**
 * 列表Bean的基类
 * <p>
 * 所有用于Item的Bean均要继承本类，
 * 以保证对插件式列表的支持
 * <p>
 * Created by Bamboy on 2017/4/13.
 */
public abstract class AdapterBean {
    /**
     * 工具箱
     */
    public UtilBox utils = UtilBox.getUtilBox();

    /**
     * 条目的类型
     */
    private int itemType;
    /**
     * 监听，用于将数据绘制到ViewHolder上
     */
    private OnBindViewHolderListener bindListener;

    protected BamActivity activity;
    /**
     * 监听，用于Activity与ViewHolde的View的交互
     */
    private RecylerCallback callback;

    /**
     * 构造函数
     *
     * @param activity
     */
    public AdapterBean(BamActivity activity) {
        this.activity = activity;
        if (activity instanceof RecylerCallback) {
            this.callback = (RecylerCallback) activity;
        } else {
            BamToast.show(activity, "Activity未实现RecylerCallback接口！", BamToast.COLOR_RED);
            utils.log.e("Activity未实现RecylerCallback接口！");
        }
        initItemType();
    }

    /**
     * 将数据填充到ViewHolder上
     */
    public interface OnBindViewHolderListener {
        void onBind(ViewHolderManager.ViewHolder viewHolder, int position);
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public OnBindViewHolderListener getBindListener() {
        return bindListener;
    }

    public void setBindListener(OnBindViewHolderListener bindListener) {
        this.bindListener = bindListener;
    }

    public BamActivity getActivity() {
        return activity;
    }

    public void setActivity(BamActivity activity) {
        this.activity = activity;
    }

    public RecylerCallback getCallback() {
        if (callback == null){
            utils.log.e("Activity未实现RecylerCallback接口！");
        }
        return callback;
    }

    public void setCallback(RecylerCallback callback) {
        this.callback = callback;
    }

    public void bindViewHolder(ViewHolderManager.ViewHolder viewHolder, int position) {
        if (bindListener != null) {
            bindListener.onBind(viewHolder, position);
        }
    }

    protected abstract void initBindView(List list);

    protected abstract void initItemType();
}
