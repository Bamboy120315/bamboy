package com.bamboy.bamboycollected.base.freedom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bamboy.bamboycollected.base.freedom.manager.ManagerA;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerB;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerBamboy;
import com.bamboy.bamboycollected.base.freedom.manager.ManagerC;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * ViewHolder的管理类
 * 用于存放ViewHolder的基类
 * 和 条目类型与相对应的Bean类型
 * <p/>
 * Created by Bamboy on 2017/4/13.
 */
public class ViewHolderManager {

    /**
     * 条目类型 和 对应的条目XML
     */
    private static Map<Integer, Class> itemMap;

    /**
     * 加载条目类型，以及对应的条目XML
     */
    static {
        itemMap = new HashMap<>();

        // 添加开发人员Bamboy所创建的item集合
        itemMap.putAll(ManagerBamboy.getManager().getMap());

        // 添加第一个开发人员所创建的item集合
        itemMap.putAll(ManagerA.getManager().getMap());

        // 添加第二个开发人员所创建的item集合
        itemMap.putAll(ManagerB.getManager().getMap());

        // 添加第三个开发人员所创建的item集合
        itemMap.putAll(ManagerC.getManager().getMap());

    }

    /**
     * ViewHolder基类
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(findView(viewGroup, layoutId));
        }
    }

    /**
     * 创建ViewHolder
     *
     * @param viewGroup
     * @param viewType  当前item的类型，即上边的那一堆常量
     * @return
     */
    public static ViewHolder createViewHolder(ViewGroup viewGroup, int viewType) {
        try {
            // 获取Class对象
            Class itemClass = itemMap.get(viewType);
            // 开始反射，获取构造方法
            Constructor<?> cons[] = itemClass.getConstructors();
            // 运行方法，获取ViewHolder
            Object obj = cons[0].newInstance(viewGroup);

            return (ViewHolder) obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取View
     * <p/>
     * Ps：简化createViewHolder里的代码
     *
     * @param viewGroup
     * @param itemId
     * @return
     */
    private static View findView(ViewGroup viewGroup, int itemId) {
        return LayoutInflater.from(viewGroup.getContext())
                .inflate(itemId, viewGroup, false);
    }
}
