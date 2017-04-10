package com.bamboy.bamboycollected.page.launch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.main.MainActivity;

/**
 * 启动页工具类
 *
 * 用于获取计时器与动画执行
 *
 * Created by Bamboy on 2017/4/7.
 */
public class LaunchUtil {

    private LaunchActivity mActivity;
    /**
     * 头部容器
     */
    private RelativeLayout rl_title;
    /**
     * 跳动的Icon
     */
    private ImageView iv_icon;
    /**
     * 界面背景
     */
    private View vi_back;

    /**
     * 启动界面工具类构造
     *
     * @param activity 上下文
     * @param rl_title 头部容器
     * @param iv_icon 跳动的Icon
     * @param vi_back 界面背景
     */
    public LaunchUtil(LaunchActivity activity, RelativeLayout rl_title, ImageView iv_icon, View vi_back){
        this.mActivity = activity;
        this.rl_title = rl_title;
        this.iv_icon = iv_icon;
        this.vi_back = vi_back;
    }

    /**
     * 获取计时器
     *
     * @param millisInFuture    表示以毫秒为单位 倒计时的总数
     *                          <p>
     *                          例如 millisInFuture=1000 表示1秒
     * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
     *                          <p>
     *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
     * @return 计时器
     */
    public LaunchCountTimer gteCountTimet(long millisInFuture, long countDownInterval){
        return new LaunchCountTimer((long) (2.7 * 1000), 300l);
    }

    /**
     * 开始动画
     */
    private void startAnim() {
        int[] location = new int[2];
        iv_icon.getLocationOnScreen(location);

        vi_back.setVisibility(View.VISIBLE);
        rl_title.setVisibility(View.VISIBLE);

        // icon降下
        iv_icon.setImageResource(R.drawable.ic_bamboy);
        ObjectAnimator.ofFloat(iv_icon, "Y", location[1], location[1] + iv_icon.getY()).setDuration(400).start();

        // 白色背景展示
        ObjectAnimator.ofFloat(vi_back, "Y", vi_back.getHeight(), 0).setDuration(400).start();

        // titleBar展示
        ObjectAnimator anim = ObjectAnimator.ofFloat(rl_title, "Y", 0 - rl_title.getHeight(), 0);
        anim.setDuration(400);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(intent, R.anim.act_shade_in, R.anim.act_shade_out);
                mActivity.finish(R.anim.act_static, R.anim.act_static);
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

    /**
     * 改变眼睛样式
     */
    private void changeEye(long stageTimer) {
        if (iv_icon.getTag() == null) {
            iv_icon.setTag(false);
            return;
        }
        boolean open = (boolean) iv_icon.getTag();
        iv_icon.setTag(!open);

        // 上下幅度为屏幕的1/6
        float value = mActivity.utils.info.phoneHeigh / 6.5f;
        // 获取当前高度
        float y = iv_icon.getY();

        if (open) {
            // 降落
            iv_icon.setImageResource(R.drawable.ic_bamboy);
            ObjectAnimator.ofFloat(iv_icon, "Y", y, y + value).setDuration(stageTimer).start();
        } else {
            // 起跳
            if (y > mActivity.utils.info.phoneHeigh * 0.45){
                // 若是点钱高度低于屏幕一半，增加跳动幅度
                value = value * 1.6f;
            }
            iv_icon.setImageResource(R.drawable.icon_eye);
            ObjectAnimator.ofFloat(iv_icon, "Y", y, y - value).setDuration(stageTimer).start();
        }
    }

    /**
     * 计时器
     */
    class LaunchCountTimer extends CountDownTimer {

        /**
         * 周期时间
         */
        long stageTimer;

        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public LaunchCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            stageTimer = countDownInterval;
        }

        @Override
        public void onFinish() {
            startAnim();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            changeEye(stageTimer);
        }
    }
}
