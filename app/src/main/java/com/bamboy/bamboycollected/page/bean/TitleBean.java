package com.bamboy.bamboycollected.page.bean;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;

import java.util.List;

/**
 * 标题的条目的Bean
 * <p>
 * Created by Bamboy on 2018/3/25.
 */
public class TitleBean extends FreedomBean {

    private String text;

    /**
     * 构造函数
     */
    public TitleBean() {
    }

    /**
     * 构造函数
     *
     * @param text 标题
     */
    public TitleBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    /**
     * 设置本Bean所对应的条目类型
     */
    @Override
    protected void initItemType() {
        setItemType(ManagerBamboy.ITEM_TYPE_TITLE);
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
            public void onBindViewHolder(Context context, ViewHolderManager.ViewHolder viewHolder, int position) {
                TitleViewHolder vh = (TitleViewHolder) viewHolder;
                final TitleBean bean = (TitleBean) list.get(position);
                vh.tv_title.setText(bean.getText());
            }
        });
    }

    /**
     * ViewHolder --> 主页的按钮
     */
    public static class TitleViewHolder extends ViewHolderManager.ViewHolder {
        public TextView tv_title;

        public TitleViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.item_title);

            tv_title = itemView.findViewById(R.id.tv_title);
        }

    }
}
