package com.bamboy.bamboycollected.page.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.adapter.RecyclerAdapter;
import com.bamboy.bamboycollected.adapter.RecylerCallback;
import com.bamboy.bamboycollected.adapter.ViewHolderManager;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.page.auto_line.AutoLineActivity;
import com.bamboy.bamboycollected.page.blur.BlurActivity;
import com.bamboy.bamboycollected.page.divide_load.DivideLoadActivity;
import com.bamboy.bamboycollected.page.toast.ToastActivity;
import com.bamboy.bamboycollected.views.BamToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * <p>
 * 动画显示技术点列表
 * 每个按钮进入一个技术点展示页面
 * <p>
 * Created by Bamboy on 2017/3/24.
 */
public class MainActivity extends BamActivity implements RecylerCallback {

    private List<SingleBtnBean> mList;
    private RecyclerAdapter mAdapter;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findView() {
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void init() {
        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);

        initList();
    }

    /**
     * 初始化List
     */
    private void initList() {
        mList = new ArrayList<>();

        mList.add(new SingleBtnBean(this, "Toast Demo", ToastActivity.class));
        mList.add(new SingleBtnBean(this, "高斯模糊 Demo", BlurActivity.class));
        mList.add(new SingleBtnBean(this, "自动换行 Demo", AutoLineActivity.class));
        mList.add(new SingleBtnBean(this, "分批加载 Demo", DivideLoadActivity.class));
        mList.add(new SingleBtnBean(this, "点击动画 Demo", null));

        mAdapter = new RecyclerAdapter(mList);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(mAdapter);
    }

    /**
     * 条目里的View的点击事件
     *
     * @param view     点击的View
     * @param position 列表的第N个条目
     * @param holder   所使用的的ViewHolder
     */
    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        SingleBtnBean bean = mList.get(position);
        switch (view.getId()) {
            case R.id.rl_single:
                if (bean.getStartClass() != null) {
                    Intent intent = new Intent(MainActivity.this, bean.getStartClass());
                    MainActivity.this.startActivity(intent);
                } else {
                    BamToast.show(MainActivity.this, "该Demo尚未整合\n请耐心等待", BamToast.COLOR_BLUE_SKY);
                }
                break;
        }
    }

    @Override
    public void finish() {
        // 如果动画正在执行，则不重新执行动画
        if (0 != rl_title.getY()) {
            return;
        }
        // 白色背景展示
        ObjectAnimator.ofFloat(rv_list, "Y", rv_list.getY(), rv_list.getY() + rv_list.getHeight()).setDuration(300).start();

        // titleBar展示
        ObjectAnimator anim = ObjectAnimator.ofFloat(rl_title, "Y", rl_title.getY(), 0 - rl_title.getHeight());
        anim.setDuration(300);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.super.finish(R.anim.act_shade_in, R.anim.act_shade_out);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        anim.start();
    }
}