package com.bamboy.bamboycollected.page.progress.progress;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Bamboy on 2019/2/20.
 */

public class BaseProgress extends View {

    /**
     * 进度改变时的监听
     */
    protected OnProgressListener mListener;
    /**
     * 当前进度
     */
    protected int progress;
    /**
     * 总进度
     */
    protected int mProgressMax;

    public BaseProgress(Context context) {
        super(context);
    }

    public BaseProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(float progress) {
        if (progress < 0)
            progress = 0;

        if (progress > mProgressMax)
            progress = mProgressMax;

        this.progress = (int) progress;

        if (mListener != null)
            mListener.onProgress(this, this.progress);

        // 更新UI
        invalidate();
    }

    /**
     * 获取进度
     *
     * @return 当前进度
     */
    public int getProgress() {
        return progress;
    }

    /**
     * 获取最大值
     *
     * @return 最大进度
     */
    public int getProgressMax() {
        return mProgressMax;
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setProgressListener(OnProgressListener listener) {
        mListener = listener;
    }
}
