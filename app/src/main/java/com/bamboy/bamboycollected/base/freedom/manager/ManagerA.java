package com.bamboy.bamboycollected.base.freedom.manager;

import com.bamboy.bamboycollected.page.freedom.bean.BeanNewsImg;
import com.bamboy.bamboycollected.page.freedom.bean.BeanNewsText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 程序猿A on 2017/10/16.
 */
public class ManagerA {

    /**
     * 条目类型 和 对应的条目XML
     */
    private Map<Integer, Class> itemMap;

    private ManagerA() {

    }

    private static class ManagerHolder {
        private static final ManagerA INSTANCE = new ManagerA();
    }

    public static final ManagerA getManager() {
        return ManagerHolder.INSTANCE;
    }

    // ____________________________
    //  └——— 新闻相关的条目类型 ———┘
    /**
     * 条目类型 --> 文字新闻条目类型
     */
    public static final int ITEM_TYPE_NEWS_TEXT = 1000;
    /**
     * 条目类型 --> 图片新闻条目类型
     */
    public static final int ITEM_TYPE_NEWS_IMG = 1001;

    /**
     * 获取集合
     *
     * @return
     */
    public Map<Integer, Class> getMap() {
        if (itemMap == null) {
            itemMap = new HashMap<>();

            // ▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁
            // ━━━━━━━━━━━━━━━━━ 【新闻】相关 ━━━━━━━━━━━━━━━━━━
            itemMap.put(ITEM_TYPE_NEWS_TEXT, BeanNewsText.NewsTextViewHolder.class);
            itemMap.put(ITEM_TYPE_NEWS_IMG, BeanNewsImg.NewsImgViewHolder.class);
        }

        return itemMap;
    }
}
