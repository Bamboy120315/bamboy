package com.bamboy.bamboycollected.page.freedom.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.freedom.FreedomBean;
import com.bamboy.bamboycollected.base.freedom.ViewHolderBindListener;
import com.bamboy.bamboycollected.base.freedom.ViewHolderManager;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerC;

import java.util.List;

/**
 * Created by 程序猿C on 2017/5/19.
 */
public class BeanDialogueRight extends FreedomBean {

    /**
     * 名字
     */
    private String name;
    /**
     * 内容
     */
    private String content;

    /**
     * 构造
     */
    public BeanDialogueRight() {
    }

    /**
     * 构造
     *
     * @param name    名字
     * @param content 内容
     */
    public BeanDialogueRight(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected void initBindView(final List list) {
        setViewHolderBindListener(new ViewHolderBindListener() {
            @Override
            public void onBindViewHolder(final Context context, ViewHolderManager.ViewHolder viewHolder, final int position) {
                BeanDialogueRight bean = (BeanDialogueRight) list.get(position);
                final DialogueViewHolder vh = (DialogueViewHolder) viewHolder;

                vh.tv_name.setText(bean.getName());
                vh.tv_content.setText(bean.getContent());
                vh.tv_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        getCallback(context).onClickCallback(v, position, vh);
                    }
                });

            }
        });
    }

    @Override
    protected void initItemType() {
        setItemType(ManagerC.ITEM_TYPE_DIALOGUE_RIGHT);
    }

    public static class DialogueViewHolder extends ViewHolderManager.ViewHolder {

        TextView tv_name;
        TextView tv_content;

        public DialogueViewHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.fitem_dialogue_r);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);

        }
    }
}
