package com.bamboy.bamboycollected.page.slidingshut;

import android.os.Bundle;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.slidingshut.baseactivity.BaseSlidingShutActivity;

/**
 * 抬起模式示例
 * <p>
 * Created by Bamboy on 2018/10/25.
 */
public class UpFinishActivity extends BaseSlidingShutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_finish);
    }
}
