package com.bamboy.bamboycollected.page.progress.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.bamboy.bamboycollected.R;
import com.bamboy.bamboycollected.page.progress.progress.BaseProgress;

/**
 * 圆形进度条
 * <p>
 * Created by Bamboy on 2018/10/18.
 */
public class SlopeProgress extends BaseProgress {
    /**
     * 图像类型 ==》 没有图像
     */
    public final static int IMGTYPE_NOT = 0;
    /**
     * 图像类型 ==》 对号
     */
    public final static int IMGTYPE_SUCCESS = 1;
    /**
     * 图像类型 ==》 叉号
     */
    public final static int IMGTYPE_ERROR = 2;
    /**
     * 图像类型 ==》 数字进度
     */
    public final static int IMGTYPE_PROGRESS = 3;
    /**
     * 图像类型 ==》 自定义图片
     */
    public final static int IMGTYPE_PICTURE = 4;

    /**
     * 圆环颜色
     */
    private int ringColor;
    /**
     * 直径
     */
    private float diam = 100;
    /**
     * View边缘与圆圈的距离
     */
    private int viewPadding = 0;
    /**
     * 线条
     */
    private float lineWidth = -1;
    /**
     * 图片类型
     */
    private int mImgType;
    /**
     * 图片资源
     */
    private int mPictureSrc = -1;
    /**
     * 图片
     */
    private Bitmap pictureBitmap;
    /**
     * 图片 与 圆圈的间距
     */
    private int mPictureMargin = -1;

    public SlopeProgress(Context context) {
        super(context);

        ringColor = ContextCompat.getColor(context, R.color.colorPrimary);
    }

