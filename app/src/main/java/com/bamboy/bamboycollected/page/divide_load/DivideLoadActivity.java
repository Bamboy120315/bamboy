package com.bamboy.bamboycollected.page.divide_load;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.actiivty.BamActivity;
import com.bamboy.bamboycollected.base.freedom.FreedomAdapter;
import com.bamboy.bamboycollected.base.freedom.FreedomCallback;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.page.bean.DivideBean;
import com.bamboy.bamboycollected.page.bean.FootPromptBean;
import com.bamboy.bamboycollected.page.toast.bamtoast.BamToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 分批加载Demo
 * <p>
 * Created by Bamboy on 2017/4/10.
 */
public class DivideLoadActivity extends BamActivity implements FreedomCallback {

    private List mList;
    private FreedomAdapter mAdapter;
    private RecyclerView rv_divide_load;
    /**
     * 页脚的提示文字
     */
    private FootPromptBean tvBean;
    /**
     * 每页条数
     */
    private final int PAGESIZE = 10;
    /**
     * 当前页数
     */
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide_load);
    }

    @Override
    protected void findView() {
        rv_divide_load = findViewById(R.id.rv_divide_load);
    }

    @Override
    protected void setListener() {
        // 为RecyclerView添加滑动监听
        rv_divide_load.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {

                    //获取最后一个可见view的位置
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int lastPosition = linearManager.findLastVisibleItemPosition();

                    // 如果滑动到倒数第三条数据，就自动加载下一页数据
                    if (lastPosition >= layoutManager.getItemCount() - 4) {
                        load();
                    }

                }
            }
        });
    }

    @Override
    protected void init() {
        // 初始化页脚提示文字
        tvBean = new FootPromptBean();
        tvBean.setType(FootPromptBean.TYPE_PAGE_SUCCEED);

        load();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 模拟从网络加载数据
     */
    private void load() {
        try {
            // 如果是正在加载下一页的时候，禁止加载
            if (tvBean.getType() != 1) {
                return;
            }

            // 先把底部文字改成正在加载
            tvBean.setType(FootPromptBean.TYPE_LOAD_ING);

            // 模拟解析数据
            analyticalData();

            if (mAdapter == null) {
                mAdapter = new FreedomAdapter(this, mList);
                rv_divide_load.setLayoutManager(new LinearLayoutManager(this));
                rv_divide_load.setItemAnimator(new DefaultItemAnimator());
                rv_divide_load.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }

            // 数据正常，把状态标记成读取完毕，可进行下页数据的读取
            tvBean.setType(FootPromptBean.TYPE_PAGE_SUCCEED);
            page++;
        } catch (Exception e) {
            e.printStackTrace();

            if (tvBean != null) {
                // 如遇断网、数据解析失败等异常情况时，把状态标记成加载失败，点击可重新加载
                tvBean.setType(FootPromptBean.TYPE_PAGE_FAILURE);
            }
        }
    }

    /**
     * 模拟解析数据
     */
    private void analyticalData() throws Exception {

        // 模拟加载数据
        List<DivideBean> list = new ArrayList();
        for (int i = 0; i < PAGESIZE; i++) {
            list.add(new DivideBean(page, i + 1));

            // 模拟数据一共只有53条
            if (page == 5 && i == 2) {
                break;
            }
        }

        // 是否是最后一页判断
        if (list.size() < PAGESIZE) {
            // 数据不足PageSize，说明最后一页
            tvBean.setType(FootPromptBean.TYPE_ALL_SUCCEED);

            // 如果一条数据都没读到，说明是最后一页，就不用刷新列表，故return；
            if (list.size() == 0){
                return;
            }
        }

        // 如果mList为空，则进行初始化
        if (utils.want.isNull(mList)) {
            mList = new ArrayList();
        }

        // 移除页脚的提示文字
        if (mList.size() >= 1 && mList.get(mList.size() - 1) instanceof FootPromptBean) {
            mList.remove(mList.size() - 1);
        }

        // 将数据放到数据源中
        mList.addAll(list);

        // 将页脚的提示文字放到数据源
        mList.add(tvBean);
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
        switch (view.getId()) {
            case R.id.tv_foot_prompt:                   // 底部提示文字点击事件
                if (tvBean.getType() != 2 && tvBean.getType() != 3) {
                    // 只要不是正在读取下一页 和 已经读取全部数据，点击即可加载数据
                    tvBean.setType(FootPromptBean.TYPE_PAGE_SUCCEED);
                    load();
                }
                break;

            case R.id.rl_root:
                DivideBean bean = (DivideBean) mList.get(position);
                BamToast.showText(
                        this,
                        "点击了第" + bean.getPagination() + "页的第" + bean.getSerialNumber() + "个");
                break;
        }
    }
}
