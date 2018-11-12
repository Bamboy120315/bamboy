package com.bamboy.bamboycollected.page.toast;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.views.BamToast;

/**
 * 自定义Toast
 * <p>
 * Created by Bamboy on 2017/3/29.
 */
public class ToastActivity extends BamActivity implements View.OnClickListener {
    /**
     * 介绍容器
     */
    private RelativeLayout rl_introduce;
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

    /**
     * 显示自定义Toast按钮
     */
//    private Button btn_jianbian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    @Override
    protected void findView() {
        sb_color_a = (SeekBar) findViewById(R.id.sb_color_a);
        tv_color_value_a = (TextView) findViewById(R.id.tv_color_value_a);

        sb_color_r = (SeekBar) findViewById(R.id.sb_color_r);
        tv_color_value_r = (TextView) findViewById(R.id.tv_color_value_r);

        sb_color_g = (SeekBar) findViewById(R.id.sb_color_g);
        tv_color_value_g = (TextView) findViewById(R.id.tv_color_value_g);

        sb_color_b = (SeekBar) findViewById(R.id.sb_color_b);
        tv_color_value_b = (TextView) findViewById(R.id.tv_color_value_b);

        btn_toast_custom = (Button) findViewById(R.id.btn_toast_custom);

        /*btn_jianbian = (Button) findViewById(R.id.btn_jianbian);
        btn_jianbian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startColor = Color.argb(255, 0, 102, 204);
                int endColor = Color.argb(255, 244, 164, 96);

                ValueAnimator vAnim = ValueAnimator.ofInt(startColor, endColor);
                vAnim.setRepeatCount(ValueAnimator.INFINITE);
                vAnim.setDuration(2000);
                vAnim.setEvaluator(new ArgbEvaluator());
                vAnim.setRepeatMode(ValueAnimator.REVERSE);
                vAnim.setInterpolator(new LinearInterpolator());
                vAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int color = (int) animation.getAnimatedValue();
                        int r = Color.red(color);
                        int g = Color.green(color);
                        int b = Color.blue(color);
                        btn_jianbian.setBackgroundColor(color);
                        btn_jianbian.setText("色值：r:" + r + " g:" + g + " b:" + b);
                    }
                });
                vAnim.start();
            }
        });*/
    }

    @Override
    protected void setListener() {
        findViewById(R.id.btn_toast_red).setOnClickListener(this);
        findViewById(R.id.btn_toast_blue).setOnClickListener(this);
        findViewById(R.id.btn_toast_green).setOnClickListener(this);
        findViewById(R.id.btn_toast_black).setOnClickListener(this);
        findViewById(R.id.btn_toast_custom).setOnClickListener(this);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView) seekBar.getTag()).setText("" + progress);
                btn_toast_custom.setBackgroundColor(getColor());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        sb_color_a.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_r.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_g.setOnSeekBarChangeListener(seekBarChangeListener);
        sb_color_b.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    @Override
    protected void init() {
        sb_color_a.setTag(tv_color_value_a);
        sb_color_r.setTag(tv_color_value_r);
        sb_color_g.setTag(tv_color_value_g);
        sb_color_b.setTag(tv_color_value_b);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                BamToast.show(this, "自定义颜色Toast", getColor());
                break;
        }
    }

    /**
     * 获取颜色值
     *
     * @return
     */
    private int getColor() {
        int a = Integer.parseInt(tv_color_value_a.getText().toString());
        int r = Integer.parseInt(tv_color_value_r.getText().toString());
        int g = Integer.parseInt(tv_color_value_g.getText().toString());
        int b = Integer.parseInt(tv_color_value_b.getText().toString());

        return Color.argb(a, r, g, b);
    }

}
