package com.bamboy.bamboycollected.page.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;

import java.util.List;

/**
 * 单个按钮的条目的Bean
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class SingleBtnBean extends FreedomBean {

    private String text;
    private Class startClass;

    /**
     * 构造函数
     */
    public SingleBtnBean() {
    }

    /**
     * 构造函数
     *
     * @param text       按钮上的文字
     * @param startClass 要打开的Activity的类名
     */
    public SingleBtnBean(String text, Class startClass) {
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
        setItemType(ManagerBamboy.ITEM_TYPE_SINGLE_BUTTON);
    }

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    public void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                SingleBtnViewHolder vh = (SingleBtnViewHolder) viewHolder;
                final SingleBtnBean bean = (SingleBtnBean) list.get(position);
                vh.tv_single.setText(bean.getText());

                vh.rl_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, viewHolder);
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

        public SingleBtnViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.item_single_button);

            rl_single = itemView.findViewById(R.id.rl_single);
            tv_single = itemView.findViewById(R.id.tv_single);
        }

    }
}
