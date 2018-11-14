package com.bamboy.bamboycollected.page.blur;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Button;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;

public class BlurActivity extends BamActivity implements View.OnClickListener {
    /**
     * 按钮【模糊图片】
     */
    private Button btn_blur_img;
    /**
     * 图片
     */
    private ImageView iv_head_portrait;
    /**
     * 按钮【显示弹窗】
     */
    private Button btn_blur_popup_window;

    /**
     * 弹窗背景
     */
    private ImageView iv_popup_window_back;
    /**
     * 弹窗容器
     */
    private RelativeLayout rl_popup_window;
    /**
     * 按钮【关闭弹窗】
     */
    private Button btn_close_popup_window;

    /**
     * 本页工具类
     */
    private BlurUtil blurUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
    }

    @Override
    protected void findView() {
        btn_blur_img = findViewById(R.id.btn_blur_img);
        iv_head_portrait = findViewById(R.id.iv_head_portrait);
        btn_blur_popup_window = findViewById(R.id.btn_blur_popup_window);

        iv_popup_window_back = findViewById(R.id.iv_popup_window_back);
        btn_close_popup_window = findViewById(R.id.btn_close_popup_window);
        rl_popup_window = findViewById(R.id.rl_popup_window);
    }

    @Override
    protected void setListener() {
        btn_blur_img.setOnClickListener(this);
        btn_blur_popup_window.setOnClickListener(this);
        btn_close_popup_window.setOnClickListener(this);
        iv_popup_window_back.setOnClickListener(this);
    }

    @Override
    protected void init() {
        blurUtil = new BlurUtil(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_blur_img:          // 点击模糊图片
                blurUtil.clickBlurImg(iv_head_portrait);
                break;
            case R.id.btn_blur_popup_window: // 点击显示弹窗
                blurUtil.clickPopupWindow(rl_popup_window, iv_popup_window_back);
                break;
            case R.id.iv_popup_window_back: // 弹窗背景
            case R.id.btn_close_popup_window: // 关闭弹窗
                blurUtil.clickClosePopupWindow(rl_popup_window, iv_popup_window_back);
                break;
        }
    }

    @Override
    public void finish() {
        if (rl_popup_window.getVisibility() == View.VISIBLE) {
            blurUtil.clickClosePopupWindow(rl_popup_window, iv_popup_window_back);
            return;
        }

        super.finish();
    }
}
