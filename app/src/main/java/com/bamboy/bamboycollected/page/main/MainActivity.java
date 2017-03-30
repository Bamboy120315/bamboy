package com.bamboy.bamboycollected.page.main;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.page.toast.ToastActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 *
 * 动画显示技术点列表
 * 每个按钮进入一个技术点展示页面
 *
 * Created by Bamboy on 2017/3/24.
 */
public class MainActivity extends BamActivity {

    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;
    private List<MainBean> mList;
    private MainAdapter mAdapter;
    private RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void findView() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
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

        iv_back.setVisibility(View.GONE);
        tv_title.setText("主页");
        initList();
    }

    /**
     * 初始化List
     */
    private void initList() {
        mList = new ArrayList<MainBean>();

        mList.add(new MainBean("Toast Demo", ToastActivity.class));
        mList.add(new MainBean("自动换行 Demo", null));
        mList.add(new MainBean("分批加载 Demo", null));
        mList.add(new MainBean("高斯模糊 Demo", null));
        mList.add(new MainBean("点击动画 Demo", null));

        mAdapter = new MainAdapter(this, mList);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(mAdapter);
    }

    @Override
    public void finish() {
        super.finish(false);
    }
}
