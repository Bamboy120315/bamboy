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
import com.bamboy.bamboycollected.base.BamboyActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BamboyActivity {

    private RelativeLayout rl_title;
    private ImageView iv_back;
    private TextView tv_title;
    private List<BeanMain> mList;
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
        mList = new ArrayList<BeanMain>();

        mList.add(new BeanMain("Text 1", null));
        mList.add(new BeanMain("Text 2", null));
        mList.add(new BeanMain("Text 3", null));
        mList.add(new BeanMain("Text 4", null));
        mList.add(new BeanMain("Text 5", null));
        mList.add(new BeanMain("Text 6", null));

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
