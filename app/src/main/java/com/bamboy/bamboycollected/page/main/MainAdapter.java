package com.bamboy.bamboycollected.page.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.base.BamboyActivity;
import com.bamboy.bamboycollected.view.clickanimview.BamButton;

import java.util.List;

/**
 * Created by Bamboy on 2017/3/27.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private BamboyActivity mActivity;
    private List<BeanMain> mList;

    public MainAdapter(BamboyActivity activity, List<BeanMain> list){
        mActivity = activity;
        mList = list;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.item_btn, viewGroup,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder vh, int i) {
        BeanMain bean = mList.get(i);
        vh.btn_main.setText(bean.getText());
        vh.btn_main.closeSuperb();

        vh.btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        BamButton btn_main;

        public ViewHolder(View itemView) {
            super(itemView);

            btn_main = (BamButton) itemView.findViewById(R.id.btn_main);
        }
    }
}
