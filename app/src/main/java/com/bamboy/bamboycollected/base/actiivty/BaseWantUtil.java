package com.bamboy.bamboycollected.base.actiivty;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.utils.UtilBox;

/**
 * Base Activity必备工具类
 *
 * Created by Bamboy on 2017/4/10.
 */
public class BaseWantUtil {

    private Activity mActivity;
    /**
     * 工具箱
     */
    public UtilBox utils;

    public BaseWantUtil(Activity activity) {
        mActivity = activity;
        utils = UtilBox.getBox();
    }

    /**
     * 初始化状态栏
     */
    public void initBar() {
        //透明状态栏
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // 根布局顶部加上了状态栏高度的间距
        View view = ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
        view.setPadding(
                view.getPaddingLeft(),
                view.getPaddingTop()
                        + utils.ui.getBarHeight(mActivity),
                view.getPaddingRight(),
                view.getPaddingBottom());
    }

    /**
     * 设置沉浸TitleBar
     *
     * @param topView
     */
    public void setImmerseTitleBar(final View topView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT || topView == null) {
            return;
        }
        // 去掉根布局顶部的状态栏高度间距
        View view = ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
        view.setPadding(
                view.getPaddingLeft(),
                view.getPaddingTop()
                        - utils.ui.getBarHeight(mActivity),
                view.getPaddingRight(),
                view.getPaddingBottom());
        /**
         * titleBar加上状态栏高度的间距
         *
         * 这个时候Activity还在加载布局，
         * 获取控件的宽高都是0，
         * 所以要写在view.post(new Runnable() {……})里
         */
        topView.post(new Runnable() {
            @Override
            public void run() {
                View parentView = (View) topView.getParent();

                if (parentView instanceof RelativeLayout) {
                    topView.setLayoutParams(
                            new RelativeLayout.LayoutParams(
                                    -1,
                                    topView.getHeight()
                                            + utils.ui.getBarHeight(mActivity)));
                } else if (parentView instanceof LinearLayout) {
                    topView.setLayoutParams(
                            new LinearLayout.LayoutParams(
                                    -1,
                                    topView.getHeight()
                                            + utils.ui.getBarHeight(mActivity)));
                } else if (parentView instanceof FrameLayout) {
                    topView.setLayoutParams(
                            new FrameLayout.LayoutParams(
                                    -1,
                                    topView.getHeight()
                                            + utils.ui.getBarHeight(mActivity)));
                }

                topView.setPadding(
                        topView.getPaddingLeft(),
                        topView.getPaddingTop()
                                + utils.ui.getBarHeight(mActivity),
                        topView.getPaddingRight(),
                        topView.getPaddingBottom());

            }
        });
    }

    /**
     * 展开介绍
     */
    public void showIntroduce(View rl_introduce) {

        // 获取截图的Bitmap
        Bitmap bitmap = UtilBox.getBox().ui.getDrawing(mActivity);

        // 介绍View的背景
        ImageView iv_introduce_back = null;
        try {
            iv_introduce_back = rl_introduce.findViewById(R.id.iv_introduce_back);
        } catch (Exception e) {
            return;
        }

        if (utils.info.getPhoneSDK() >= Build.VERSION_CODES.KITKAT && bitmap != null) {
            // 将截屏Bitma放入ImageView
            iv_introduce_back.setImageBitmap(bitmap);
            // 将ImageView进行高斯模糊【25是最高模糊等级】【最后一个参数是蒙上一层颜色，此参数可不填】
            // 如果需要更高的模糊程度，可以将此行代码写两遍
            utils.bitmap.blurImageView(mActivity, iv_introduce_back, 25, mActivity.getResources().getColor(R.color.colorWhite_tA));
        } else {
            // 获取的Bitmap为null时，用半透明代替
            iv_introduce_back.setBackgroundColor(mActivity.getResources().getColor(R.color.colorWhite_tD));
        }

        utils.anim.showIntroduce(rl_introduce);
    }

    /**
     * 关闭介绍
     */
    public void hideIntroduce(View rl_introduce) {
        utils.anim.hideIntroduce(rl_introduce);
    }

}
