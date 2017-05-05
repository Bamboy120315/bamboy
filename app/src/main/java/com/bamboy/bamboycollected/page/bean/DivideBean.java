package com.bamboy.bamboycollected.page.bean;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.listener.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;

import java.util.List;

/**
 * 分页加载Activity的Bean
 * <p>
 * 用于在分页加载页展示
 * <p>
 * Created by Bamboy on 2017/4/11.
 */
public class DivideBean extends FreedomBean {

    private int Pagination;
    private int serialNumber;

    public DivideBean(int pagination, int serialNumber) {
        Pagination = pagination;
        this.serialNumber = serialNumber;
    }

    public int getPagination() {
        return Pagination;
    }

    public void setPagination(int pagination) {
        Pagination = pagination;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }


    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ViewHolderManager.ITEM_TYPE_DIVIDE_LOAD);
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
            public void onBindViewHolder(final Activity activity, ViewHolderManager.ViewHolder viewHolder, final int position) {
                final DivideLoadItemViewHolder vh = (DivideLoadItemViewHolder) viewHolder;
                DivideBean bean = (DivideBean) list.get(position);

                // 第N页
                vh.tv_pagination.setText(Html.fromHtml("<small><small><small>" + "第" + "</small></small></small>" +
                        bean.getPagination() +
                        "<small><small><small>" + "页" + "</small></small></small>"));

                // 由于本Demo最大的优点就是是无感知
                // 所以为了方便同学们明确感知到分批加载
                // 每一页以不同颜色来进行区分
                switch (bean.getPagination() % 5) {
                    case 1:
                    default:
                        vh.tv_pagination.setBackgroundColor(activity.getResources().getColor(R.color.colorBlue));
                        break;
                    case 2:
                        vh.tv_pagination.setBackgroundColor(activity.getResources().getColor(R.color.colorBrownDark));
                        break;
                    case 3:
                        vh.tv_pagination.setBackgroundColor(activity.getResources().getColor(R.color.colorOrange));
                        break;
                    case 4:
                        vh.tv_pagination.setBackgroundColor(activity.getResources().getColor(R.color.colorGreenTeg));
                        break;
                    case 0:
                        vh.tv_pagination.setBackgroundColor(activity.getResources().getColor(R.color.colorRed));
                        break;
                }

                //第N个条目
                vh.tv_serial_number.setText("第" + bean.getSerialNumber() + "个");

                vh.rl_root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(activity).onClickCallback(v, position, vh);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 分批加载Item
     */
    public static class DivideLoadItemViewHolder extends ViewHolderManager.ViewHolder {
        public RelativeLayout rl_root;
        public TextView tv_pagination;
        public TextView tv_serial_number;

        public DivideLoadItemViewHolder(View itemView) {
            super(itemView);

            rl_root = (RelativeLayout) itemView.findViewById(R.id.rl_root);
            tv_pagination = (TextView) itemView.findViewById(R.id.tv_pagination);
            tv_serial_number = (TextView) itemView.findViewById(R.id.tv_serial_number);
        }
    }
}
