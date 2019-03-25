package com.bamboy.bamboycollected.base.freedom.manager;

import com.bamboy.bamboycollected.page.freedom.bean.BeanMusic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 程序猿B on 2017/11/29.
 */
public class ManagerB {

    /**
     * 条目类型 和 对应的条目XML
     */
    private Map<Integer, Class> itemMap;

    private ManagerB() {

    }

    private static class ManagerHolder {
        private static final ManagerB INSTANCE = new ManagerB();
    }

    public static final ManagerB getManager() {
        return ManagerHolder.INSTANCE;
    }

    // ____________________________
    //  └——— 音乐相关的条目类型 ———┘
    /**
     * 条目类型 --> 歌曲条目
     */
    public static final int ITEM_TYPE_MUSIC = 2000;

    /**
     * 获取集合
     *
     * @return
     */
    public Map<Integer, Class> getMap() {
        if (itemMap == null) {
            itemMap = new HashMap<>();

            // ▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁▁
            // ━━━━━━━━━━━━━━━ 【音乐】相关 ━━━━━━━━━━━━━━━━━━━━━━━
            itemMap.put(ITEM_TYPE_MUSIC, BeanMusic.MusicViewHolder.class);
        }

        return itemMap;
    }
}
