package com.bamboy.bamboycollected.page.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;

import java.util.List;

/**
 * 有四个【图标+文字】按钮的条目的Bean
 * <p>
 * 没啥用，单纯为了体现非约束列表
 * <p>
 * Created by Bamboy on 2017/4/18.
 */
public class FourIconBean extends FreedomBean {

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerBamboy.ITEM_TYPE_FOUR_ICON);
    }

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    protected void initBindView(List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, ViewHolderManager.ViewHolder viewHolder, final int position) {
                final FourIconViewHolder vh = (FourIconViewHolder) viewHolder;

                /**
                 * 点击事件
                 */
                View.OnClickListener onClick = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, vh);
                    }
                };

                vh.rl_fresh.setOnClickListener(onClick);
                vh.rl_hot.setOnClickListener(onClick);
                vh.rl_classic.setOnClickListener(onClick);
                vh.rl_sleep.setOnClickListener(onClick);
            }
        });
    }

    /**
     * ViewHolder --> 分批加载Item
     */
    public static class FourIconViewHolder extends ViewHolderManager.ViewHolder {

        // 第一个按钮 --> 最新上架
        public RelativeLayout rl_fresh;
        public ImageView iv_fresh;
        public TextView tv_fresh;

        // 第二个按钮 --> 热门精选
        public RelativeLayout rl_hot;
        public ImageView iv_hot;
        public TextView tv_hot;

        // 第三个按钮 --> 重温经典
        public RelativeLayout rl_classic;
        public ImageView iv_classic;
        public TextView tv_classic;

        // 第四个按钮 --> 睡前小酌
        public RelativeLayout rl_sleep;
        public ImageView iv_sleep;
        public TextView tv_sleep;

        public FourIconViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.item_four_icon);

            // 第一个按钮 --> 最新上架
            rl_fresh = itemView.findViewById(R.id.rl_fresh);
            iv_fresh = itemView.findViewById(R.id.iv_fresh);
            tv_fresh = itemView.findViewById(R.id.tv_fresh);

            // 第二个按钮 --> 热门精选
            rl_hot = itemView.findViewById(R.id.rl_hot);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            tv_hot = itemView.findViewById(R.id.tv_hot);

            // 第三个按钮 --> 重温经典
            rl_classic = itemView.findViewById(R.id.rl_classic);
            iv_classic = itemView.findViewById(R.id.iv_classic);
            tv_classic = itemView.findViewById(R.id.tv_classic);

            // 第四个按钮 --> 睡前小酌
            rl_sleep = itemView.findViewById(R.id.rl_sleep);
            iv_sleep = itemView.findViewById(R.id.iv_sleep);
            tv_sleep = itemView.findViewById(R.id.tv_sleep);
        }

    }
}
