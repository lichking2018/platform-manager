package com.wt.common.core.handle;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.handle
 * @Description: 表连接处理类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午11:10
 * @Version: v1.0
 */
public class SqlJoinHandle {
    public enum JoinType{
        JOIN,LEFT_JOIN,RIGHT_JOIN
    }

    private String selectColumns;

    private JoinType joinType;

    private String joinSql;

    public SqlJoinHandle(String selectColumns, JoinType joinType, String joinSql) {
        this.selectColumns = selectColumns;
        this.joinType = joinType;
        this.joinSql = joinSql;
    }

    public String getSelectColumns() {
        return selectColumns;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public String getJoinSql() {
        return joinSql;
    }
}
