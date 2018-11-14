package com.bamboy.bamboycollected.page.slidingshut;

import android.os.Bundle;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.slidingshut.baseactivity.BaseSlidingShutActivity;

/**
 * 即刻模式示例
 * <p>
 * Created by Bamboy on 2018/10/25.
 */
public class StartInstantlyActivity extends BaseSlidingShutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_instantly);

        // 关闭抬起模式，即切换为即刻模式
        setUpFinish(false);
    }
}
