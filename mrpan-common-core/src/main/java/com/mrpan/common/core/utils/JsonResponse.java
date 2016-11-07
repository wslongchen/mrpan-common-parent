package com.mrpan.common.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public class JsonResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private int rtState; // 0:表示正确，1：表示失败
    private String rtMsrg;
    private Object rtData;

    /** APP 返回分页信息 */
    private long totalRecord;
    private List<?> listData;
    private List<?> pageData;

    public JsonResponse() {
    }

    public JsonResponse(int rtState, String rtMsrg) {
        super();
        this.rtState = rtState;
        this.rtMsrg = rtMsrg;
    }

    public JsonResponse(int rtState, String rtMsrg, Object rtData) {
        super();
        this.rtState = rtState;
        this.rtMsrg = rtMsrg;
        this.rtData = rtData;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<?> getListData() {
        return listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }

    public int getRtState() {
        return rtState;
    }

    public void setRtState(int rtState) {
        this.rtState = rtState;
    }

    public String getRtMsrg() {
        return rtMsrg;
    }

    public void setRtMsrg(String rtMsrg) {
        this.rtMsrg = rtMsrg;
    }

    public Object getRtData() {
        return rtData;
    }

    public void setRtData(Object rtData) {
        this.rtData = rtData;
    }

    public List<?> getPageData() {
        return pageData;
    }

    public void setPageData(List<?> pageData) {
        this.pageData = pageData;
    }

}

