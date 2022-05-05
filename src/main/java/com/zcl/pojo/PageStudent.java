package com.zcl.pojo;

import java.util.List;

/**
 * 分页条件查询数据返回
 */
public class PageStudent<T> {
    // 总数记录
    private int totalCiunt;

    // 当前页数据
    private List<T> rows;

    public int getTotalCiunt() {
        return totalCiunt;
    }

    public void setTotalCiunt(int totalCiunt) {
        this.totalCiunt = totalCiunt;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
