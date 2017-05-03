package com.bamboy.bamboycollected.base.freedom;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView的Adapter
 * <p>
 * 由于使用插件式列表，
 * 在开发过程中，
 * 本类基本不需要改动
 * <p>
 * Created by Bamboy on 2017/4/11.
 */
public class FreedomAdapter extends RecyclerView.Adapter<ViewHolderManager.ViewHolder> {

    private List<FreedomBean> mList;
    private Activity mActivity;

    /**
     * Adapter 构造
     *
     * @param list
     */
    public FreedomAdapter(Activity activity, List list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public ViewHolderManager.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return ViewHolderManager.createViewHolder(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolderManager.ViewHolder viewHolder, int position) {
        FreedomBean bean = mList.get(position);
        if (bean.getViewHolderBindListener() == null) {
            bean.initBindView(mList);
        }
        bean.bindViewHolder(mActivity, viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();
    }

    /**
     * 瀑布流模式
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(ViewHolderManager.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        // 处理瀑布流模式 最后的 item 占整行
        if (holder.getLayoutPosition() == getItemCount() - 1) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /**
     * 网格布局模式
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        // 处理网格布局模式 最后的 item 占整行
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridManager.getSpanSizeLookup();
            final int lastSpanCount = gridManager.getSpanCount();
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == getItemCount() - 1 ? lastSpanCount :
                            (spanSizeLookup == null ? 1 : spanSizeLookup.getSpanSize(position));
                }
            });
        }
    }

}
