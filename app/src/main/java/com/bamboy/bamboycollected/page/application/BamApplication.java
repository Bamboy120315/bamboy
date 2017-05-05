package com.bamboy.bamboycollected.page.application;

import android.app.Application;
import android.util.DisplayMetrics;

import com.bamboy.bamboycollected.utils.UtilBox;

/**
 * Created by liushaochen on 2017/5/4.
 */
public class BamApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化工具箱
        UtilBox.getBox().initBox();
    }

}
