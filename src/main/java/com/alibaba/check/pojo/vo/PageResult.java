package com.alibaba.check.pojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类
 * @param <T>
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 7163654731288889866L;
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
