package com.bamboy.bamboycollected.page.expresscard.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;

import java.util.List;

/**
 * Created by liushaochen on 2019/3/4.
 */
public class AdapterConvey extends RecyclerView.Adapter {
    private List<String> mList;

    public AdapterConvey(List<String> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_convey, parent, false);
        return new ConveyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ConveyViewHolder vh = (ConveyViewHolder) holder;
        String str = mList.get(position);

        vh.tv_convey.setText(str);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ConveyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_convey;

        ConveyViewHolder(View itemView) {
            super(itemView);
            tv_convey = itemView.findViewById(R.id.tv_convey);
        }

    }
}
