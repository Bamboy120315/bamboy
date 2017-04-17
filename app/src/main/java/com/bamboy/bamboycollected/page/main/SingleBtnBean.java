package com.bamboy.bamboycollected.page.main;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.adapter.AdapterBean;
import com.bamboy.bamboycollected.adapter.ViewHolderManager;
import com.bamboy.bamboycollected.base.BamActivity;

import java.util.List;

/**
 * Activity的Bean
 * <p>
 * 用于在主页展示
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class SingleBtnBean extends AdapterBean {

    private String text;
    private Class startClass;

    /**
     * 构造函数
     * @param activity
     */
    public SingleBtnBean(BamActivity activity) {
        super(activity);
    }

    /**
     * 构造函数
     * @param activity
     * @param text 按钮上的文字
     * @param startClass 要打开的Activity的类名
     */
    public SingleBtnBean(BamActivity activity, String text, Class startClass) {
        super(activity);
        this.text = text;
        this.startClass = startClass;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getStartClass() {
        return startClass;
    }

    public void setStartClass(Class startClass) {
        this.startClass = startClass;
    }


    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    /**
     * 设置本Bean所对应的条目类型
     */
    @Override
    protected void initItemType() {
        setItemType(ViewHolderManager.ITEM_TYPE_SINGLE_BUTTON);
    }

    /**
     * 将数据绘制到ViewHolder上
     * @param list 数据源
     */
    @Override
    public void initBindView(final List list) {
        setBindListener(new AdapterBean.OnBindViewHolderListener() {
            @Override
            public void onBind(final ViewHolderManager.ViewHolder viewHolder, final int position) {
                SingleBtnViewHolder vh = (SingleBtnViewHolder) viewHolder;
                final SingleBtnBean bean = (SingleBtnBean) list.get(position);
                vh.tv_single.setText(bean.getText());

                vh.rl_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback().onClickCallback(v, position, viewHolder);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 主页的按钮
     */
    public static class SingleBtnViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_single;
        public TextView tv_single;

        public SingleBtnViewHolder(View itemView) {
            super(itemView);

            rl_single = (RelativeLayout) itemView.findViewById(R.id.rl_single);
            tv_single = (TextView) itemView.findViewById(R.id.tv_single);
        }
    }
}
