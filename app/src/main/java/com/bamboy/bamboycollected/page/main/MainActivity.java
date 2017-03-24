package com.bamboy.bamboycollected.page.main;

import android.os.Bundle;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamboyActivity;

public class MainActivity extends BamboyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);
    }

    @Override
    protected void findView() {
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void init() {
    }
}
