package com.bamboy.bamboycollected.base;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.util.UtilBox;

/**
 * Activity 基类
 * <p>
 * 右滑关闭
 * 沉浸式
 * 先显示后加载
 * 工具箱
 * <p>
 * Created by Bamboy on 2017/3/24.
 */
public abstract class BamActivity extends Activity {

    /**
     * 手势探测器
     */
    private GestureDetector mGDetector;
    /**
     * 滑动速度
     */
    private float slideSpeed = 0;
    /**
     * 根View
     */
    private View rootView;
    /**
     * 滑动关闭开关
     */
    private boolean slideOpen = true;

    /**
     * 执行模拟onCreate标记
     */
    protected boolean isCreate = true;
    /**
     * 状态高度
     */
    private static int barHeight = -1;
    /**
     * 工具箱
     */
    public UtilBox util = UtilBox.getUtilBox();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isCreate = true;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        /**
         * 如果是Android 4.4 以上，就兼容沉浸式
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                // 初始化状态栏
                initBar();
            } catch (Exception e) {
                util.want.showException(e);
            }
        }

        // 获取根布局，用于右滑关闭
        rootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        // 手势监听，用于监听滑动
        mGDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            /**
             * 按下 未抬起
             */
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            /**
             * 短按
             */
            @Override
            public void onShowPress(MotionEvent e) {
            }

            /**
             * 单击 并 抬起
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            /**
             * 滑动 开始滑动即开始执行 无需抬起
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float e1X = e1.getX();
                float e2X = e2.getX();
                if (false == slideOpen || rootView == null || e1X > 50) {
                    return false;
                }
                // 计算滑动距离
                float move = e2X > e1X ? e2X - e1X : 0;
                // 更新界面位置
                rootView.setX(move);
                // 记录滑动速度，用于抬起手指时计算滚回去还是滚出去
                slideSpeed = distanceX;
                return false;
            }

            /**
             * 长按
             */
            @Override
            public void onLongPress(MotionEvent e) {
            }

            /**
             * 滑动 飞翔 滑动时抬起监听
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    /**
     * 绑定手势
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        mGDetector.onTouchEvent(ev);

        // 触摸监听，用于监听抬起手指
        switch (ev.getAction()) {
            // 手指抬起
            case MotionEvent.ACTION_UP:

                if (false == slideOpen || rootView == null || rootView.getX() == 0) {
                    return super.dispatchTouchEvent(ev);
                }

                boolean isFinish = false;
                // 判断当前滑动有没有过屏幕的一半
                if (rootView.getX() < util.info.phoneWidth / 2) {
                    // 左半边
                    if (slideSpeed < 0) {
                        // 向右
                        slideSpeed = Math.abs(slideSpeed);
                        if (slideSpeed > 5) {
                            isFinish = true;
                        } else {
                            isFinish = false;
                        }
                    } else {
                        // 向左
                        isFinish = false;
                    }
                } else {
                    // 右半边
                    if (slideSpeed < 0) {
                        // 向右
                        isFinish = true;
                    } else {
                        // 向左
                        slideSpeed = Math.abs(slideSpeed);
                        if (slideSpeed > 5) {
                            isFinish = false;
                        } else {
                            isFinish = true;
                        }
                    }
                }

                if (false == isFinish) {    // 滚回去
                    ObjectAnimator.ofFloat(rootView, "X", rootView.getX(), 0).setDuration(250).start();
                } else {                    // 滚出去
                    ObjectAnimator anim = ObjectAnimator.ofFloat(rootView, "X", rootView.getX(), util.info.phoneWidth);
                    anim.setDuration(250);
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            finish(false);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    anim.start();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 开启滑动关闭界面
     *
     * @param open
     */
    protected void openSlideFinish(boolean open) {
        slideOpen = open;
    }

    /**
     * 初始化状态栏
     */
    private void initBar() {
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

    @Override
    protected void onResume() {
        super.onResume();

        if (isCreate)
            create();

    }

    protected void create() {
        isCreate = false;
        findView();
        setListener();
        init();
    }

    protected abstract void findView();

    protected abstract void setListener();

    protected abstract void init();

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
     * 展开介绍
     */
    protected void showIntroduce(View rl_introduce) {

        // 获取截图的Bitmap
        Bitmap bitmap = UtilBox.getUtilBox().ui.getDrawing(this);

        ImageView iv_introduce_back = (ImageView) rl_introduce.findViewById(R.id.iv_introduce_back);

        if (util.info.phoneSDK >= Build.VERSION_CODES.KITKAT && bitmap != null) {
            // 将截屏Bitma放入ImageView
            iv_introduce_back.setImageBitmap(bitmap);
            // 将ImageView进行高斯模糊【25是最高模糊等级】【最后一个参数是蒙上一层颜色，此参数可不填】
            // 如果需要更高的模糊程度，可以将此行代码写两遍
            util.bitmap.blurImageView(this, iv_introduce_back, 25, getResources().getColor(R.color.colorWhite_t8));
        } else {
            // 获取的Bitmap为null时，用半透明代替
            iv_introduce_back.setBackgroundColor(getResources().getColor(R.color.colorWhite_tD));
        }

        util.anim.showIntroduce(rl_introduce);
    }

    /**
     * 关闭介绍
     */
    protected void hideIntroduce(View rl_introduce) {
        util.anim.hideIntroduce(rl_introduce);
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
            startActivity(intent, R.anim.act_right_in, R.anim.act_left_out);
        } else {
            super.startActivity(intent);
        }
    }

    /**
     * 打开Activity
     *
     * @param intent intent
     */
    @Override
    public void startActivity(Intent intent) {
        startActivity(intent, true);
    }

    /**
     * 退出Activity
     */
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
            finish(R.anim.act_left_in, R.anim.act_right_out);
        } else {
            super.finish();
        }
    }

}
