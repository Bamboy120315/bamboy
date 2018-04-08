package com.bamboy.bamboycollected.page.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.base.freedom.FreedomAdapter;
import com.bamboy.bamboycollected.base.freedom.FreedomCallback;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.page.anim_click.AnimClickActivity;
import com.bamboy.bamboycollected.page.auto_line.AutoLineActivity;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;
import com.bamboy.bamboycollected.page.blur.BlurActivity;
import com.bamboy.bamboycollected.page.divide_load.DivideLoadActivity;
import com.bamboy.bamboycollected.page.freedom.FreedomListActivity;
import com.bamboy.bamboycollected.page.noun_progress.NounProgressActivity;
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
public class MainActivity extends BamActivity implements FreedomCallback {

    private List<SingleBtnBean> mList;
    private FreedomAdapter mAdapter;
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

        // 初始化手机信息
        utils.info.initPhoneInfo(this);
        // 初始化3DPress
        initFlyme3DPress();

        // 关闭当前界面的右滑关闭功能
        openSlideFinish(false);

        initList();
    }

    /**
     * 初始化List
     */
    private void initList() {
        mList = new ArrayList<>();

        mList.add(new SingleBtnBean("★★★ 非约束列表 Demo  ", FreedomListActivity.class));
        mList.add(new SingleBtnBean("【New】节点进度条 Demo", NounProgressActivity.class));
        mList.add(new SingleBtnBean("分批加载 Demo", DivideLoadActivity.class));
        mList.add(new SingleBtnBean("高斯模糊 Demo", BlurActivity.class));
        mList.add(new SingleBtnBean("点击动画 Demo", AnimClickActivity.class));
        mList.add(new SingleBtnBean("Toast Demo", ToastActivity.class));
        mList.add(new SingleBtnBean("自动换行 Demo", AutoLineActivity.class));

        mAdapter = new FreedomAdapter(this, mList);
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

    /**
     * 初始化3DPress
     */
    private void initFlyme3DPress() {
        Intent intent = getIntent();
        if (intent != null) {
            Uri data = intent.getData();
            if (data != null && TextUtils.equals("flyme_3dtouch", data.getScheme())) {
                // 进入到这里，
                // 说明是通过3D Press进来的，
                // 所以把主页的列表动画取消，
                // 以免浪费性能。
                rv_list.setLayoutAnimation(null);

                // 判断是通过桌面菜单的哪一项进来的
                if (TextUtils.equals("/freemod", data.getPath())) {
                    startActivity(new Intent(this, FreedomListActivity.class));
                    return;

                } else if (TextUtils.equals("/blus", data.getPath())) {
                    startActivity(new Intent(this, BlurActivity.class));
                    return;

                } else if (TextUtils.equals("/toast", data.getPath())) {
                    startActivity(new Intent(this, ToastActivity.class));
                    return;

                } else if (TextUtils.equals("/click", data.getPath())) {
                    startActivity(new Intent(this, AnimClickActivity.class));
                    return;
                }
            }
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