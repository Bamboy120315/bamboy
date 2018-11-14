package com.bamboy.bamboycollected.page.toast.bamtoast.btoast;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bamboy.bamboycollected.R;

public class BToast extends Toast {

    /**
     * Toast单例
     */
    private static BToast toast;

    /**
     * Toast引用布局所需要的inflater
     */
    private static LayoutInflater inflater;

    /**
     * Toast所引用的布局
     */
    private static View layout;

    /**
     * Toast显示的文本
     */
    private static TextView toast_text;

    /**
     * Toast所显示的图片
     */
    private static ImageView toast_img;

    /**
     * 图标状态 不显示图标
     */
    public final static int ICONTYPE_NONE = 0;
    /**
     * 图标状态 显示对号图标
     */
    public final static int ICONTYPE_SUCCEED = 1;
    /**
     * 图标状态 显示叉号图标
     */
    public final static int ICONTYPE_ERROR = 2;

    /**
     * 构造
     *
     * @param context
     */
    public BToast(Context context) {
        super(context);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context  上下文
     * @param stringId 显示的文本的Id
     */
    public static BToast makeText(Context context, int stringId) {
        return getToast(context, context.getString(stringId), 0, ICONTYPE_NONE);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    public static BToast makeText(Context context, CharSequence text) {
        return getToast(context, text, 0, ICONTYPE_NONE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param stringId  显示的文本的Id
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static BToast makeText(Context context, int stringId, boolean isSucceed) {
        return getToast(context, context.getString(stringId), 0, isSucceed ? ICONTYPE_SUCCEED : ICONTYPE_ERROR);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static BToast makeText(Context context, CharSequence text,
                                  boolean isSucceed) {
        return getToast(context, text, 0, isSucceed ? ICONTYPE_SUCCEED : ICONTYPE_ERROR);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context  上下文
     * @param stringId 显示的文本的Id
     * @param time     持续的时间
     */
    public static BToast makeText(Context context, int stringId, int time) {
        return getToast(context, context.getString(stringId), time, ICONTYPE_NONE);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    持续的时间
     */
    public static BToast makeText(Context context, CharSequence text, int time) {
        return getToast(context, text, time, ICONTYPE_NONE);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param stringId  显示的文本的Id
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static BToast makeText(Context context, int stringId, int time,
                                  boolean isSucceed) {
        return getToast(context, context.getString(stringId), time, isSucceed ? ICONTYPE_SUCCEED : ICONTYPE_ERROR);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static BToast makeText(Context context, CharSequence text, int time,
                                  boolean isSucceed) {
        return getToast(context, text, time, isSucceed ? ICONTYPE_SUCCEED : ICONTYPE_ERROR);
    }

    /**
     * 获取Toast对象
     *
     * @param context  上下文
     * @param text     显示的文本
     * @param time     持续的时间
     * @param iconType 图标显示状态【0：不显示图标】【1：显示对号图标】2：显示叉号图标】
     */
    public static BToast getToast(Context context, CharSequence text, int time,
                                  int iconType) {
        initToast(context, text);

        switch (iconType) {
            case ICONTYPE_SUCCEED:
                toast_img.setBackgroundResource(R.drawable.toast_y);
                toast_img.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(toast_img, "rotationY", 30, 180, 0)
                        .setDuration(1000).start();
                break;
            case ICONTYPE_ERROR:
                toast_img.setBackgroundResource(R.drawable.toast_n);
                toast_img.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(toast_img, "rotationY", 30, 180, 0)
                        .setDuration(1000).start();
                break;
            case ICONTYPE_NONE:
                toast_img.setVisibility(View.GONE);
                break;
        }

        if (time == Toast.LENGTH_LONG) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        return toast;
    }

    /**
     * 初始化Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     */
    private static void initToast(Context context, CharSequence text) {
        try {
            cancelToast();

            toast = new BToast(context);
            // 获取LayoutInflater对象
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 由layout文件创建一个View对象
            layout = inflater.inflate(R.layout.toast_layout, null);

            // 吐司上的图片
            toast_img = layout.findViewById(R.id.toast_img);

            // 实例化ImageView和TextView对象
            toast_text = layout.findViewById(R.id.toast_text);
            toast_text.setText(text);
            toast.setView(layout);
            toast.setGravity(Gravity.CENTER, 0, 70);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏当前Toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}