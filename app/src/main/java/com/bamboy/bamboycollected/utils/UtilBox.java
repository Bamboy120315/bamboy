package com.bamboy.bamboycollected.utils;

/**
 * 工具箱，所有工具类均从这里取
 * <p>
 * Created by Bamboy on 2017/3/24.
 */
public class UtilBox {

    /**
     * UI工具类
     */
    public UtilUI ui;
    /**
     * 基础工具类
     */
    public UtilWant want;
    /**
     * 动画工具类
     */
    public UtilAnim anim;
    /**
     * Log工具类
     */
    public UtilLog log;
    /**
     * 手机信息工具类
     */
    public UtilInfo info;
    /**
     * 图像工具类
     */
    public UtilBitmap bitmap;

    /**
     * 私有化构造方法
     */
    private UtilBox() {
        // 先初始化几个急需的工具类
        ui = new UtilUI();
        want = new UtilWant();
        info = new UtilInfo();

        // 然后再初始化非急需的工具类
        initBox();
    }

    /**
     * 加载工具箱
     */
    public void initBox() {
        // 子线程加载工具类
        new Thread(new Runnable() {
            public void run() {
                anim = new UtilAnim();
                log = new UtilLog();
                bitmap = new UtilBitmap();
            }
        }).start();
    }

    /**
     * 初始化工具箱
     */
    private static class StockRemindUtilHolder {
        private static final UtilBox mUtilBox = new UtilBox();
    }

    /**
     * 获取工具箱单例
     *
     * @return
     */
    public static UtilBox getBox() {
        return StockRemindUtilHolder.mUtilBox;
    }

}
