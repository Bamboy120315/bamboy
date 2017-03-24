package com.bamboy.bamboycollected.util;

/**
 * 工具箱，所有工具类均从这里取
 *
 * Created by Bamboy on 2017/3/24.
 */
public class UtilBox {

    public UtilUI ui;
    public UtilWant want;

    /**
     * 私有化构造方法
     */
    private UtilBox() {
        ui = new UtilUI();
        want = new UtilWant();
    }

    /**
     * 初始化工具箱
     */
    private static class StockRemindUtilHolder {
        private static final UtilBox mUtilBox = new UtilBox();
    }

    /**
     * 获取工具箱单例
     * @return
     */
    public static UtilBox getUtilBox(){
        return StockRemindUtilHolder.mUtilBox;
    }

}
