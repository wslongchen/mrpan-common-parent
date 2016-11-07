package com.mrpan.common.core.utils;

import java.io.Serializable;

/**
 * Created by mrpan on 2016/11/3.
 */
public class FourObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object first;
    private Object second;
    private Object third = "=";
    private Object four = "and";

    public FourObject() {

    }

    public FourObject(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public FourObject(Object first, Object second, Object third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public FourObject(Object first, Object second, Object third, Object four) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.four = four;
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

    public Object getFour() {
        return four;
    }

    public void setFour(Object four) {
        this.four = four;
    }

    @Override
    public String toString() {
        return "FourObject [first=" + first + ", second=" + second + ", third="
                + third + ", four=" + four + "]";
    }
}

