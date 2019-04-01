package com.bamboy.bamboycollected.page.progress;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.page.progress.progress.BaseProgress;
import com.bamboy.bamboycollected.page.progress.progress.NounProgressBar;
import com.bamboy.bamboycollected.page.progress.progress.OnProgressListener;

public class ActNoun extends BamActivity {
    /**
     * 节点进度条
     */
    private NounProgressBar noun_progress;
    /**
     * 进度条监听指示文字
     */
    private TextView tv_value;
    /**
     * 直接控制进度条的SeekBar
     */
    private SeekBar sb_progress;
    /**
     * 控制进度条【后退】的按钮
     */
    private Button btn_retreat;
    /**
     * 控制进度条【前进】的按钮
     */
    private Button btn_advance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_noun);
    }

    @Override
    protected void findView() {
        noun_progress = findViewById(R.id.noun_progress);
        tv_value = findViewById(R.id.tv_value);
        sb_progress = findViewById(R.id.sb_progress);
        btn_retreat = findViewById(R.id.btn_retreat);
        btn_advance = findViewById(R.id.btn_advance);
    }

    @Override
    protected void setListener() {
        // 设置进度条的监听器
        noun_progress.setProgressListener(new OnProgressListener() {
            @Override
            public void onProgress(BaseProgress progressBar, int progress) {
                // 显示进度
                tv_value.setText("监听：" + progress + "/" + progressBar.getProgressMax());
            }
        });

        // 滑动监听
        sb_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // 设置进度
                noun_progress.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // 后退按钮
        btn_retreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noun_progress.getProgress() > 0) {
                    // 利用属性动画控制进度条后退
                    ObjectAnimator.ofFloat(
                            noun_progress,
                            "progress",
                            noun_progress.getProgress(),
                            noun_progress.getProgress() - 29)
                            .setDuration(250)
                            .start();
                }
            }
        });

        // 前进按钮
        btn_advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noun_progress.getProgress() < noun_progress.getProgressMax()) {
                    // 利用属性动画控制进度条前进
                    ObjectAnimator
                            .ofFloat(
                                    noun_progress,
                                    "progress",
                                    noun_progress.getProgress(),
                                    noun_progress.getProgress() + 29)
                            .setDuration(250)
                            .start();
                }
            }
        });
    }

    @Override
    protected void init() {
        tv_title.setText("节点进度条Demo");
        iv_introduce.setVisibility(View.GONE);
    }
}
