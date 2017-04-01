package com.bamboy.bamboycollected.page.blur;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;

public class BlurActivity extends BamActivity implements View.OnClickListener {

    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;
    private ImageView iv_introduce;
    /**
     * 介绍容器
     */
    private RelativeLayout rl_introduce;
    private ImageView iv_introduce_back;
    private TextView tv_introduce;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
    }

    @Override
    protected void findView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_introduce = (ImageView) findViewById(R.id.iv_introduce);

        rl_introduce = (RelativeLayout) findViewById(R.id.rl_introduce);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        iv_introduce_back = (ImageView) findViewById(R.id.iv_introduce_back);

        btn_blur_img = (Button) findViewById(R.id.btn_blur_img);
        iv_head_portrait = (ImageView) findViewById(R.id.iv_head_portrait);
        btn_blur_popup_window = (Button) findViewById(R.id.btn_blur_popup_window);

        iv_popup_window_back = (ImageView) findViewById(R.id.iv_popup_window_back);
        btn_close_popup_window = (Button) findViewById(R.id.btn_close_popup_window);
        rl_popup_window = (RelativeLayout) findViewById(R.id.rl_popup_window);
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(this);
        iv_introduce.setOnClickListener(this);
        iv_introduce.setOnClickListener(this);
        btn_blur_img.setOnClickListener(this);
        btn_blur_popup_window.setOnClickListener(this);
        btn_close_popup_window.setOnClickListener(this);
        iv_popup_window_back.setOnClickListener(this);
    }

    @Override
    protected void init() {
        // 设置titleBar
        setImmerseTitleBar(rl_title);

        tv_title.setText("高斯模糊 Demo");
        iv_introduce.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_introduce:
                if (rl_introduce.getVisibility() == View.GONE) {
                    showIntroduce(rl_introduce, iv_introduce_back);
                } else {
                    hideIntroduce(rl_introduce);
                }
                break;
            case R.id.btn_blur_img:          // 点击模糊图片
                clickBlurImg();
                break;
            case R.id.btn_blur_popup_window: // 点击显示弹窗
                clickPopupWindow();
                break;
            case R.id.iv_popup_window_back: // 弹窗背景
            case R.id.btn_close_popup_window: // 关闭弹窗
                clickClosePopupWindow();
                break;
        }
    }

    /**
     * 按键监听
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (rl_introduce.getVisibility() == View.VISIBLE) {
                hideIntroduce(rl_introduce);
                return false;
            }
            if (rl_popup_window.getVisibility() == View.VISIBLE) {
                clickClosePopupWindow();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 模糊图片
     */
    private void clickBlurImg() {
        // 将图片进行高斯模糊，
        // 最后一个参数是模糊等级，值为 0~25
        util.bitmap.blurImageView(this, iv_head_portrait, 5);
    }

    /**
     * 显示弹窗
     */
    private void clickPopupWindow() {
        // 获取截图的Bitmap
        Bitmap bitmap = util.ui.getDrawing(this);

        if (bitmap != null) {
            // 将截屏Bitma放入ImageView
            iv_popup_window_back.setImageBitmap(bitmap);
            // 将ImageView进行高斯模糊【25是最高模糊等级】【最后一个参数是蒙上一层颜色，此参数可不填】
            util.bitmap.blurImageView(this, iv_popup_window_back, 25, getColor(R.color.colorWhite_t));
        } else {
            // 获取的Bitmap为null时，用半透明代替
            iv_popup_window_back.setBackgroundColor(getColor(R.color.colorWhite_t));
        }

        // 打开弹窗
        util.anim.showPopupWindow(rl_popup_window, iv_popup_window_back);

    }

    /**
     * 关闭弹窗
     */
    private void clickClosePopupWindow() {
        util.anim.hidePopupWindow(rl_popup_window, iv_popup_window_back);
    }
}
