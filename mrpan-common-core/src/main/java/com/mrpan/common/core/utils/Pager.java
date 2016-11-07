package com.mrpan.common.core.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mrpan on 2016/11/3.
 */
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DEF_COUNT = 20;

    protected long totalCount = 0;
    protected int pageSize = 20;
    protected int pageNo = 1;

    /**
     * 当前页的数据
     */
    private List<?> datas;

    /**
     * 检查页码 checkPageNo
     *
     * @param pageNo
     * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
     */
    public static int cpn(Integer pageNo) {
        return (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    public Pager() {
    }

    /**
     * 构造器
     *
     * @param pageNo
     *            页码
     * @param pageSize
     *            每页几条数据
     * @param totalCount
     *            总共几条数据
     */
    public Pager(int pageNo, int pageSize, long totalCount, List<?> datas) {
        setTotalCount(totalCount);
        setPageSize(pageSize);
        setPageNo(pageNo);
        adjustPageNo();
        this.datas = datas;
    }

    /**
     * 调整页码，使不超过最大页数
     */
    public void adjustPageNo() {
        if (pageNo == 1) {
            return;
        }
        int tp = getTotalPage();
        if (pageNo > tp) {
            pageNo = tp;
        }
    }

    /**
     * 获得页码
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 每页几条数据
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 总共几条数据
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 总共几页
     */
    public int getTotalPage() {
        int totalPage = (int) (totalCount / pageSize);
        if (totalPage == 0 || totalCount % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }

    /**
     * 是否第一页
     */
    public boolean isFirstPage() {
        return pageNo <= 1;
    }

    /**
     * 是否最后一页
     */
    public boolean isLastPage() {
        return pageNo >= getTotalPage();
    }

    /**
     * 下一页页码
     */
    public int getNextPage() {
        if (isLastPage()) {
            return pageNo;
        } else {
            return pageNo + 1;
        }
    }

    /**
     * 上一页页码
     */
    public int getPrePage() {
        if (isFirstPage()) {
            return pageNo;
        } else {
            return pageNo - 1;
        }
    }

    /**
     * if totalCount<0 then totalCount=0
     *
     * @param totalCount
     */
    public void setTotalCount(Long totalCount) {
        if (totalCount < 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }
    }

    /**
     * if pageSize< 1 then pageSize=DEF_COUNT
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = DEF_COUNT;
        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * if pageNo < 1 then pageNo=1
     *
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }
    }

    /**
     * 第一条数据位置
     *
     * @return
     */
    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 获得分页内容
     *
     * @return
     */
    public List<?> getDatas() {
        return datas;
    }

    /**
     * 设置分页内容
     *
     * @param datas
     */
    public void setDatas(List<?> datas) {
        this.datas = datas;
    }
}
