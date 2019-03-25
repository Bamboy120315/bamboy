package com.bamboy.bamboycollected.page.freedom.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerA;

import java.util.List;

/**
 * 新闻条目Bean
 * <p>
 * Created by Bamboy on 2017/5/10.
 */
public class BeanNewsText extends FreedomBean {

    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻内容
     */
    private String desc;

    public BeanNewsText() {
    }

    /**
     * 构造
     *
     * @param title 新闻标题
     * @param desc  新闻内容
     */
    public BeanNewsText(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_NEWS_TEXT);
    }

    @Override
    protected void initBindView(final List list) {

        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                final NewsTextViewHolder vh = (NewsTextViewHolder) viewHolder;
                final BeanNewsText bean = (BeanNewsText) list.get(position);

                vh.tv_title.setText(bean.getTitle());
                vh.tv_desc.setText(bean.getDesc());

                vh.rl_root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 点击事件
                        // 如果不需要和Activity进行交互，
                        // 那么直接在这里写点击事件即可
                        //
                        // 如果需要和Activity进行交互，
                        // 那么Activity实现FreedomCallback接口，
                        // 并在onClickCallback里编写代码，
                        // 即可触发回调，
                        // 以和Activity进行交互。
                        //
                        // 注意：
                        // 该Activity必须实现FreedomCallback接口才能触发回调，
                        // 否则会报错
                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 主页的按钮
     */
    public static class NewsTextViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_root;
        public TextView tv_title;
        public TextView tv_desc;

        public NewsTextViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_news_text);

            rl_root = itemView.findViewById(R.id.rl_root);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }

    }
}
