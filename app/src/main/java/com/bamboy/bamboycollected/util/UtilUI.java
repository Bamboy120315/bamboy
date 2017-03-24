package com.bamboy.bamboycollected.util;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * 关于UI的工具类
 *
 * Created by Bamboy on 2017/3/24.
 */
public class UtilUI {

    /**
     *  获取状态栏高度
     * @return 状态栏高度
     */
    public int getBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 38;//默认为38，貌似大部分是这样的

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
    
}
