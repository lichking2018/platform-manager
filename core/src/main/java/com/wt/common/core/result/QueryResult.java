package com.wt.common.core.result;

import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.result
 * @Description: 列表分页查询结果
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午3:26
 * @Version: v1.0
 */
public class QueryResult<T> {

    public QueryResult() {
    }

    public QueryResult(List<T> data) {
        this.data = data;
    }

    private Integer recordesTotal;

    private List<T> data;

    public Integer getRecordesTotal() {
        return recordesTotal;
    }

    public QueryResult<T> setRecordesTotal(Integer recordesTotal) {
        this.recordesTotal = recordesTotal;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public QueryResult<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
