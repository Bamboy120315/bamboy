package com.bamboy.bamboycollected.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.util.UtilBox;
import com.bamboy.bamboycollected.view.SlideBackLayout;

/**
 * Created by liushaochen on 2017/3/24.
 */
public abstract class BamboyActivity extends Activity {
    /**
     * 执行模拟onCreate标记
     */
    private boolean isCreate = true;
    /**
     * 右滑关闭View
     */
    private SlideBackLayout mSlideBack;
    /**
     * 状态高度
     */
    private static int barHeight = -1;
    /**
     * 工具箱
     */
    protected UtilBox util = UtilBox.getUtilBox();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSlideBack = new SlideBackLayout(this);
        mSlideBack.bind();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        /**
         * 如果是Android 4.4 以上，就兼容沉浸式
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (barHeight == -1) {
                barHeight = util.ui.getBarHeight(this);
            }

            // 根布局顶部加上了状态栏高度的间距
            View view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
            view.setPadding(
                    view.getPaddingLeft(),
                    view.getPaddingTop() + barHeight,
                    view.getPaddingRight(),
                    view.getPaddingBottom());
        }
    }

    @Override
    public void startActivity(Intent intent) {
        startActivity(intent, true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isCreate)
            create();

    }

    private void create() {
        isCreate = false;
        findView();
        setListener();
        init();
    }

    protected abstract void findView();

    protected abstract void setListener();

    protected abstract  void init();

    /**
     * 设置沉浸TitleBar
     *
     * @param topView
     */
    protected void setImmerseTitleBar(final View topView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }

        // 去掉根布局顶部的状态栏高度间距
        View view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        view.setPadding(
                view.getPaddingLeft(),
                view.getPaddingTop() - barHeight,
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
                    topView.setLayoutParams(new RelativeLayout.LayoutParams(-1, topView.getHeight() + barHeight));
                } else if (parentView instanceof LinearLayout) {
                    topView.setLayoutParams(new LinearLayout.LayoutParams(-1, topView.getHeight() + barHeight));
                } else if (parentView instanceof FrameLayout) {
                    topView.setLayoutParams(new FrameLayout.LayoutParams(-1, topView.getHeight() + barHeight));
                }

                topView.setPadding(
                        topView.getPaddingLeft(),
                        topView.getPaddingTop() + barHeight,
                        topView.getPaddingRight(),
                        topView.getPaddingBottom());
            }
        });


    }

    /**
     * 开启滑动关闭界面
     *
     * @param open
     */
    protected void openSlideFinish(boolean open) {
        mSlideBack.setmSlideX(open ? 0 : -1);
    }

    /**
     * 打开新Activity
     *
     * @param intent  intent
     * @param animIn  新Activity进入的动画
     * @param animOut 当前Activity退出的动画
     */
    public void startActivity(Intent intent, int animIn, int animOut) {
        super.startActivity(intent);
        overridePendingTransition(animIn, animOut);
    }

    /**
     * 打开新的Activity
     *
     * @param intent intent
     * @param isAnim 是否开启过渡动画
     */
    public void startActivity(Intent intent, boolean isAnim) {
        if (isAnim) {
            startActivity(intent, R.anim.bamboy_right_in, R.anim.bamboy_left_out);
        } else {
            super.startActivity(intent);
        }
    }


    @Override
    public void finish() {
        finish(true);
    }

    /**
     * 退出Activity
     *
     * @param animIn  老Activity进入的动画
     * @param animOut 当前Activity退出的动画
     */
    public void finish(int animIn, int animOut) {
        super.finish();
        overridePendingTransition(animIn, animOut);
    }

    /**
     * 退出Activity
     *
     * @param isAnim 是否开启过渡动画
     */
    public void finish(boolean isAnim) {
        if (isAnim) {
            finish(R.anim.bamboy_left_in, R.anim.bamboy_right_out);
        } else {
            super.finish();
        }
    }

}
