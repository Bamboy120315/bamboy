package com.bamboy.bamboycollected.page.expresscard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;


/**
 * Created by liushaochen on 2019/3/4.
 */
public class CardFragment extends Fragment {
    private View mView;
    private BeanExpress bean;

    private TextView tv_consignee;
    private TextView tv_express;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fm_card, null);

        // 初始化
        initView();

        return mView;
    }

    /**
     * 初始化
     */
    private void initView() {
        if (mView == null) {
            return;
        }

        tv_consignee = mView.findViewById(R.id.tv_consignee);
        tv_express = mView.findViewById(R.id.tv_express);

        if (bean != null) {
            tv_consignee.setText("收货人：" + bean.getConsignee());
            tv_express.setText("承运方：" + bean.getExpress());
        }

    }

    /**
     * 设置数据Bean
     *
     * @param bean
     */
    public void setExpress(BeanExpress bean) {
        this.bean = bean;

        if (tv_consignee == null) {
            initView();
        } else {
            tv_consignee.setText(bean.getConsignee());
            tv_express.setText(bean.getExpress());
        }
    }

}
