package com.bamboy.bamboycollected.base.freedom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.bean.DivideBean;
import com.bamboy.bamboycollected.page.bean.FootPromptBean;
import com.bamboy.bamboycollected.page.bean.FourIconBean;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;
import com.bamboy.bamboycollected.page.bean.SingleImageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewHolder的管理类
 * 用于存放ViewHolder的基类
 * 和 条目类型与相对应的条目XML
 * <p>
 * Created by Bamboy on 2017/4/13.
 */
public class ViewHolderManager {
    /**
     * 条目类型 --> 单个的按钮
     */
    public static final int ITEM_TYPE_SINGLE_BUTTON = 1;
    /**
     * 条目类型 --> 分批加载
     */
    public static final int ITEM_TYPE_DIVIDE_LOAD = 2;
    /**
     * 条目类型 --> 分批加载底部提示文字
     */
    public static final int ITEM_TYPE_FOOT_PROMPT = 3;
    /**
     * 条目类型 --> 四个【图标+文字】的按钮
     */
    public static final int ITEM_TYPE_FOUR_ICON = 4;
    /**
     * 条目类型 --> 单个图片
     */
    public static final int ITEM_TYPE_SINGLE_IMAGE = 5;

    /**
     * 条目类型 和 对应的条目XML
     */
    private static Map<Integer, Integer> itemMap;

    /**
     * 加载条目类型，以及对应的条目XML
     */
    static {
        itemMap = new HashMap<>();
        itemMap.put(ITEM_TYPE_SINGLE_BUTTON, R.layout.item_single_button);
        itemMap.put(ITEM_TYPE_DIVIDE_LOAD, R.layout.item_divide_load);
        itemMap.put(ITEM_TYPE_FOOT_PROMPT, R.layout.item_divide_foot_prompt);
        itemMap.put(ITEM_TYPE_FOUR_ICON, R.layout.item_four_icon);
        itemMap.put(ITEM_TYPE_SINGLE_IMAGE, R.layout.item_single_image);
    }

    /**
     * ViewHolder基类
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    public static ViewHolder createViewHolder(ViewGroup viewGroup, int viewType) {
        ViewHolderManager.ViewHolder holder = null;
        switch (viewType) {
            case ITEM_TYPE_SINGLE_BUTTON:           // 单个的按钮
                holder = new SingleBtnBean.SingleBtnViewHolder(findView(viewGroup, itemMap.get(viewType)));
                break;
            case ITEM_TYPE_DIVIDE_LOAD:             // 分批加载
                holder = new DivideBean.DivideLoadItemViewHolder(findView(viewGroup, itemMap.get(viewType)));
                break;
            case ITEM_TYPE_FOOT_PROMPT:             // 分批加载底部提示文字
                holder = new FootPromptBean.FootPromptViewHolder(findView(viewGroup, itemMap.get(viewType)));
                break;
            case ITEM_TYPE_FOUR_ICON:               // 四个【图标+文字】的按钮
                holder = new FourIconBean.FourIconViewHolder(findView(viewGroup, itemMap.get(viewType)));
                break;
            case ITEM_TYPE_SINGLE_IMAGE:            // 单个图片
                holder = new SingleImageBean.SingleImageViewHolder(findView(viewGroup, itemMap.get(viewType)));
                break;
        }
        return holder;
    }

    /**
     * 获取View
     * <p>
     * Ps：简化createViewHolder里的代码
     *
     * @param viewGroup
     * @param itemId
     * @return
     */
    private static View findView(ViewGroup viewGroup, int itemId) {
        return LayoutInflater.from(viewGroup.getContext())
                .inflate(itemId, viewGroup, false);
    }

}
