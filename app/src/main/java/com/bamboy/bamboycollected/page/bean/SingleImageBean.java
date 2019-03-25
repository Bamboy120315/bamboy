package com.bamboy.bamboycollected.page.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;

import java.util.List;

/**
 * 单个图片的条目的Bean
 * <p>
 * 没啥用，单纯为了体现非约束列表
 * <p>
 * Created by Bamboy on 2017/4/18.
 */
public class SingleImageBean extends FreedomBean {
    /**
     * 图片链接(这里使用本地图片形式)
     */
    private int img_url;

    public SingleImageBean() {
    }

    public SingleImageBean(int img_url) {
        this.img_url = img_url;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerBamboy.ITEM_TYPE_SINGLE_IMAGE);
    }

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    protected void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, ViewHolderManager.ViewHolder viewHolder, final int position) {
                final SingleImageViewHolder vh = (SingleImageViewHolder) viewHolder;
                SingleImageBean bean = (SingleImageBean) list.get(position);

                vh.iv_single.post(new Runnable() {
                    @Override
                    public void run() {
                        // 设置宽高比
                        RelativeLayout.LayoutParams params =
                                new RelativeLayout.LayoutParams(
                                        -1,
                                        vh.iv_single.getWidth() * 300 / 511);
                        vh.iv_single.setLayoutParams(params);
                    }
                });

                // 显示图片
                vh.iv_single.setImageResource(bean.getImg_url());

                // 点击事件
                vh.rl_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 分批加载Item
     */
    public static class SingleImageViewHolder extends ViewHolderManager.ViewHolder {

        public RelativeLayout rl_single;
        public ImageView iv_single;

        public SingleImageViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.item_single_image);

            rl_single = itemView.findViewById(R.id.rl_single);
            iv_single = itemView.findViewById(R.id.iv_single);
        }
    }
}
