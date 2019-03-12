package com.bamboy.bamboycollected.page.expresscard.viewpager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * 出场动画工具类
 * <p>
 * Created by Bamboy on 2019/3/6.
 */
public class UtilShowAnim {
    /**
     * 动画类型 =》逐一
     */
    public final static int ANIM_TYPE_INPROPERORDER = 1;
    /**
     * 动画类型 =》展开
     */
    public final static int ANIM_TYPE_UNFOLD = 2;
    /**
     * 动画类型 =》旋转
     */
    public final static int ANIM_TYPE_ROTATION = 3;

    /**
     * ViewPager对象
     */
    private ViewPager mViewPager;

    public UtilShowAnim(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    /**
     * 卡片动画
     *
     * @param animType 动画类型
     *                 【ANIM_TYPE_INPROPERORDER：逐一】
     *                 【ANIM_TYPE_UNFOLD：展开】
     *                 【ANIM_TYPE_ROTATION：旋转】
     */
    public void cardAnim(final int animType) {
        if (mViewPager == null) {
            return;
        }

        if (animType == 0) {
            return;
        }

        mViewPager.setAlpha(0);
        // 等待View加载完成，否则动画可能会受影响
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                int count = mViewPager.getChildCount();
                if (count == 0) {
                    mViewPager.setAlpha(1);
                    return;
                }

                switch (animType) {
                    case ANIM_TYPE_INPROPERORDER:
                        // 动画效果 =》逐一
                        cardAnimInproperorder();
                        break;

                    case ANIM_TYPE_UNFOLD:
                        // 动画效果 =》展开
                        cardAnimUnfold();
                        break;

                    case ANIM_TYPE_ROTATION:
                        // 动画效果 =》旋转
                        cardAnimRotation();
                        break;
                }


                mViewPager.setAlpha(1);
            }
        });
    }

    /**
     * 动画效果 =》逐一
     */
    private void cardAnimInproperorder() {
        // 可见的卡片数量
        int offscreen = mViewPager.getOffscreenPageLimit();
        // 卡片总数量
        int count = mViewPager.getAdapter().getCount();

        for (int i = 0; i <= offscreen; i++) {

            // 越界判断
            if (i >= count) {
                return;
            }

            // 获取卡片
            View card = mViewPager.getChildAt(i);
            if (card == null) {
                return;
            }

            card.setAlpha(0);

            // 卡片透明度渐变
            ObjectAnimator animAlpha = ObjectAnimator.ofFloat(
                    card,
                    "alpha",
                    0,
                    1);
            animAlpha.setDuration(200);
            animAlpha.setStartDelay((i) * 150);
            animAlpha.start();

            // 卡片Y轴位移动画
            ObjectAnimator animTY = ObjectAnimator.ofFloat(
                    card,
                    "translationY",
                    card.getTranslationY() + 350,
                    card.getTranslationY());
            animTY.setDuration(450);
            animTY.setStartDelay((i) * 150);
            animTY.setInterpolator(new OvershootInterpolator(2));
            animTY.start();
        }
    }

    /**
     * 动画效果 =》展开
     */
    private void cardAnimUnfold() {
        // 可见的卡片数量
        int offscreen = mViewPager.getOffscreenPageLimit();
        // 卡片总数量
        int count = mViewPager.getAdapter().getCount();

        // 获取第一张卡片
        View cardOne = mViewPager.getChildAt(0);

        // 卡片透明度渐变
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(
                cardOne,
                "alpha",
                0,
                1);
        animAlpha.setDuration(200);
        animAlpha.start();

        // 卡片Y轴位移动画
        ObjectAnimator animTY = ObjectAnimator.ofFloat(
                cardOne,
                "translationY",
                cardOne.getTranslationY() + 350,
                cardOne.getTranslationY());
        animTY.setDuration(450);
        animTY.setInterpolator(new OvershootInterpolator(2));
        animTY.start();

        if (count > 1) {
            for (int i = 1; i <= offscreen; i++) {
                if (i >= count) {
                    return;
                }

                // 获取卡片
                final View card = mViewPager.getChildAt(i);
                if (card == null) {
                    return;
                }

                // 隐藏卡片，动画开始时再设为可见
                card.setVisibility(View.INVISIBLE);

                // 卡片X轴位移动画
                ObjectAnimator animTX = ObjectAnimator.ofFloat(
                        card,
                        "translationX",
                        card.getTranslationX() - card.getWidth() * 0.075f,
                        card.getTranslationX() + card.getWidth() * 0.035f,
                        card.getTranslationX());
                animTX.setDuration(350);
                animTX.setStartDelay(180 + i * 100);
                animTX.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);

                        // 卡片动画开始时，设置卡片可见
                        card.setVisibility(View.VISIBLE);
                    }
                });
                animTX.start();
            }
        }
    }

    /**
     * 动画效果 =》旋转
     */
    private void cardAnimRotation() {
        // 可见的卡片数量
        int offscreen = mViewPager.getOffscreenPageLimit();
        // 卡片总数量
        int count = mViewPager.getAdapter().getCount();

        for (int i = 0; i <= offscreen; i++) {
            if (i >= count) {
                return;
            }

            // 获取卡片
            final View card = mViewPager.getChildAt(i);
            if (card == null) {
                return;
            }
            card.setAlpha(0);

            // 卡片透明度渐变
            ObjectAnimator animAlpha = ObjectAnimator.ofFloat(
                    card,
                    "alpha",
                    0,
                    1);
            animAlpha.setDuration(200);
            animAlpha.setStartDelay(i * 150);
            animAlpha.start();

            // 卡片旋转动画
            ObjectAnimator animTY = ObjectAnimator.ofFloat(
                    card,
                    "rotation",
                    card.getRotation() - 30,
                    card.getRotation());
            animTY.setDuration(450);
            animTY.setStartDelay(i * 150);
            animTY.setInterpolator(new OvershootInterpolator(2));
            animTY.start();

        }
    }

}
