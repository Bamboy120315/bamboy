package com.bamboy.bamboycollected.page.toast;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.page.toast.bamtoast.BamToast;

/**
 * 自定义Toast
 * <p>
 * Created by Bamboy on 2017/3/29.
 */
public class ToastActivity extends BamActivity implements View.OnClickListener {

    /**
     * 显示纯文本Toast
     */
    private Button tv_text;
    /**
     * 显示文字+对号Toast
     */
    private Button tv_text_y;
    /**
     * 显示文字+叉号Toast
     */
    private Button tv_text_n;
    /**
     * 显示长时间纯文字Toast
     */
    private Button tv_text_l;
    /**
     * 显示长时间文字+对号Toast
     */
    private Button tv_text_y_l;
    /**
     * 显示长时间文字+叉号Toast
     */
    private Button tv_text_n_l;

    /**
     * 【开启/关闭】通知权限
     */
    private Button tv_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    @Override
    protected void findView() {
        tv_text = findViewById(R.id.tv_text);
        tv_text_y = findViewById(R.id.tv_text_y);
        tv_text_n = findViewById(R.id.tv_text_n);
        tv_text_l = findViewById(R.id.tv_text_l);
        tv_text_y_l = findViewById(R.id.tv_text_y_l);
        tv_text_n_l = findViewById(R.id.tv_text_n_l);

        tv_notification = findViewById(R.id.tv_notification);
    }

    @Override
    protected void setListener() {
        tv_text.setOnClickListener(this);
        tv_text_y.setOnClickListener(this);
        tv_text_n.setOnClickListener(this);
        tv_text_l.setOnClickListener(this);
        tv_text_y_l.setOnClickListener(this);
        tv_text_n_l.setOnClickListener(this);

        tv_notification.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 显示纯文本Toast
            case R.id.tv_text:
                BamToast.showText(this, "这是纯文本Toast");
                break;

            // 显示文字+对号Toast
            case R.id.tv_text_y:
                BamToast.showText(this, "这是带对号Toast", true);
                break;

            // 显示文字+叉号Toast
            case R.id.tv_text_n:
                BamToast.showText(this, "这是带叉号Toast", false);
                break;

            // 显示长时间纯文字Toast
            case R.id.tv_text_l:
                BamToast.showText(this, "这是纯文本 长时间Toast", Toast.LENGTH_LONG);
                break;

            // 显示长时间文字+对号Toast
            case R.id.tv_text_y_l:
                BamToast.showText(this, "这是带对号 长时间Toast", Toast.LENGTH_LONG, true);
                break;

            // 显示长时间文字+叉号Toast
            case R.id.tv_text_n_l:
                BamToast.showText(this, "这是带叉号 长时间Toast", Toast.LENGTH_LONG, false);
                break;

            // 【开启/关闭】通知权限
            case R.id.tv_notification:
                // 跳转【通知管理页面】
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    localIntent.setAction(Intent.ACTION_VIEW);
                    localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                    localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
                }
                startActivity(localIntent);
                break;
        }
    }

}
