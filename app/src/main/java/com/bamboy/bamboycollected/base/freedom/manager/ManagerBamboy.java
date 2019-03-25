package com.bamboy.bamboycollected.base.freedom.manager;

import com.bamboy.bamboycollected.page.bean.DivideBean;
import com.bamboy.bamboycollected.page.bean.FootPromptBean;
import com.bamboy.bamboycollected.page.bean.FourIconBean;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;
import com.bamboy.bamboycollected.page.bean.SingleImageBean;
import com.bamboy.bamboycollected.page.bean.TitleBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 程序猿B on 2017/11/29.
 */
public class ManagerBamboy {

    /**
     * 条目类型 和 对应的条目XML
     */
    private Map<Integer, Class> itemMap;

    private ManagerBamboy() {

    }

    private static class ManagerHolder {
        private static final ManagerBamboy INSTANCE = new ManagerBamboy();
    }

    public static final ManagerBamboy getManager() {
        return ManagerHolder.INSTANCE;
    }

    // ____________________________
    //  └——— 本合集所需的条目类型 ———┘
    /**
     * 条目类型 --> 单个的按钮
     */
    public static final int ITEM_TYPE_SINGLE_BUTTON = 0001;
    /**
     * 条目类型 --> 分批加载
     */
    public static final int ITEM_TYPE_DIVIDE_LOAD = 0002;
    /**
     * 条目类型 --> 分批加载底部提示文字
     */
    public static final int ITEM_TYPE_FOOT_PROMPT = 0003;
    /**
     * 条目类型 --> 四个【图标+文字】的按钮
     */
    public static final int ITEM_TYPE_FOUR_ICON = 0004;
    /**
     * 条目类型 --> 单个图片
     */
    public static final int ITEM_TYPE_SINGLE_IMAGE = 0005;
    /**
     * 条目类型 --> 标题
     */
    public static final int ITEM_TYPE_TITLE = 0006;

    /**
     * 获取集合
     *
     * @return
     */
    public Map<Integer, Class> getMap() {
        if (itemMap == null) {
            itemMap = new HashMap<>();

            // ▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁
            // ━━━━━━━━━━━━━━━ 本合集所需的条目类型 ━━━━━━━━━━━━━━━━━━━━━━━
            itemMap.put(ITEM_TYPE_SINGLE_BUTTON, SingleBtnBean.SingleBtnViewHolder.class);
            itemMap.put(ITEM_TYPE_DIVIDE_LOAD, DivideBean.DivideLoadItemViewHolder.class);
            itemMap.put(ITEM_TYPE_FOOT_PROMPT, FootPromptBean.FootPromptViewHolder.class);
            itemMap.put(ITEM_TYPE_FOUR_ICON, FourIconBean.FourIconViewHolder.class);
            itemMap.put(ITEM_TYPE_SINGLE_IMAGE, SingleImageBean.SingleImageViewHolder.class);
            itemMap.put(ITEM_TYPE_TITLE, TitleBean.TitleViewHolder.class);
        }

        return itemMap;
    }
}
