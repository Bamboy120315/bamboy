package com.bamboy.bamboycollected.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bamboy.bamboycollected.base.BamboyActivity;

/**
 * Created by Bamboy on 2017/3/10.
 * 功能：使activity可以从边缘滑动关闭
 *
 * 实现原理：
 * 获取DecorView的RootView,
 * 把RootView添加到当前View
 * 再把当前View添加到DecorView
 */
public class SlideBackLayout extends FrameLayout {
    /**
     * 速度阈值【建议3~20之间，值越小，触发关闭越灵敏】
     */
    private int mSpeedThreshold = 3;
    /**
     * 当前滑动速度
     */
    private int mSpeed = 0;
    /**
     * 屏幕的宽
     */
    private int mWidth;
    /**
     * 屏幕的高
     */
    private int mHeight;
    /**
     * 当前滑动距离
     */
    private int mSlideX;
    /**
     * 画笔，用来绘制阴影效果
     */
    private Paint mPaint;

    private BamboyActivity mActivity;
    /**
     * 当前Activity的ViewGroup
     */
    private ViewGroup mViewGroup;
    /**
     * 最左侧的View
     */
    private View mLeftView;
    /**
     * Drag助手类
     */
    private ViewDragHelper mViewDragHelper;

    // ______________________________________________________________________
    // = = = = = = = = = = = 以 下 是 关 于 初 始 化 = = = = = = = = = = =

    public SlideBackLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        //必须是传入Activity
        mActivity = (BamboyActivity) context;
        //构造ViewDragHelper
        mViewDragHelper = ViewDragHelper.create(this, new DragCallback());
        //设置从左边缘捕捉View
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);

        //初始化画笔
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
    }

    /**
     * 绑定ViewGroup
     */
    public void bind() {
        mViewGroup = (ViewGroup) mActivity.getWindow().getDecorView();
        mLeftView = mViewGroup.getChildAt(0);
        mViewGroup.removeView(mLeftView);
        this.addView(mLeftView);
        mViewGroup.addView(this);

        //计算屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    // ______________________________________________________________________
    // = = = = = = = = = = = 以 下 是 关 于 画 笔 = = = = = = = = = = =

    @Override
    protected void dispatchDraw(Canvas canvas) {
        //绘制阴影,onDraw（）方法在ViewGroup中不一定会执行
        drawShadow(canvas);
        super.dispatchDraw(canvas);
    }

    private void drawShadow(Canvas canvas) {
        // 滑动百分比，以计算阴影透明度
        float slidePercentage = 1 - (float) mSlideX / (float) mWidth;
        //调整阈值
        mSpeedThreshold = 3 + (int)((1 - slidePercentage) * 5);

        // 透明的黑色阴影
        int color = Color.argb((int) (220 * slidePercentage), 0, 0, 0);

        canvas.save();
        //构造着色器
        Shader mShader = new LinearGradient(0, 0, mSlideX, 0, new int[]{color, color}, null, Shader.TileMode.REPEAT);
        //设置着色器
        mPaint.setShader(mShader);
        RectF rectF = new RectF(0, 0, mSlideX, mHeight);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();
    }

    // ______________________________________________________________________
    // = = = = = = = = = = = 以 下 是 关 于 触 摸 操 作 = = = = = = = = = = =

    @Override
    public void computeScroll() {
        //持续滚动期间，不断刷新ViewGroup
        if (mViewDragHelper.continueSettling(true))
            invalidate();
    }

    /**
     * 获取当前滑动距离
     *
     * @param slideX
     */
    public void setmSlideX(int slideX) {
        mSlideX = slideX;
    }

    /**
     * 触摸监听
     */
    class DragCallback extends ViewDragHelper.Callback {

        @Override
        /**
         * 手指按下
         */
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        /**
         * 手指抬起
         */
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //当前回调，松开手时触发，比较触发条件和当前的滑动距离
            int left = releasedChild.getLeft();
            if (mSpeed > 1 && (left > mWidth * 0.5f || mSpeed > mSpeedThreshold)) {
                //大于触发条件，滚出去...
                mViewDragHelper.settleCapturedViewAt(mWidth, 0);
            } else {
                //缓慢滑动的方法,小于触发条件，滚回去
                mViewDragHelper.settleCapturedViewAt(0, 0);
            }
            //需要手动调用更新界面的方法
            invalidate();

        }

        @Override
        /**
         * 发生改变
         */
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mSpeed = dx;
            mSlideX = left;
            //当滑动位置改变时，刷新View,绘制新的阴影位置
            invalidate();
            //当滚动位置到达屏幕最右边，则关掉Activity
            if (changedView == mLeftView && left >= mWidth) {
                mActivity.finish(false);
            }
        }

        @Override
        /**
         * X轴变化
         */
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //限制左右拖拽的位移
            left = left >= 0 ? left : 0;
            return left;
        }

        @Override
        /**
         * Y轴变化
         */
        public int clampViewPositionVertical(View child, int top, int dy) {
            //上下不能移动，返回0
            return 0;
        }

        @Override
        /**
         * 触摸边缘
         */
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //触发边缘时，主动捕捉mRootView
            if (mSlideX == 0) {
                mViewDragHelper.captureChildView(mLeftView, pointerId);
            }
        }
    }
}