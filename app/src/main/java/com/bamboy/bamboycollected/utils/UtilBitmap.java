package com.bamboy.bamboycollected.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.widget.ImageView;

/**
 * 图片工具类
 * <p>
 * Created by Bamboy on 2017/04/01.
 */
public class UtilBitmap {
    /**
     * 图片缩放比例
     */
    private final float BITMAP_SCALE = 0.2f;

    /**
     * 将Drawable对象转化为Bitmap对象
     *
     * @param drawable Drawable对象
     * @return 对应的Bitmap对象
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        //如果本身就是BitmapDrawable类型 直接转换即可
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        //取得Drawable固有宽高
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            //创建一个1x1像素的单位色图
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            //直接设置一下宽高和ARGB
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        //重新绘制Bitmap
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * 模糊ImageView
     *
     * @param context
     * @param img     ImageView
     * @param level   模糊等级【0 ~ 25之间】
     */
    public void blurImageView(Context context, ImageView img, float level) {
        // 将图片处理成模糊
        Bitmap bitmap = blurBitmap(context, drawableToBitmap(img.getDrawable()), level);
        if (bitmap != null) {
            img.setImageBitmap(bitmap);
        }
    }

    /**
     * 模糊ImageView
     *
     * @param context
     * @param img     ImageView
     * @param level   模糊等级【0 ~ 25之间】
     * @param color   为ImageView蒙上一层颜色
     */
    public void blurImageView(Context context, ImageView img, float level, int color) {
        // 将图片处理成模糊
        Bitmap bitmap = blurBitmap(context, drawableToBitmap(img.getDrawable()), level);
        if (bitmap != null) {
            Drawable drawable = coverColor(context, bitmap, color);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageDrawable(drawable);
        } else {
            img.setImageBitmap(null);
            img.setBackgroundColor(color);
        }
    }

    /**
     * 将bitmap转成蒙上颜色的Drawable
     *
     * @param context
     * @param bitmap
     * @param color   要蒙上的颜色
     * @return Drawable
     */
    public Drawable coverColor(Context context, Bitmap bitmap, int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        new Canvas(bitmap).drawRoundRect(rect, 0, 0, paint);
        return new BitmapDrawable(context.getResources(), bitmap);
    }


    /**
     * 模糊图片的具体方法
     *
     * @param context 上下文对象
     * @param bitmap  需要模糊的图片
     * @return 模糊处理后的图片
     */
    public Bitmap blurBitmap(Context context, Bitmap bitmap, float blurRadius) {
        if (blurRadius < 0) {
            blurRadius = 0;
        }
        if (blurRadius > 25) {
            blurRadius = 25;
        }
        Bitmap outputBitmap = null;
        try {

            Class.forName("android.renderscript.ScriptIntrinsicBlur");
            // 计算图片缩小后的长宽
            int width = Math.round(bitmap.getWidth() * BITMAP_SCALE);
            int height = Math.round(bitmap.getHeight() * BITMAP_SCALE);
            if (width < 2 || height < 2) {
                return null;
            }

            // 将缩小后的图片做为预渲染的图片。
            Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
            // 创建一张渲染后的输出图片。
            outputBitmap = Bitmap.createBitmap(inputBitmap);

            // 创建RenderScript内核对象
            RenderScript rs = RenderScript.create(context);
            // 创建一个模糊效果的RenderScript的工具对象
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

            // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间。
            // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去。
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

            // 设置渲染的模糊程度, 25f是最大模糊度
            blurScript.setRadius(blurRadius);
            // 设置blurScript对象的输入内存
            blurScript.setInput(tmpIn);
            // 将输出数据保存到输出内存中
            blurScript.forEach(tmpOut);

            // 将数据填充到Allocation中
            tmpOut.copyTo(outputBitmap);

            return outputBitmap;
        } catch (Exception e) {
            Log.e("Bemboy_Error", "Android版本过低");
            return null;
        }
    }
}
