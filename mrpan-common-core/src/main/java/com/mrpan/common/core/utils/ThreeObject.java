package com.mrpan.common.core.utils;

import java.io.Serializable;

/**
 * Created by mrpan on 2016/11/3.
 */
public class ThreeObject implements Serializable {
    private static final long serialVersionUID = 8799104934148660255L;
    private Object first;
    private Object second;
    private Object third = "=";

    public ThreeObject() {

    }

    public ThreeObject(Object x, Object y) {
        this.first = x;
        this.second = y;
    }

    public ThreeObject(Object x, Object y, Object z) {
        this.first = x;
        this.second = y;
        this.third = z;
    }

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getSecond() {
        return second;
    }

    public void setSecond(Object second) {
        this.second = second;
    }

    public Object getThird() {
        return third;
    }

    public void setThird(Object third) {
        this.third = third;
    }

}

