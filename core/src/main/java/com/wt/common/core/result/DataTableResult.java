package com.wt.common.core.result;

import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.result
 * @Description: DataTable组件结果
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午3:31
 * @Version: v1.0
 */
public class DataTableResult<T> extends QueryResult<T> {

    private long draw;

    private long recordsFiltered;

    public DataTableResult() {

    }

    public DataTableResult(List<T> data) {
        super(data);
    }

    public long getDraw() {
        return draw;
    }

    public DataTableResult<T> setDraw(long draw) {
        this.draw = draw;
        return this;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public DataTableResult<T> setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
        return this;
    }
}
