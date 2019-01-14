package com.wt.common.core.handle;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.handle
 * @Description:查询辅助类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午11:54
 * @Version: v1.0
 */
public class OperatorHandle {
    public enum OperateType {
        IN,NOT_IN,LIKE,LESS_THAN,LESS_EQUAL_THEN,MORE_THEN,MORE_EQUAL_THEN,EQUAL,NOT_EQUAL,NOT_LIKE,IS_NULL,IS_NOT_NULL
    }

    private String column;

    private OperateType operateType;

    private Object[] values;


    public OperatorHandle(String column, OperateType operateType, Object[] values) {
        this.column = column;
        this.operateType = operateType;
        this.values = values;
    }

    public String getColumn() {
        return column;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public Object[] getValues() {
        return values;
    }
}
