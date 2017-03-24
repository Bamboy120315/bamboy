package com.bamboy.bamboycollected.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bamboy.bamboycollected.BuildConfig;

import org.json.JSONException;

import java.util.List;

/**
 * 必需的小工具
 * <p>
 * Created by Bamboy on 2017/3/24.
 */
public class UtilWant {

    /**
     * 判断字符串是否为空
     *
     * @param str 需要判断的字符串
     * @return true即为空；false不为空
     */
    public boolean isNull(String str) {
        if (str == null
                || "".equals(str)
                || "null".equals(str)
                || "[null]".equals(str)
                || "{null}".equals(str)
                || "[]".equals(str)
                || "{}".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断TextView或其内容是否为空
     *
     * @param tv 需要判断的TextView
     * @return true即为空；false不为空
     */
    public boolean isNull(TextView tv) {
        if (tv == null
                || tv.getText() == null
                || isNull(tv.getText().toString())) {
            return true;
        }
        return false;
    }

    /**
     * 判断list或其内容是否为空
     *
     * @param list 需要判断的list
     * @return true即为空；false不为空
     */
    public boolean isNull(List list) {
        if (list == null
                || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空List
     *
     * @param list 要清空的List
     */
    public void clearList(List list) {
        if (!isNull(list)) {
            for (int i = list.size() - 1; i >= 0; i--) {
                list.remove(i);
            }
        }
    }


    public void showInput(EditText et) {
        InputMethodManager inputManager =
                (InputMethodManager) et.getContext().
                        getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(et, 0);
    }

    /**
     * 收起输入法
     *
     * @param activity 上下文
     */
    @SuppressWarnings("static-access")
    public void hideInput(Activity activity) {
        try {
            ((InputMethodManager) activity
                    .getSystemService(activity.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(
                            activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    /**
     * 打印错误日志【非打包模式下才会打印】
     *
     * @param e
     */
    public void showException(Exception e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }

    /**
     * 打印错误日志
     *
     * @param e
     */
    public void showException(JSONException e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
    }
}
