package com.bamboy.bamboycollected.base.actiivty;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bamboy.bamboycollected.utils.UtilBox;

/**
 * BaseActivity触摸工具类
 * <p/>
 * Created by Bamboy on 2017/4/10.
 */
public class BaseGestureUtil {

    private BamActivity mActivity;
    /**
     * 工具箱
     */
    public UtilBox util;
    /**
     * 手势探测器
     */
    private GestureDetector mGDetector;
    /**
     * 滑动起点
     */
    private float startX;
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
     * 构造
     *
     * @param activity
     */
    public BaseGestureUtil(BamActivity activity) {
        this.mActivity = activity;
        util = UtilBox.getBox();

        // 初始化手势探测器 用于计算滑动速度
        initGestureDetector();
        // 获取根布局，用于右滑关闭
        rootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 开启滑动关闭界面
     *
     * @param open
     */
    public void openSlideFinish(boolean open) {
        slideOpen = open;
    }

    /**
     * 获取手势监听器
     *
     * @return 手势探测
     */
    public GestureDetector getGestureDetector() {
        if (mGDetector == null) {
            initGestureDetector();
        }
        return mGDetector;
    }

    /**
     * 初始化手势探测器 用于计算滑动速度
     */
    private void initGestureDetector() {
        mGDetector = new GestureDetector(mActivity, new GestureDetector.OnGestureListener() {
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
                // 记录滑动速度，用于抬起手指时计算滚回去还是滚出去
                slideSpeed = distanceX;
                return true;
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
     * 处理触摸事件
     *
     * @param motionEvent 触摸事件
     */
    public boolean motionEvent(MotionEvent motionEvent) {
        // 触摸监听，用于监听手指运动
        switch (motionEvent.getAction()) {
            // 手指按下
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                break;

            // 手指移动
            case MotionEvent.ACTION_MOVE:
                float nowX = motionEvent.getX();
                if (false == slideOpen || rootView == null || startX > util.info.getPhoneWidth() / 20) {
                    return false;
                }
                // 计算滑动距离
                float move = nowX > startX ? nowX - startX : 0;
                // 更新界面位置
                rootView.setX(move);
                return true;

            // 手指抬起
            case MotionEvent.ACTION_UP:

                if (false == slideOpen || rootView == null || rootView.getX() == 0)
                    return false;

                if (false == calculateIsFinish()) {     // 不关闭界面，滚回去
                    ObjectAnimator.ofFloat(rootView, "X", rootView.getX(), 0).setDuration(250).start();
                } else {                                // 关闭界面，滚出去
                    ObjectAnimator anim = ObjectAnimator.ofFloat(rootView, "X", rootView.getX(), util.info.getPhoneWidth());
                    anim.setDuration(250);
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mActivity.finish(false);
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
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * 计算 是否关闭界面
     *
     * @return 是否关闭界面
     */
    private boolean calculateIsFinish() {
        // 判断当前滑动有没有过屏幕的一半
        if (rootView.getX() < util.info.getPhoneWidth() / 2) {
            // 左半边
            if (slideSpeed < 0) {
                // 向右
                slideSpeed = Math.abs(slideSpeed);
                if (slideSpeed > 5) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // 向左
                return false;
            }
        } else {
            // 右半边
            if (slideSpeed < 0) {
                // 向右
                return true;
            } else {
                // 向左
                slideSpeed = Math.abs(slideSpeed);
                if (slideSpeed > 5) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

}
