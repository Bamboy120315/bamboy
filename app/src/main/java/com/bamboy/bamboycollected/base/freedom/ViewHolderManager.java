package com.bamboy.bamboycollected.base.freedom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboy.bamboycollected.page.bean.DivideBean;
import com.bamboy.bamboycollected.page.bean.FootPromptBean;
import com.bamboy.bamboycollected.page.bean.FourIconBean;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;
import com.bamboy.bamboycollected.page.bean.SingleImageBean;
import com.bamboy.bamboycollected.page.bean.TitleBean;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * ViewHolder的管理类
 * 用于存放ViewHolder的基类
 * 和 条目类型与相对应的Bean类型
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
     * 条目类型 --> 标题
     */
    public static final int ITEM_TYPE_TITLE = 6;

    /**
     * 条目类型 和 对应的条目XML
     */
    private static Map<Integer, Class> itemMap;

    /**
     * 加载条目类型，以及对应的条目XML
     */
    static {
        itemMap = new HashMap<>();
        itemMap.put(ITEM_TYPE_SINGLE_BUTTON, SingleBtnBean.SingleBtnViewHolder.class);
        itemMap.put(ITEM_TYPE_DIVIDE_LOAD, DivideBean.DivideLoadItemViewHolder.class);
        itemMap.put(ITEM_TYPE_FOOT_PROMPT, FootPromptBean.FootPromptViewHolder.class);
        itemMap.put(ITEM_TYPE_FOUR_ICON, FourIconBean.FourIconViewHolder.class);
        itemMap.put(ITEM_TYPE_SINGLE_IMAGE, SingleImageBean.SingleImageViewHolder.class);
        itemMap.put(ITEM_TYPE_TITLE, TitleBean.TitleViewHolder.class);
    }

    /**
     * ViewHolder基类
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(findView(viewGroup, layoutId));
        }
    }

    /**
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param viewType  当前item的类型，即上边的那一堆常量
     * @return
     */
    public static ViewHolder createViewHolder(ViewGroup viewGroup, int viewType) {
        try {
            // 获取Class对象
            Class itemClass = itemMap.get(viewType);
            // 开始反射，获取构造方法
            Constructor<?> cons[] = itemClass.getConstructors();
            // 运行方法，获取ViewHolder
            Object obj = cons[0].newInstance(viewGroup);

            return (ViewHolder) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
