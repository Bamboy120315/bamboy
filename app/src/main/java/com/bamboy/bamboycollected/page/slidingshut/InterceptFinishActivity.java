package com.bamboy.bamboycollected.page.slidingshut;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.slidingshut.baseactivity.BaseSlidingShutActivity;

/**
 * 拦截finish()情况示例
 * <p>
 * Created by Bamboy on 2018/11/5.
 */
public class InterceptFinishActivity extends BaseSlidingShutActivity {

    /**
     * 菜单背景蒙层
     */
    private View view_background;
    /**
     * 菜单
     */
    private TextView tv_menu;
    /**
     * 打开菜单按钮
     */
    private Button btn_open_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept_finish);

        view_background = findViewById(R.id.view_background);
        tv_menu = findViewById(R.id.tv_menu);
        btn_open_menu = findViewById(R.id.btn_open_menu);

        // 打开菜单按钮点击事件
        btn_open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleMenu();
            }
        });

        // 展开菜单
        openMenu();
    }

    /**
     * 切换菜单【展开/收起】状态
     */
    private void toggleMenu() {
        if (tv_menu.getVisibility() == View.GONE)
            openMenu();
        else if (tv_menu.getTranslationY() == 0)
            closeMenu();
    }

    /**
     * 展开菜单
     */
    private void openMenu() {
        if (tv_menu.getVisibility() != View.GONE)
            return;

        tv_menu.setAlpha(0);
        tv_menu.setVisibility(View.VISIBLE);
        view_background.setAlpha(0);
        view_background.setVisibility(View.VISIBLE);
        tv_menu.post(new Runnable() {
            @Override
            public void run() {

                int height = tv_menu.getHeight();
                float btnTranslationY = btn_open_menu.getTranslationY();

                tv_menu.setAlpha(1);
                ObjectAnimator.ofFloat(view_background, "alpha", 0, 0.7f).setDuration(300).start();
                ObjectAnimator.ofFloat(tv_menu, "translationY", 0 - height, 0).setDuration(300).start();
                ObjectAnimator.ofFloat(btn_open_menu, "translationY", btnTranslationY, btnTranslationY + height).setDuration(300).start();
            }
        });
    }

    /**
     * 收起菜单
     */
    private void closeMenu() {
        if (tv_menu.getTranslationY() != 0)
            return;

        int height = tv_menu.getHeight();
        float btnTranslationY = btn_open_menu.getTranslationY();

        ObjectAnimator.ofFloat(view_background, "alpha", 0.7f, 0).setDuration(300).start();
        ObjectAnimator anim = ObjectAnimator.ofFloat(tv_menu, "translationY", 0, 0 - height);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                tv_menu.setVisibility(View.GONE);
            }
        });
        anim.setDuration(300);
        anim.start();
        ObjectAnimator.ofFloat(btn_open_menu, "translationY", btnTranslationY, btnTranslationY - height).setDuration(300).start();
    }

    @Override
    public void finish() {
        if (tv_menu.getVisibility() == View.VISIBLE && tv_menu.getTranslationY() == 0) {
            closeMenu();
            return;
        }
        super.finish();
    }
}
