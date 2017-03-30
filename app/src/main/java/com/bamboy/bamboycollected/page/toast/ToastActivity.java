package com.bamboy.bamboycollected.page.toast;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.view.BamToast;

/**
 * 自定义Toast
 * <p>
 * Created by Bamboy on 2017/3/29.
 */
public class ToastActivity extends BamActivity implements View.OnClickListener {


    private RelativeLayout rl_title;
    private ImageView iv_back;
    private ImageView iv_introduce;
    private TextView tv_title;
    /**
     * 介绍容器
     */
    private ScrollView sv_introduce;
    private TextView tv_introduce;

    /**
     * 控制条 A
     */
    private SeekBar sb_color_a;
    private TextView tv_color_value_a;
    /**
     * 控制条 R
     */
    private SeekBar sb_color_r;
    private TextView tv_color_value_r;
    /**
     * 控制条 G
     */
    private SeekBar sb_color_g;
    private TextView tv_color_value_g;
    /**
     * 控制条 B
     */
    private SeekBar sb_color_b;
    private TextView tv_color_value_b;

    /**
     * 显示自定义Toast按钮
     */
    private Button btn_toast_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    @Override
    protected void findView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_introduce = (ImageView) findViewById(R.id.iv_introduce);
        tv_title = (TextView) findViewById(R.id.tv_title);
        sv_introduce = (ScrollView) findViewById(R.id.sv_introduce);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);

        sb_color_a = (SeekBar) findViewById(R.id.sb_color_a);
        tv_color_value_a = (TextView) findViewById(R.id.tv_color_value_a);

        sb_color_r = (SeekBar) findViewById(R.id.sb_color_r);
        tv_color_value_r = (TextView) findViewById(R.id.tv_color_value_r);

        sb_color_g = (SeekBar) findViewById(R.id.sb_color_g);
        tv_color_value_g = (TextView) findViewById(R.id.tv_color_value_g);

        sb_color_b = (SeekBar) findViewById(R.id.sb_color_b);
        tv_color_value_b = (TextView) findViewById(R.id.tv_color_value_b);

        btn_toast_custom = (Button) findViewById(R.id.btn_toast_custom);
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(this);
        iv_introduce.setOnClickListener(this);
        findViewById(R.id.btn_toast_red).setOnClickListener(this);
        findViewById(R.id.btn_toast_blue).setOnClickListener(this);
        findViewById(R.id.btn_toast_green).setOnClickListener(this);
        findViewById(R.id.btn_toast_black).setOnClickListener(this);
        findViewById(R.id.btn_toast_custom).setOnClickListener(this);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView) seekBar.getTag()).setText("" + progress);
                setBtnColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        sb_color_a.setTag(tv_color_value_a);
        sb_color_r.setTag(tv_color_value_r);
        sb_color_g.setTag(tv_color_value_g);
        sb_color_b.setTag(tv_color_value_b);

        sb_color_a.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_r.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_g.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_b.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    @Override
    protected void init() {
        // 设置titleBar
        setImmerseTitleBar(rl_title);
        tv_title.setText("Toast Demo");
        iv_introduce.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_introduce:
                if (sv_introduce.getVisibility() == View.GONE){
                    showIntroduce();
                } else {
                    hideIntroduce();
                }
                break;
            case R.id.btn_toast_red:
                BamToast.show(this, "红色Toast", BamToast.COLOR_RED);
                break;
            case R.id.btn_toast_blue:
                BamToast.show(this, "蓝色Toast", BamToast.COLOR_BLUE);
                break;
            case R.id.btn_toast_green:
                BamToast.show(this, "绿色Toast", BamToast.COLOR_GREEN);
                break;
            case R.id.btn_toast_black:
                BamToast.show(this, "黑色Toast");
                break;
            case R.id.btn_toast_custom:
                showColorToast();
                break;
        }
    }

    /**
     * 设置按钮颜色
     */
    private void setBtnColor() {
        int a = Integer.parseInt(tv_color_value_a.getText().toString());
        int r = Integer.parseInt(tv_color_value_r.getText().toString());
        int g = Integer.parseInt(tv_color_value_g.getText().toString());
        int b = Integer.parseInt(tv_color_value_b.getText().toString());

        int color = Color.argb(a, r, g, b);
        btn_toast_custom.setBackgroundColor(color);
    }

    /**
     * 显示自定义颜色Toast
     */
    private void showColorToast() {
        int a = Integer.parseInt(tv_color_value_a.getText().toString());
        int r = Integer.parseInt(tv_color_value_r.getText().toString());
        int g = Integer.parseInt(tv_color_value_g.getText().toString());
        int b = Integer.parseInt(tv_color_value_b.getText().toString());

        int color = Color.argb(a, r, g, b);
        BamToast.show(this, "自定义颜色Toast", color);

        util.log.i("-=-=-=" + rl_title.getHeight());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (sv_introduce.getVisibility() == View.VISIBLE){
                hideIntroduce();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 展开介绍
     */
    private void showIntroduce() {
        sv_introduce.setAlpha(0);
        sv_introduce.setVisibility(View.VISIBLE);
        sv_introduce.post(new Runnable() {
            @Override
            public void run() {
                float maxRadius = (float) Math.sqrt(sv_introduce.getWidth() * sv_introduce.getWidth() + sv_introduce.getHeight() * sv_introduce.getHeight());
                sv_introduce.setAlpha(1);
                ViewAnimationUtils.createCircularReveal(sv_introduce, util.info.phoneWidth, 0, 0f, maxRadius).setDuration(300).start();
            }
        });
    }

    /**
     * 关闭介绍
     */
    private void hideIntroduce() {
        float maxRadius = (float) Math.sqrt(sv_introduce.getWidth() * sv_introduce.getWidth() + sv_introduce.getHeight() * sv_introduce.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(sv_introduce, util.info.phoneWidth, 0, maxRadius, 0f);
        anim.setDuration(300);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                sv_introduce.setVisibility(View.GONE);
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

}
