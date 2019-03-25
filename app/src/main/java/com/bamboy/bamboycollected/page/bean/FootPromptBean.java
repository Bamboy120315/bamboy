package com.bamboy.bamboycollected.page.bean;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;

import java.util.List;

/**
 * 列表底部”正在加载“文字提示
 * <p>
 * Created by Bamboy on 2017/4/11.
 */
public class FootPromptBean extends FreedomBean {
    /**
     * 本页数据加载完成
     */
    public static final int TYPE_PAGE_SUCCEED = 1;
    /**
     * 正在加载下一页
     */
    public static final int TYPE_LOAD_ING = 2;
    /**
     * 全部数据加载完成
     */
    public static final int TYPE_ALL_SUCCEED = 3;
    /**
     * 本页数据加载失败
     */
    public static final int TYPE_PAGE_FAILURE = 4;

    private String prompt;
    private int type;

    public FootPromptBean() {
        setType(TYPE_PAGE_SUCCEED);
    }

    public String getPrompt() {
        return prompt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (this.type != TYPE_ALL_SUCCEED) {
            this.type = type;
        }

        switch (this.type) {
            default:
            case TYPE_PAGE_SUCCEED:
                prompt = "点击加载下一页";
                break;
            case TYPE_LOAD_ING:
                prompt = "Bamboy正在为您加载";
                break;
            case TYPE_ALL_SUCCEED:
                prompt = "全部数据加载完成";
                break;
            case TYPE_PAGE_FAILURE:
                prompt = "加载失败，点击重新加载";
                break;
        }
    }


    //==============================================================================================
    //======================= 以 下 是 关 于 条 目 所 需 内 容 ========================================
    //==============================================================================================

    @Override
    protected void initItemType() {
        setItemType(ManagerBamboy.ITEM_TYPE_FOOT_PROMPT);
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
            public void onBindViewHolder(final Context context, final ViewHolderManager.ViewHolder viewHolder, final int position) {
                FootPromptViewHolder vh = (FootPromptViewHolder) viewHolder;
                FootPromptBean bean = (FootPromptBean) list.get(position);

                vh.tv_foot_prompt.setText(bean.getPrompt());

                vh.tv_foot_prompt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCallback(context).onClickCallback(v, position, viewHolder);
                    }
                });
            }
        });
    }

    /**
     * ViewHolder --> 分批加载底部提示文字
     */
    public static class FootPromptViewHolder extends ViewHolderManager.ViewHolder {
        public TextView tv_foot_prompt;

        public FootPromptViewHolder(ViewGroup viewGroup) {
            // 两个参数，第一个viewGroup不解释，第二个即本ViewHolder对应的LayoutXml
            super(viewGroup, R.layout.item_divide_foot_prompt);

            tv_foot_prompt = itemView.findViewById(R.id.tv_foot_prompt);
        }

    }
}
