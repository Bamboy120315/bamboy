package com.bamboy.bamboycollected.page.auto_line;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;

/**
 * 自动换行 Demo
 * <p>
 * Created by Bamboy on 2017/4/7.
 */
public class AutoLineActivity extends BamActivity implements View.OnClickListener {
    /**
     * 按钮【添加Item】
     */
    private Button btn_add;
    /**
     * 自动换行容器
     */
    private BamAutoLineList bal_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_line);
    }

    @Override
    protected void findView() {
        btn_add = findViewById(R.id.btn_add);
        bal_list = findViewById(R.id.bal_list);
    }

    @Override
    protected void setListener() {
        btn_add.setOnClickListener(this);
    }

    @Override
    protected void init() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                // 实例化View
                View item = createView();
                // 把View放到控件里去
                bal_list.addView(item);
                break;
        }

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
        ((TextView)item.findViewById(R.id.tv_item)).setText(randomText());

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
