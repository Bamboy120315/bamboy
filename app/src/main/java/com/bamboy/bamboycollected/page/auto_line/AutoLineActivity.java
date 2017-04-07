package com.bamboy.bamboycollected.page.auto_line;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.view.BamAutoLineList;

/**
 * 自动换行 Demo
 * <p>
 * Created by Bamboy on 2017/4/7.
 */
public class AutoLineActivity extends BamActivity implements View.OnClickListener {
    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;
    private ImageView iv_introduce;
    /**
     * 介绍容器
     */
    private RelativeLayout rl_introduce;
    private TextView tv_introduce;

    /**
     * 按钮【添加Item】
     */
    private Button btn_add;
    private BamAutoLineList bal_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_line);
    }

    @Override
    protected void findView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_introduce = (ImageView) findViewById(R.id.iv_introduce);

        rl_introduce = (RelativeLayout) findViewById(R.id.rl_introduce);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);

        btn_add = (Button) findViewById(R.id.btn_add);
        bal_list = (BamAutoLineList) findViewById(R.id.bal_list);
    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(this);
        iv_introduce.setOnClickListener(this);

        btn_add.setOnClickListener(this);
    }

    @Override
    protected void init() {
        // 设置titleBar
        setImmerseTitleBar(rl_title);

        tv_title.setText("自动换行 Demo");
        iv_introduce.setVisibility(View.VISIBLE);
        tv_introduce.setText(
                getString(R.string.introduce_auto_line) +
                        getString(R.string.introduce_foot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_introduce:
                if (rl_introduce.getVisibility() == View.GONE) {
                    showIntroduce(rl_introduce);
                } else {
                    hideIntroduce(rl_introduce);
                }
                break;
            case R.id.btn_add:
                // 实例化View
                View item = createView();
                // 把View放到控件里去
                bal_list.addView(item);
                break;
        }

    }

    /**
     * 按键监听
     *
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
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化一个布局
     *
     * @return
     */
    private View createView() {
        // 实例化一个View，以放到List里
        View item = getLayoutInflater().inflate(R.layout.item_auto_line, null);
        // 设置View里的文本值
        ((TextView) item.findViewById(R.id.tv_item)).setText(randomText());

        // 动画，如果需要可以打开，不需要则关闭
        if (true) {
            Animation anim = new ScaleAnimation(0, 1, 1, 1);
            anim.setDuration(300);
            item.setAnimation(anim);
        }

        return item;
    }

    /**
     * 产生随机字符串，
     * 为了让每个条目的文本不一样，
     * 正常开发不需要此方法
     *
     * @return 带有随机数的文本
     */
    private String randomText() {
        StringBuffer str = new StringBuffer("条目");

        int count = (int) (Math.random() * 4);
        for (int i = 0; i < count; i++) {
            str.append("" + (int) (Math.random() * 10));
        }
        return str.toString();
    }
}
