package com.bamboy.bamboycollected.page.slidingshut;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.slidingshut.baseactivity.BaseSlidingShutActivity;

/**
 * 指定移动View示例
 * <p>
 * Created by Bamboy on 2018/10/25.
 */
public class AssignViewActivity extends BaseSlidingShutActivity {

    /**
     * Activity内容View
     * 【即TitleBar以下的内容】
     */
    private RelativeLayout rl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_view);

        rl_root = findViewById(R.id.rl_root);

        // 指定只移动TitleBar以下的内容
        setMoveView(rl_root);
    }

}
