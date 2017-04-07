package com.bamboy.bamboycollected.page.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamActivity;
import com.bamboy.bamboycollected.view.BamToast;
import com.bamboy.bamboycollected.view.clickanimview.BamButton;

import java.util.List;

/**
 * 主页Adapter
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private BamActivity mActivity;
    private List<MainBean> mList;

    public MainAdapter(BamActivity activity, List<MainBean> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_main, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder vh, int i) {
        final MainBean bean = mList.get(i);
        vh.btn_main.setText(bean.getText());
        vh.btn_main.closeSuperb();

        vh.btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getStartClass() != null) {
                    Intent intent = new Intent(mActivity, bean.getStartClass());
                    mActivity.startActivity(intent);
                } else {
                    BamToast.show(mActivity, "该Demo尚未整合\n请耐心等待", BamToast.COLOR_BLUE_SKY);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        BamButton btn_main;

        public ViewHolder(View itemView) {
            super(itemView);

            btn_main = (BamButton) itemView.findViewById(R.id.btn_main);
        }
    }
}
