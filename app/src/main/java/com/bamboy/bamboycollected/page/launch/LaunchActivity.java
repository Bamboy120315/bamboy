package com.bamboy.bamboycollected.page.launch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.page.main.MainActivity;

/**
 * 启动页
 * <p>
 * 利用透明主题，
 * 只显示Logo在眨眼，
 * 然后等数据加载完成后，
 * 利用动画过渡成主页的样子，
 * 最后利用无动画完成与主页的无感知跳转
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class LaunchActivity extends BamActivity {

    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;

    private View vi_back;
    private ImageView iv_icon;
    private ImageView iv_introduce;

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
        iv_introduce = (ImageView) findViewById(R.id.iv_introduce);

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
        iv_back.setVisibility(View.INVISIBLE);
        iv_introduce.setVisibility(View.VISIBLE);
        tv_title.setText("主页");

        // 模拟加载数据时间
        mcMSM = new MyCountDownTimer((int) (2.7 * 1000), 300);
        //mcMSM = new MyCountDownTimer((int) (0.9 * 1000), 300);

        iv_icon.post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics dm = new DisplayMetrics();
                // 获取屏幕信息
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                util.info.phoneWidth = dm.widthPixels;
                util.info.phoneHeigh = dm.heightPixels;
                util.info.phoneSDK = android.os.Build.VERSION.SDK_INT;
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
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent, R.anim.act_shade_in, R.anim.act_shade_out);
                finish(R.anim.act_static, R.anim.act_static);
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
    private void changeEye(long countTimer) {
        if (iv_icon.getTag() == null) {
            iv_icon.setTag(false);
            return;
        }
        boolean open = (boolean) iv_icon.getTag();
        iv_icon.setTag(!open);

        // 上下幅度为屏幕的1/6
        float value = util.info.phoneHeigh / 6.5f;
        // 获取当前高度
        float y = iv_icon.getY();

        if (open) {
            // 降落
            iv_icon.setImageResource(R.drawable.ic_bamboy);
            ObjectAnimator.ofFloat(iv_icon, "Y", y, y + value).setDuration(countTimer).start();
        } else {
            // 起跳
            if (y > util.info.phoneHeigh * 0.45){
                // 若是点钱高度低于屏幕一半，增加跳动幅度
                value = value * 1.6f;
            }
            iv_icon.setImageResource(R.drawable.icon_eye);
            ObjectAnimator.ofFloat(iv_icon, "Y", y, y - value).setDuration(countTimer).start();
        }
    }

    class MyCountDownTimer extends CountDownTimer {

        long countTimer;

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
            countTimer = countDownInterval;
        }

        @Override
        public void onFinish() {
            startAnim();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long sec = millisUntilFinished;
            changeEye(countTimer);
        }
    }
}
