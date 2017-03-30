package com.bamboy.bamboycollected.page.main;

/**
 * Activity的Bean
 * <p>
 * 用于在主页展示
 * <p>
 * Created by Bamboy on 2017/3/27.
 */
public class MainBean {

    private String text;
    private Class startClass;

    public MainBean(String text, Class startClass) {
        this.text = text;
        this.startClass = startClass;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Class getStartClass() {
        return startClass;
    }

    public void setStartClass(Class startClass) {
        this.startClass = startClass;
    }

}