    public SlopeProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public SlopeProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) {
            mProgressMax = 100;
            ringColor = 0xFF009E96;
            return;
        }

        // -------------------- 获取自定义属性 --------------------
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlopeProgressBar);
        lineWidth = typedArray.getDimension(R.styleable.SlopeProgressBar_lineWidth, -1);
        progress = typedArray.getInteger(R.styleable.SlopeProgressBar_progress, 0);
        mProgressMax = typedArray.getInteger(R.styleable.SlopeProgressBar_progressMax, 100);
        ringColor = typedArray.getColor(R.styleable.SlopeProgressBar_color, 0xFF009E96);
        mImgType = typedArray.getColor(R.styleable.SlopeProgressBar_imgType, 0);
        mPictureSrc = typedArray.getResourceId(R.styleable.SlopeProgressBar_pictureSrc, -1);
        mPictureMargin = typedArray.getInteger(R.styleable.SlopeProgressBar_pictureMargin, -1);

        this.post(new Runnable() {
            @Override
            public void run() {
                // 更新UI
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int diameter = Math.min(width, height);

        lineWidth = lineWidth == -1 ? diameter * 0.13f : lineWidth;
        diam = diameter - lineWidth * 2;

        if (mImgType == IMGTYPE_PICTURE) {
            float length = Math.min(width, height);
            // 计算内边距
            viewPadding = (int) ((length - diam) / 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画圆圈内的图像
        switch (mImgType) {
            case IMGTYPE_SUCCESS:
                // 画对号
                onDrawSuccess(canvas);
                break;
            case IMGTYPE_ERROR:
                // 画叉号
                onDrawError(canvas);
                break;
            case IMGTYPE_PROGRESS:
                // 画数字
                onDrawNumber(canvas);
                break;
            case IMGTYPE_PICTURE:
                // 画图片
                onDrawPicture(canvas);
                break;
            default:
                break;
        }

        // 画圆环
        onDrawRound(canvas);
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
        paint.setColor(ringColor);
        // 设置线条圆角
        paint.setStrokeCap(Paint.Cap.ROUND);

        return paint;
    }

    /**
     * 画圆圈
     *
     * @param canvas
     */
    private void onDrawRound(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float length = Math.min(width, height);
        // 进度
        float pro = (float) progress / (float) mProgressMax;
        // 旋转角度
        float startAngle = 180 - (pro * 180);
        // 圆环进度
        float sweepAngle = pro * 360;
        // 计算内边距
        viewPadding = (int) ((length - diam) / 2);
        RectF oval = new RectF(viewPadding, viewPadding, viewPadding + diam, viewPadding + diam);

        // 声明画笔
        Paint paint = initPaint();
        // 设置描边
        paint.setStyle(Paint.Style.STROKE);
        // 设置画笔的宽度
        paint.setStrokeWidth(lineWidth);
        // 绘制圆圈
        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
    }

    /**
     * 画数字
     *
     * @param canvas
     */
    private void onDrawNumber(Canvas canvas) {
        // 进度
        float pro = (float) progress / (float) mProgressMax;

        // 声明画笔
        Paint paint = initPaint();
        // 修改透明度
        paint.setAlpha(pro == 1 ? 255 : (int) (pro / 1.8 * 255));
        // 设置实心
        paint.setStyle(Paint.Style.FILL);
        //设置字体大小
        paint.setTextSize(diam / 2);
        //设置字体类型
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        //设置水平居中
        paint.setTextAlign(Paint.Align.CENTER);

        // 绘制图片
        Bitmap bitmap = createTextBitmap(
                paint,
                progress + "",
                (int) (diam * 0.2f));
        if (bitmap == null) {
            return;
        }

        mPictureMargin = (int) (lineWidth / 3);
        float textMaxLength = Math.max(bitmap.getWidth(), bitmap.getHeight());
        float scale = (diam - lineWidth - mPictureMargin * 2) / textMaxLength;
        //精确缩放到指定大小
        bitmap = Bitmap.createScaledBitmap(
                bitmap,
                (int) (bitmap.getWidth() * scale),
                (int) (bitmap.getHeight() * scale),
                true);


        // 图片的上下左右坐标
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int length = Math.min(width, height);
        float imgleft = (length - bitmap.getWidth()) / 2;
        float imgTop = (length - bitmap.getHeight()) / 2;
        float imgright = imgleft + bitmap.getWidth();
        float imgbootom = imgTop + bitmap.getHeight();

        // 设置图片坐标
        RectF rectF = new RectF(imgleft, imgTop, imgright, imgbootom);

        // 绘制图片
        canvas.drawBitmap(bitmap, null, rectF, paint);
    }

    private int mTextLength = 0;
    private int mTextWidth = 0;
    private int mTextHeight = 0;
    private int mTextPadding = 0;

    /**
     * 文字绘制在图片上，并返回bitmap对象
     */
    private Bitmap createTextBitmap(Paint paint, String text, int padding) {

        if (text == null || text.length() == 0) {
            return null;
        }

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int width = rect.width();//文本的宽度
        int height = rect.height();//文本的高度

        // 如果位数变化，或者新旧宽或高差距过大，则重新计算画布宽高，
        // 否则使用上次的宽高，以避免内容跳动
        if (mTextLength != text.length() ||
                valueGapPerCenT(mTextWidth, width) > 0.2 ||
                valueGapPerCenT(mTextHeight, height) > 0.2) {

            mTextLength = text.length();

            mTextWidth = width;
            mTextHeight = height;
            mTextPadding = padding;
        }

        Bitmap bitmap = Bitmap.createBitmap(
                mTextWidth + mTextPadding,
                mTextHeight + mTextPadding,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, (mTextWidth + mTextPadding) / 2, (mTextHeight * 2 + mTextPadding) / 2, paint);

        return bitmap;
    }

    /**
     * 计算两个数值之间的差得百分比
     *
     * @param value_1
     * @param value_2
     * @return
     */
    private float valueGapPerCenT(float value_1, float value_2) {
        try {
            float gap = Math.abs(value_1 - value_2);
            float perCenT = gap / Math.max(value_1, value_2);

            return perCenT;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 画对号
     *
     * @param canvas
     */
    private void onDrawSuccess(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float length = Math.min(width, height);
        // 进度
        float pro = (float) progress / (float) mProgressMax;

        // 起点 X坐标
        float origin_X = length / 20 * 6;
        // 起点 Y坐标
        float origin_Y = length / 20 * 10.5f;

        // 第2个点 X坐标
        float dot2_X = length / 20 * 8.5f;
        // 第2个点 Y坐标
        float dot2_Y = length / 20 * 13f;

        // 第3个点 X坐标
        float dot3_X = length / 20 * 13.5f;
        // 第3个点 Y坐标
        float dot3_Y = length / 20 * 7.4f;

        // 声明一条折线
        Path path = new Path();
        // 设置起点的 X 和 Y 坐标
        path.moveTo(origin_X, origin_Y);
        // 设置第 2 个点的 X 和 Y 坐标
        path.lineTo(dot2_X, dot2_Y);
        // 设置第 3 个点的 X 和 Y 坐标
        path.lineTo(dot3_X, dot3_Y);

        // 声明画笔
        Paint paint = initPaint();
        // 设置描边
        paint.setStyle(Paint.Style.STROKE);
        // 设置画笔的宽度
        paint.setStrokeWidth(lineWidth * 0.7f);
        // 修改透明度
        paint.setAlpha(pro == 1 ? 255 : (int) (pro / 1.8 * 255));
        // 绘制折线
        canvas.drawPath(path, paint);

        //每隔10毫秒界面刷新
//        postInvalidateDelayed(10);

    }

    /**
     * 画叉号
     *
     * @param canvas
     */
    private void onDrawError(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float length = Math.min(width, height);
        // 进度
        float pro = (float) progress / (float) mProgressMax;

        // 线距离边缘的距离，值越小，叉号越大，值范围在 0 ~ 0.5 之间
        float linePadding = 0.373f;

        // 第一根直线的【起点/终点】坐标
        float lineStartX_1 = length * linePadding;
        float lineStartY_1 = length * linePadding;
        float lineEndX_1 = length * (1 - linePadding);
        float lineEndY_1 = length * (1 - linePadding);

        // 第二根直线的【起点/终点】坐标
        float lineStartX_2 = length * linePadding;
        float lineStartY_2 = length * (1 - linePadding);
        float lineEndX_2 = length * (1 - linePadding);
        float lineEndY_2 = length * linePadding;

        // 声明画笔
        Paint paint = initPaint();
        // 设置描边
        paint.setStyle(Paint.Style.STROKE);
        // 设置画笔的宽度
        paint.setStrokeWidth(lineWidth * 0.7f);
        // 修改透明度
        paint.setAlpha(pro == 1 ? 255 : (int) (pro / 1.8 * 255));
        // 绘制直线
        canvas.drawLines(new float[]{
                // 绘制一根直线 每四数字(两个点的坐标)确定一条线
                lineStartX_1, lineStartY_1, lineEndX_1, lineEndY_1,
                // 绘制第二根直线 每四数字(两个点的坐标)确定一条线
                lineStartX_2, lineStartY_2, lineEndX_2, lineEndY_2
        }, paint);

        //每隔10毫秒界面刷新
//        postInvalidateDelayed(10);
    }

    /**
     * 画图片
     *
     * @param canvas
     */
    private void onDrawPicture(Canvas canvas) {
        if (pictureBitmap == null && mPictureSrc <= 0) {
            return;
        }

        // 进度
        float pro = (float) progress / (float) mProgressMax;

        // 圆圈内的图片与圈的间距
        float pictureMargin = mPictureMargin == -1 ? lineWidth / 3 : mPictureMargin;

        // 图片的上下左右坐标
        float imgleft = viewPadding + lineWidth / 2 + pictureMargin;
        float imgTop = viewPadding + lineWidth / 2 + pictureMargin;
        float imgright = diam + lineWidth / 2 - pictureMargin;
        float imgbootom = diam + lineWidth / 2 - pictureMargin;

        // 声明图片的bitmap
        Bitmap bitmap = pictureBitmap == null ? BitmapFactory.decodeResource(getResources(), mPictureSrc) : pictureBitmap;
        // 裁剪成圆形
        bitmap = createCircleImage(bitmap, (int) imgright);
        // 设置图片坐标
        RectF rectF = new RectF(imgleft, imgTop, imgright, imgbootom);

        // 声明画笔
        Paint paint = initPaint();
        // 修改透明度
        paint.setAlpha(pro == 1 ? 255 : (int) (pro / 1.8 * 255));
        // 绘制图片
        canvas.drawBitmap(bitmap, null, rectF, paint);

        //每隔10毫秒界面刷新
//        postInvalidateDelayed(10);
    }

    /**
     * 裁剪圆形图片
     *
     * @param bitmap 处理前的图片
     * @param length 正方形画布边长
     * @return
     */
    public Bitmap createCircleImage(Bitmap bitmap, int length) {
        if (bitmap == null ||
                length <= 0 ||
                bitmap.getWidth() == 0 ||
                bitmap.getHeight() == 0) {
            return null;
        }
        // 声明正方形Bitmap
        Bitmap target = Bitmap.createBitmap(length, length, bitmap.getConfig());

        // 计算【宽和高】中小的那个值
        int minLength = Math.min(bitmap.getWidth(), bitmap.getHeight());
        // 缩放图片至画布宽高
        float scale = (float) length / (float) minLength;
        //精确缩放到指定大小
        bitmap = Bitmap.createScaledBitmap(
                bitmap,
                (int) (bitmap.getWidth() * scale),
                (int) (bitmap.getHeight() * scale),
                true);

        // 声明画笔
        Paint paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 设置对位图进行滤波处理
        paint.setFilterBitmap(true);

        // 声明与Bitmap相同尺寸的画布
        Canvas canvas = new Canvas(target);
        // 加上抗锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        // 先绘制圆形
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        // 设置画笔的混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        int left = 0 - (bitmap.getWidth() - length) / 2;
        int top = 0 - (bitmap.getHeight() - length) / 2;
        // 绘制图片
        canvas.drawBitmap(bitmap, left, top, paint);
        return target;
    }

    /**
     * 获取当前颜色
     */
    public int getRingColor() {
        return ringColor;
    }

    /**
     * 设置颜色
     */
    public void setRingColor(int ringColor) {
        if (ringColor > 0)
            this.ringColor = ringColor;

        // 更新UI
        invalidate();
    }

    /**
     * 获取线条宽度
     */
    public float getLineWidth() {
        return lineWidth;
    }

    /**
     * 设置线条宽度
     */
    public void setLineWidth(float line) {
        this.lineWidth = line;

        // 更新UI
        invalidate();
    }

    /**
     * 获取当前圈内图像类型
     *
     * @return 【IMGTYPE_NOT：没有图像】
     * 【IMGTYPE_SUCCESS：对号】
     * 【IMGTYPE_ERROR：叉号】
     * 【IMGTYPE_PROGRESS：数字进度】
     * 【IMGTYPE_PICTURE：自定义图片】
     */
    public int getImgType() {
        return mImgType;
    }

    /**
     * 设置圈内图像类型
     * 【IMGTYPE_NOT：没有图像】
     * 【IMGTYPE_SUCCESS：对号】
     * 【IMGTYPE_ERROR：叉号】
     * 【IMGTYPE_PROGRESS：数字进度】
     * 【IMGTYPE_PICTURE：自定义图片】
     */
    public void setImgType(int mImgType) {
        this.mImgType = mImgType;

        // 更新UI
        invalidate();
    }

    /**
     * 设置圈内图像类型
     *
     * @return
     */
    public void setPicture(Bitmap bitmap) {
        this.pictureBitmap = bitmap;
        setImgType(IMGTYPE_PICTURE);

        // 更新UI
        invalidate();
    }

    /**
     * 获取圆圈与图像之间的间隔
     *
     * @return
     */
    public int getPictureMargin() {
        return mPictureMargin;
    }

    /**
     * 设置圆圈与图像之间的间隔（ImgType == IMGTYPE_PICTURE 时生效）
     *
     * @return
     */
    public void setPictureMargin(int mPictureMargin) {
        this.mPictureMargin = mPictureMargin;

        // 更新UI
        invalidate();
    }
}
