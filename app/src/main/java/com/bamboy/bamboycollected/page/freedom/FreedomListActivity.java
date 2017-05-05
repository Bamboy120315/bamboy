package com.bamboy.bamboycollected.page.freedom;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.bean.FourIconBean;
import com.bamboy.bamboycollected.page.bean.SingleImageBean;
import com.bamboy.bamboycollected.views.BamToast;
import com.bamboy.bamboycollected.base.freedom.FreedomAdapter;
import com.bamboy.bamboycollected.base.freedom.listener.FreedomCallback;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.page.bean.SingleBtnBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 非约束列表Demo
 * <p>
 * Created by Bamboy on 2017/4/17.
 */
public class FreedomListActivity extends BamActivity implements FreedomCallback {

    /**
     * 数据源
     */
    private List mList;
    /**
     * 适配器
     */
    private FreedomAdapter mAdapter;
    /**
     * RecyclerView
     */
    private RecyclerView rv_divide_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedom);
    }

    @Override
    protected void findView() {
        rv_divide_load = (RecyclerView) findViewById(R.id.rv_freedom);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {
        // 实例化List
        mList = new ArrayList();

        // 加载数据
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));
        mList.add(new SingleImageBean(R.drawable.picture));
        mList.add(new FourIconBean());
        mList.add(new SingleBtnBean("条目样式 1", null));

        // 实例化RecyclerView
        mAdapter = new FreedomAdapter(this, mList);
        rv_divide_load.setLayoutManager(new LinearLayoutManager(this));
        rv_divide_load.setItemAnimator(new DefaultItemAnimator());
        rv_divide_load.setAdapter(mAdapter);
    }

    @Override
    public void onClickCallback(View view, int position, ViewHolderManager.ViewHolder holder) {
        switch (view.getId()) {
            case R.id.rl_single:
                BamToast.show(this, "点击了条目样式 1");
                break;
            case R.id.rl_fresh:
                BamToast.show(this, "点击了最新上架");
                break;
            case R.id.rl_hot:
                BamToast.show(this, "点击了热门精选");
                break;
            case R.id.rl_classic:
                BamToast.show(this, "点击了重温经典");
                break;
            case R.id.rl_sleep:
                BamToast.show(this, "点击了睡前小酌");
                break;
            case R.id.iv_single:
                BamToast.show(this, "点击了单个图片");
                break;
        }
    }
}
