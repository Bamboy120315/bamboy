package com.bamboy.bamboycollected.page.progress.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.progress.progress.BaseProgress;

/**
 * Created by Bamboy on 2019/3/13.
 */
public class MoveNumberProgressBar extends BaseProgress {

    /**
     * View宽度
     */
    private float mViewWidth;
    /**
     * View高度
     */
    private float mViewHeight;

    /**
     * 进度条的长度
     */
    private int mProgressLenth;
    /**
     * 进度条绿色部分的长度
     */
    private int mFinishedLenth;
    /**
     * 文字大小
     */
    private float mTextSize;
    /**
     * 文字宽度
     */
    private float mTextWidth;
    /**
     * 文字高度
     */
    private float mTextHeight;
    /**
     * 文字边距
     */
    private float mTextPadding;

    /**
     * 进度条高度
     */
    private float mLineHeight;

    /**
     * 完成的进度条的颜色
     */
    private int mFinishedColor;
    /**
     * 未完成的进度条的颜色
     */
    private int mUnfinishedColor;

    public MoveNumberProgressBar(Context context) {
        super(context);
        initAttr(context, null);
    }

    public MoveNumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public MoveNumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            mLineHeight = 2;
            mProgressMax = 100;
            mTextSize = 14;
            mFinishedColor = 0xFF009E96;
            mUnfinishedColor = 0xFFDDDDDD;
            mTextPadding = mLineHeight + 10;
            return;
        }

        // -------------------- 获取自定义属性 --------------------
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MoveNumberProgressBar);
        progress = typedArray.getInteger(R.styleable.MoveNumberProgressBar_progress, 0);
        mProgressMax = typedArray.getInteger(R.styleable.MoveNumberProgressBar_progressMax, 100);
        mLineHeight = typedArray.getDimension(R.styleable.MoveNumberProgressBar_lineHeight, 2);
        mTextSize = typedArray.getDimension(R.styleable.MoveNumberProgressBar_textSize, 14);
        mFinishedColor = typedArray.getColor(R.styleable.MoveNumberProgressBar_colorFinished, 0xFF009E96);
        mUnfinishedColor = typedArray.getColor(R.styleable.MoveNumberProgressBar_colorUnfinished, 0xFFDDDDDD);

        mTextPadding = mLineHeight + 10;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();

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

        Paint paint = initPaint();
        paint.setTextSize(mTextSize);

        Rect rect = new Rect();
        String number = new String("" + progress + "%");
        paint.getTextBounds(number, 0, number.length(), rect);
        // 数字的宽度
        mTextWidth = rect.width();
        mTextWidth += mTextPadding;
        // 数字的高度
        mTextHeight = rect.height();

        float pro = (float) progress / (float) mProgressMax;

        if (pro > 0) {
            // 画绿色进度条线条
            onDrawProgres(canvas, paint);
        }

        // 数字
        canvas.drawText(number, (mViewWidth - mTextWidth) * pro + mTextPadding / 2, (mViewHeight + mTextHeight) / 2, paint);

        if (pro < 1) {
            // 画灰色未完成进度条线条
            onDrawUnfinishedProgres(canvas, paint);
        }
    }

    /**
     * 初始化画笔
     *
     * @return
     */
    private Paint initPaint() {
        // 初始化画笔
        Paint paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 设置防抖，即边缘柔化
        paint.setDither(true);
        // 设置颜色
        paint.setColor(mFinishedColor);
        // 修改画笔的宽度
        paint.setStrokeWidth(mLineHeight);
        // 设置线条圆角
        paint.setStrokeCap(Paint.Cap.ROUND);

        return paint;
    }

    /**
     * 画绿色进度条线条
     *
     * @return
     */
    private void onDrawProgres(Canvas canvas, Paint paint) {
        // 进度
        float pro = (float) progress / (float) mProgressMax;

        // 计算左上右下坐标
        float startX = mLineHeight / 2;
        float startY = mViewHeight / 2;
        float endX = (mViewWidth - mTextWidth) * pro;
        float endY = startY;

        if (startX > endX){
            return;
        }
        // 绘制直线
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    /**
     * 画绿色进度条线条
     *
     * @return
     */
    private void onDrawUnfinishedProgres(Canvas canvas, Paint paint) {
        // 进度
        float pro = (float) progress / (float) mProgressMax;

        paint.setColor(mUnfinishedColor);

        // 计算左上右下坐标
        float startX = (mViewWidth - mTextWidth) * pro + mTextWidth;
        float startY = mViewHeight / 2;
        float endX = mViewWidth - mLineHeight / 2;
        float endY = startY;

        if (startX > endX){
            return;
        }
        // 绘制直线
        canvas.drawLine(startX, startY, endX, endY, paint);
    }
}
