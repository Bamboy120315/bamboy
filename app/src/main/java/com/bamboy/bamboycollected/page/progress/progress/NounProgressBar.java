package com.bamboy.bamboycollected.page.progress.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.progress.progress.BaseProgress;

/**
 * Created by Bamboy on 2019/2/20.
 */
public class NounProgressBar extends BaseProgress {

    /**
     * View宽度
     */
    private int mViewWidth;
    /**
     * View高度
     */
    private int mViewHeight;
    /**
     * 顶部间距，用于竖直居中
     */
    private float mTopPadding;

    /**
     * 进度条的长度
     */
    private float mProgressLenth;
    /**
     * 进度条绿色部分的长度
     */
    private int mFinishedLenth;

    /**
     * 节点数
     */
    private int mNounCount;
    /**
     * 节点高度
     */
    private float mNounHeight;
    /**
     * 进度条高度
     */
    private float mProgressHeight;

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
            mFinishedColor = 0xFF009E96;
            mUnfinishedColor = 0xFFDDDDDD;
            return;
        }

        // -------------------- 获取自定义属性 --------------------
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NounProgressBar);
        progress = typedArray.getInteger(R.styleable.NounProgressBar_progress, 0);
        mProgressMax = typedArray.getInteger(R.styleable.NounProgressBar_progressMax, 100);
        mNounCount = typedArray.getInteger(R.styleable.NounProgressBar_nounCount, 5);
        mNounHeight = typedArray.getInteger(R.styleable.NounProgressBar_nounHeight, 50);
        mProgressHeight = typedArray.getDimension(R.styleable.NounProgressBar_lineHeight, 25);
        mFinishedColor = typedArray.getColor(R.styleable.NounProgressBar_colorFinished, 0xFF009E96);
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
        mProgressLenth = mViewWidth - mNounHeight;
        // 计算进度条绿色部分的长度
        mFinishedLenth = (int) (mProgressLenth * ((float) progress / (float) mProgressMax));
        // 计算顶部预留多少间距
        mTopPadding = (mViewHeight - mNounHeight) / 2;

        this.post(new Runnable() {
            @Override
            public void run() {
                // 更新UI
                invalidate();
            }
        });
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
        // 画进度条线条
        onDrawUnfinishedProgres(canvas, paintUnFinished);
        // 画灰色节点
        onDrawNoun(canvas, paintUnFinished, finishedNounNum, mNounCount);

        // -------------------- 然后画绿色部分 --------------------
        // 构造绿色画笔
        Paint paintFinished = initPaint(mFinishedColor, Paint.Style.FILL, 0);
        // 画绿色进度条线条
        onDrawProgres(canvas, paintFinished);
        // 画绿色节点
        onDrawNoun(canvas, paintFinished, 0, finishedNounNum);
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
        // 设置线条圆角
        paint.setStrokeCap(Paint.Cap.ROUND);
        // 修改画笔的宽度
        paint.setStrokeWidth(mProgressHeight);
        return paint;
    }

    /**
     * 画未完成的灰色线条
     *
     * @return
     */
    private void onDrawUnfinishedProgres(Canvas canvas, Paint paint) {
        // 计算左上右下坐标
        float startX = mProgressHeight + mFinishedLenth;
        float startY = mViewHeight / 2;
        float endX = mProgressLenth + mNounHeight / 2;
        float endY = startY;

        // 绘制直线
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    /**
     * 画绿色进度条线条
     *
     * @return
     */
    private void onDrawProgres(Canvas canvas, Paint paint) {
        // 计算左上右下坐标
        float startX = mProgressHeight;
        float startY = mViewHeight / 2;
        float endX = startX + mFinishedLenth;
        float endY = startY;

        if (endX > mViewWidth - mProgressHeight / 2) {
            endX = mViewWidth - mProgressHeight / 2;
        }

        // 绘制直线
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    /**
     * 画节点
     *
     * @return
     */
    private void onDrawNoun(Canvas canvas, Paint paint, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            // 计算左上右下坐标
            int startLeft = (int) ((mViewWidth - mNounHeight) / (float) (mNounCount - 1) * i);
            float startTop = mTopPadding;
            float endRight = startLeft + mNounHeight;
            float endBottom = startTop + mNounHeight;

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

}