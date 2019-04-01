package com.bamboy.bamboycollected.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bamboy.bamboycollected.BuildConfig;

import org.json.JSONException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void showException(Error e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
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


    private final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    /**
     * 用来判断是否开启通知权限
     * */
    public boolean isNotificationEnabled(Context context){
        if (Build.VERSION.SDK_INT >= 24) {
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            boolean isOpened = manager.areNotificationsEnabled();
            return isOpened;
        } else if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOps =
                    (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE,
                        Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
                int value = (int) opPostNotificationValue.get(Integer.class);
                return ((int) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg)
                        == AppOpsManager.MODE_ALLOWED);
            } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException
                    | InvocationTargetException | IllegalAccessException | RuntimeException e) {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 获取当前版本号
     *
     * @return 版本号
     */
    public String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
