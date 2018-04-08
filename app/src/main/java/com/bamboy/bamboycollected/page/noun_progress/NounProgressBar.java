package com.bamboy.bamboycollected.page.noun_progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bamboy.bamboycollected.R;

/**
 * Created by Bamboy on 2018/4/3.
 */
public class NounProgressBar extends View {

    /**
     * 进度改变时的监听
     */
    private OnProgressListener mListener;

    /**
     * View宽度
     */
    private int mViewWidth;
    /**
     * View高度
     */
    private int mViewHeight;
    /**
     * 顶部间距，用户竖直居中
     */
    private int mTopPadding;

    /**
     * 进度条的长度
     */
    private int mProgressLenth;
    /**
     * 进度条绿色部分的长度
     */
    private int mFinishedLenth;

    /**
     * 当前进度
     */
    private int progress;
    /**
     * 总进度
     */
    private int mProgressMax;

    /**
     * 节点数
     */
    private int mNounCount;
    /**
     * 节点高度
     */
    private int mNounHeight;
    /**
     * 进度条高度
     */
    private int mProgressHeight;

    /**
     * 完成的进度条的颜色
     */
    private int mFinishedColor;
    /**
     * 未完成的进度条的颜色
     */
    private int mUnfinishedColor;


    public NounProgressBar(Context context) {
        super(context);
        initAttr(context, null);
    }

    public NounProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public NounProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            mNounCount = 5;
            mNounHeight = 50;
            mProgressHeight = 25;
            mProgressMax = 100;
            mFinishedColor = 0xFF2BBC69;
            mUnfinishedColor = 0xFFDDDDDD;
            return;
        }

        // -------------------- 获取自定义属性 --------------------
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NounProgressBar);
        mNounCount = typedArray.getInteger(R.styleable.NounProgressBar_nounCount, 5);
        mNounHeight = typedArray.getInteger(R.styleable.NounProgressBar_heightNoun, 50);
        mProgressHeight = typedArray.getInteger(R.styleable.NounProgressBar_heightProgress, 25);
        mProgressMax = typedArray.getInteger(R.styleable.NounProgressBar_progressMax, 100);
        mFinishedColor = typedArray.getColor(R.styleable.NounProgressBar_colorFinished, 0xFF2BBC69);
        mUnfinishedColor = typedArray.getColor(R.styleable.NounProgressBar_colorUnfinished, 0xFFDDDDDD);

        // -------------------- 效验属性值 --------------------
        // 节点不少于2个
        if (mNounCount < 2)
            mNounCount = 2;

        // 节点高度不能低于进度条的高度
        if (mNounHeight < mProgressHeight)
            mNounHeight = mProgressHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

        // 计算进度条的长度
        mProgressLenth = mViewWidth - (mNounHeight / 2) - (mProgressHeight / 2);
        // 计算进度条绿色部分的长度
        mFinishedLenth = (int) (mProgressLenth * ((float) progress / (float) mProgressMax));
        // 计算顶部预留多少间距
        mTopPadding = (mViewHeight - mNounHeight) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 先重新计算进度条绿色部分长度
        mFinishedLenth = (int) (mProgressLenth * ((float) progress / (float) mProgressMax));
        // 计算绿色节点数量
        int finishedNounNum = (int) ((mNounCount - 1) * ((float) progress / (float) mProgressMax)) + 1;

        // -------------------- 先画灰色部分 --------------------
        // 构造灰色画笔
        Paint paintUnFinished = initPaint(mUnfinishedColor, Paint.Style.FILL, 0);
        // 画进度条方块
        onDrawUnfinishedProgres(canvas, paintUnFinished);
        // 画灰色节点
        onDrawNoun(canvas, paintUnFinished, finishedNounNum, mNounCount);

        // -------------------- 然后画绿色部分 --------------------
        // 构造绿色画笔
        Paint paintFinished = initPaint(mFinishedColor, Paint.Style.FILL, 0);
        // 画绿色进度条方块
        int progresArrowStartLeft = onDrawProgres(canvas, paintFinished);
        // 画绿色进度条右侧圆头
        onDrawProgresArrow(canvas, paintFinished, progresArrowStartLeft);
        // 画绿色节点
        onDrawNoun(canvas, paintFinished, 0, finishedNounNum);

        if (mListener != null)
            mListener.onProgress(this, progress);
    }

    /**
     * 初始化画笔
     *
     * @param color 颜色
     * @param style 样式
     * @param width 轮廓宽度
     * @return 画笔
     */
    private Paint initPaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        // 设置消除锯齿
        paint.setAntiAlias(true);
        // 设置颜色
        paint.setColor(color);
        // 设置实心
        paint.setStyle(style);
        // 设置画笔的宽度
        paint.setStrokeWidth(width);
        return paint;
    }

    /**
     * 画未完成的灰色方块
     *
     * @return
     */
    private void onDrawUnfinishedProgres(Canvas canvas, Paint paint) {
        // 先计算坐标
        int startLeft = mNounHeight / 2 + mFinishedLenth;
        int startTop = (mNounHeight - mProgressHeight) / 2 + mTopPadding;
        int endRight = mViewWidth - mNounHeight / 2;
        int endBottom = startTop + mProgressHeight;

        //构造进度条
        Rect rect = new Rect(startLeft, startTop, endRight, endBottom);

        //画出进度条
        canvas.drawRect(rect, paint);
    }

    /**
     * 画绿色进度条方块
     *
     * @return
     */
    private int onDrawProgres(Canvas canvas, Paint paint) {
        // 计算左上右下坐标
        int startLeft = mNounHeight / 2;
        int startTop = (mNounHeight - mProgressHeight) / 2 + mTopPadding;
        int endRight = startLeft + mFinishedLenth;
        int endBottom = startTop + mProgressHeight;

        if (endRight > mViewWidth - mProgressHeight / 2) {
            endRight = mViewWidth - mProgressHeight / 2;
        }

        //构造进度条
        Rect rect = new Rect(startLeft, startTop, endRight, endBottom);

        //画出进度条
        canvas.drawRect(rect, paint);

        return endRight;
    }

    /**
     * 画绿色进度条右侧圆头
     *
     * @return
     */
    private void onDrawProgresArrow(Canvas canvas, Paint paint, int startLeft) {
        // 计算左上右下坐标
        startLeft = (int) (startLeft - (float) mProgressHeight / 2);
        int startTop = (mNounHeight - mProgressHeight) / 2 + mTopPadding;
        int endRight = startLeft + mProgressHeight;
        int endBottom = startTop + mProgressHeight;

        // 构造圆头
        RectF rectF = new RectF(startLeft, startTop, endRight, endBottom);

        // 画出圆头
        canvas.drawArc(rectF, -90, 180, false, paint);
    }

    /**
     * 画节点
     *
     * @return
     */
    private void onDrawNoun(Canvas canvas, Paint paint, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            // 计算左上右下坐标
            int startLeft = (int) ((float) (mViewWidth - mNounHeight) / (float) (mNounCount - 1) * i);
            int startTop = mTopPadding;
            int endRight = startLeft + mNounHeight;
            int endBottom = startTop + mNounHeight;

            // 构造圆头
            RectF rectF = new RectF(startLeft, startTop, endRight, endBottom);

            // 画出圆头
            canvas.drawArc(rectF, 0, 360, false, paint);
        }
    }

    /**
     * 设置节点数量
     *
     * @param nounCount
     */
    public void setNounCount(int nounCount) {
        this.mNounCount = nounCount;
    }

    /**
     * 获取节点数量
     *
     * @return
     */
    public int getNounCount() {
        return mNounCount;
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