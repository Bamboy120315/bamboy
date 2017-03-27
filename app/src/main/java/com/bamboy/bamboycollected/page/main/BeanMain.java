package com.bamboy.bamboycollected.page.main;

/**
 * Created by Bamboy on 2017/3/27.
 */
public class BeanMain {

    private String text;
    private Class startClass;

    public BeanMain(String text, Class startClass) {
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
