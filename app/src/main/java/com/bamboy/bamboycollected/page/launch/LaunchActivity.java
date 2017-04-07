package com.bamboy.bamboycollected.page.launch;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;

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
    private LaunchUtil.LaunchCountTimer mCountTimer;

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

        LaunchUtil launchUtil = new LaunchUtil(this, rl_title, iv_icon, vi_back);

        // 模拟加载数据时间
        mCountTimer = launchUtil.gteCountTimet((int) (2.7 * 1000), 300);
        //mCountTimer = launchUtil.gteCountTimet((int) (0.9 * 1000), 300);

        iv_icon.post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics dm = new DisplayMetrics();
                // 获取屏幕信息
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                util.info.phoneWidth = dm.widthPixels;
                util.info.phoneHeigh = dm.heightPixels;
                util.info.phoneSDK = android.os.Build.VERSION.SDK_INT;
                mCountTimer.start();
                util.initBox();
            }
        });
    }
}
