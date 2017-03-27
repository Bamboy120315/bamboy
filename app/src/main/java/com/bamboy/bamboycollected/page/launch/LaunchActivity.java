package com.bamboy.bamboycollected.page.launch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamboyActivity;
import com.bamboy.bamboycollected.page.main.MainActivity;

public class LaunchActivity extends BamboyActivity {

    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;

    private View vi_back;
    private ImageView iv_icon;

    /**
     * 倒计时
     **/
    private MyCountDownTimer mcMSM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void findView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);

        vi_back = findViewById(R.id.vi_back);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {
        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);
        // 设置titleBar
        setImmerseTitleBar(rl_title);

        rl_title.setVisibility(View.INVISIBLE);
        iv_back.setVisibility(View.GONE);
        tv_title.setText("主页");

        mcMSM = new MyCountDownTimer((int) (2 * 1000), 300);

        iv_icon.post(new Runnable() {
            @Override
            public void run() {
                mcMSM.start();
                util.initBox();
            }
        });
    }

    /**
     * 开始动画
     */
    private void startAnim() {
        int[] location = new int[2];
        iv_icon.getLocationOnScreen(location);
        int width = iv_icon.getWidth();
        int height = iv_icon.getHeight();
        vi_back.setVisibility(View.VISIBLE);
        rl_title.setVisibility(View.VISIBLE);

        // icon降下
        ObjectAnimator.ofFloat(iv_icon, "Y", location[1], location[1] + height).setDuration(400).start();

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
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent, R.anim.bamboy_static, R.anim.bamboy_static);
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

    /**
     * 改变眼睛样式
     */
    private void changeEye() {
        if (iv_icon.getTag() == null) {
            iv_icon.setTag(false);
        }
        boolean open = (boolean) iv_icon.getTag();
        iv_icon.setImageResource(open ? R.drawable.ic_bamboy : R.drawable.icon_eye);
        iv_icon.setTag(!open);
    }

    class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            startAnim();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long sec = millisUntilFinished;
            changeEye();
        }
    }
}
