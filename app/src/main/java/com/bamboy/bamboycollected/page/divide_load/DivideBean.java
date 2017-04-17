package com.bamboy.bamboycollected.page.divide_load;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.adapter.AdapterBean;
import com.bamboy.bamboycollected.adapter.ViewHolderManager;
import com.bamboy.bamboycollected.base.BamActivity;

import java.util.List;

/**
 * 分页加载Activity的Bean
 * <p>
 * 用于在分页加载页展示
 * <p>
 * Created by Bamboy on 2017/4/11.
 */
public class DivideBean extends AdapterBean {

    private int Pagination;
    private int serialNumber;

    public DivideBean(BamActivity activity) {
        super(activity);
    }

    @Override
    protected void initItemType() {
        setItemType(ViewHolderManager.ITEM_TYPE_DIVIDE_LOAD);
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

    /**
     * 将数据绘制到ViewHolder上
     *
     * @param list 数据源
     */
    @Override
    protected void initBindView(final List list) {
        setBindListener(new AdapterBean.OnBindViewHolderListener() {
            @Override
            public void onBind(ViewHolderManager.ViewHolder viewHolder, int position) {
                DivideLoadItemViewHolder vh = (DivideLoadItemViewHolder) viewHolder;
                DivideBean bean = (DivideBean) list.get(position);

                // 第N页
                vh.tv_pagination.setText(Html.fromHtml("<small><small><small>" + "第" + "</small></small></small>" +
                        bean.getPagination() +
                        "<small><small><small>" + "页" + "</small></small></small>"));

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
            }
        });
    }

    /**
     * ViewHolder --> 分批加载Item
     */
    public static class DivideLoadItemViewHolder extends ViewHolderManager.ViewHolder {
        public TextView tv_pagination;
        public TextView tv_serial_number;

        public DivideLoadItemViewHolder(View itemView) {
            super(itemView);

            tv_pagination = (TextView) itemView.findViewById(R.id.tv_pagination);
            tv_serial_number = (TextView) itemView.findViewById(R.id.tv_serial_number);
        }
    }
}
