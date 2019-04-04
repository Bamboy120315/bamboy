package com.bamboy.bamboycollected.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bamboy.bamboycollected.R;

/**
 * 动画工具类
 * <p/>
 * Created by Bamboy on 2017/3/24.
 */
public class UtilAnim {

    /**
     * 控件由下往上渐入
     *
     * @param view     渐渐显示的控件
     * @param duration 动画持续时间(单位毫秒)
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showPopupWindow(final View view,
                                final View view_back,
                                final int duration) {
        if (view_back == null) {
            return;
        }

        // 背景渐变动画
        final ObjectAnimator anim = ObjectAnimator.ofFloat(view_back, "Alpha", 0, 1)
                .setDuration(duration);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // 处理动画前操作
            view_back.setAlpha(0);
            view_back.setVisibility(View.VISIBLE);
            view.setAlpha(1);
            view.setVisibility(View.INVISIBLE);

            view.post(new Runnable() {
                @Override
                public void run() {
                    // 结束时半径
                    float endRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
                    // 动画执行时长
                    int duration_view = duration;

                    view.setVisibility(View.VISIBLE);
                    // 弹窗圆形动画
                    ViewAnimationUtils.createCircularReveal(view,
                            view.getWidth() / 2,
                            (int) (view.getHeight() * 0.85), 0, endRadius).setDuration(duration_view).start();

                }
            });
            // 开启背景渐变动画
            anim.start();

        } else {
            // 5.0以下执行另一套动画

            anim.setDuration(duration);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (Float) animation.getAnimatedValue();
                    if (view != null) {
                        view.setAlpha(alpha);
                        view.setTranslationY((1 - alpha) * 100);
                    }
                }
            });

            // 动画执行前操作
            view_back.setAlpha(0);
            view.setAlpha(0);
            view_back.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);

            // 动画开始
            anim.start();
        }
    }

    /**
     * 控件由下往上渐入
     *
     * @param view      渐渐显示的控件
     * @param view_back 背景遮罩
     */
    public void showPopupWindow(View view, View view_back) {
        showPopupWindow(view, view_back, 300);
    }

    /**
     * 控件由上往下渐出
     *
     * @param view      渐渐隐藏的控件
     * @param view_back 背景遮罩
     * @param duration  动画持续时间(单位毫秒)
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void hidePopupWindow(final View view,
                                final View view_back,
                                int duration) {
        if (view_back == null || view_back.getAlpha() != 1) {
            return;
        }

        // 背景渐变动画
        ObjectAnimator anim = ObjectAnimator.ofFloat(view_back, "Alpha", 1, 0)
                .setDuration(duration);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 弹窗圆形动画
            Animator animator = ViewAnimationUtils.createCircularReveal(view,
                    view.getWidth() / 2,
                    (int) (view.getHeight() * 0.85),
                    (float) Math.hypot(view.getWidth(), view.getHeight()),
                    0);
            animator.setDuration(duration);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                    view_back.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            anim.start();
            animator.start();
        } else {
            // 5.0以下执行另一套动画
            anim.setDuration(duration);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float alpha = (Float) animation.getAnimatedValue();
                    if (view != null) {
                        view.setAlpha(alpha);
                        view.setTranslationY((1 - alpha) * 100);
                    }
                    if (alpha < 0.06) {
                        if (view_back != null) {
                            view_back.setVisibility(View.GONE);
                        }
                        if (view != null) {
                            view.setVisibility(View.GONE);
                        }
                    }
                }
            });

            // 动画开始
            anim.start();
        }
    }

    /**
     * 控件由上往下渐出
     *
     * @param view      渐渐隐藏的控件
     * @param view_back 背景遮罩
     */
    public void hidePopupWindow(View view, View view_back) {
        hidePopupWindow(view, view_back, 300);
    }

    /**
     * 展开介绍
     *
     * @param view 需要展开的介绍容器
     */
    public void showIntroduce(final View view) {
        showIntroduce(view, 350);
    }

    /**
     * 展开介绍
     *
     * @param view     需要展开的介绍容器
     * @param duration 动画时长
     */
    @SuppressLint("WrongViewCast")
    public void showIntroduce(final View view, final int duration) {
        view.setAlpha(0);
        view.setVisibility(View.VISIBLE);

        // 容器的渐入动画
        view.animate().alpha(1).setDuration(duration);

        try {
            LinearLayout ll_text = view.findViewById(R.id.ll_introduce_text);
            ll_text.setAlpha(0f);
            ll_text.setY(80);

            // 介绍渐入动画
            ll_text.animate()
                    .alpha(1)
                    .y(0)
                    .setDuration(duration)
                    .setStartDelay(duration * 2 / 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏介绍
     *
     * @param view 需要隐藏的介绍容器
     */
    public void hideIntroduce(final View view) {
        hideIntroduce(view, 350);
    }

    /**
     * 隐藏介绍
     *
     * @param view     需要隐藏的介绍容器
     * @param duration 动画时长
     */
    public void hideIntroduce(final View view, final int duration) {

        try {
            LinearLayout ll_text = (LinearLayout) view.findViewById(R.id.ll_introduce_text);
            if (ll_text == null || ll_text.getAlpha() != 1) {
                return;
            }

            // 介绍隐藏动画
            ll_text.animate()
                    .alpha(0)
                    .y(80)
                    .setDuration(duration);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 容器的渐出动画
        view.animate()
                .alpha(0)
                .setDuration(duration)
                .setStartDelay(duration * 2 / 3)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.GONE);
                    }
                });

    }

}
