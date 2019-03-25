package com.bamboy.bamboycollected.page.freedom.bean;

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
import com.bamboy.bamboycollected.base.freedom.manager.ManagerA;

import java.util.List;

/**
 * 新闻条目Bean
 * <p>
 * Created by Bamboy on 2017/5/10.
 */
public class BeanNewsImg extends FreedomBean {

    /**
     * 新闻图片
     */
    private int imgId;
    /**
     * 新闻标题
     */
    private String title;

    public BeanNewsImg() {
    }

    /**
     * 构造
     *
     * @param imgId 新闻图片
     * @param title 新闻标题
     */
    public BeanNewsImg(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerA.ITEM_TYPE_NEWS_IMG);
    }

    @Override
    protected void initBindView(final List list) {

        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                final NewsImgViewHolder vh = (NewsImgViewHolder) viewHolder;
                final BeanNewsImg bean = (BeanNewsImg) list.get(position);

                vh.iv_img.post(new Runnable() {
                    @Override
                    public void run() {
                        // 设置宽高比
                        RelativeLayout.LayoutParams params =
                                new RelativeLayout.LayoutParams(
                                        -1,
                                        vh.iv_img.getWidth() * 250 / 500);
                        vh.iv_img.setLayoutParams(params);

                        // 显示图片
                        vh.iv_img.setImageResource(getImgId());
                    }
                });

                vh.tv_title.setText(bean.getTitle());

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
    public static class NewsImgViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_root;
        public ImageView iv_img;
        public TextView tv_title;

        public NewsImgViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.fitem_news_img);

            rl_root = itemView.findViewById(R.id.rl_root);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

    }
}
