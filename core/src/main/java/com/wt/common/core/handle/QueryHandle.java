package com.wt.common.core.handle;

import com.wt.common.core.configuration.PageConfiguration;
import com.wt.common.core.dao.MapperSupport;
import com.wt.common.core.util.WebContextUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.handle
 * @Description: 查询帮助类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午3:47
 * @Version: v1.0
 */
public class QueryHandle {

    private static final String AND = "and";
    private static final String OR = "or";

    //页面开始的位置
    private int start;
    //页面大小
    private int pageSize;

    //排序字段
    //【知识点】，jdk自带的链式容器
    private LinkedHashMap<String, String> orderBy = new LinkedHashMap();

    //表连接语句
    private LinkedList<SqlJoinHandle> sqlJoinHandles = new LinkedList<>();

    //查询辅助
    private LinkedList<OperatorHandle> operatorHandles = new LinkedList<>();

    private Map<String, Object> expendData = new HashMap<>();


    //是否排序
    //【知识点】，原始类型和封装类型的区别，原始类型有初始值，封装类型没有初始值，初始值是null
    private Boolean distinct;

    private List<String> whereSql = new ArrayList<>();


    /**
     * 获取表格服务，接收分页信息
     */
    public void pageConfig() {
        PageConfiguration.create().convert(this, WebContextUtils.getRequest());
    }


    /**
     * 添加表连接
     *
     * @param joinColumn 新连接表要查询出的列
     * @param joinType   连接类型
     * @param joinSql    连接语句
     * @return
     */
    public QueryHandle joinHandle(String joinColumn, SqlJoinHandle.JoinType joinType, String joinSql) {
        Assert.isTrue(StringUtils.isEmpty(joinSql) || joinType == null, "参数错误，joinType==null或者joinSql为空");
        sqlJoinHandles.add(new SqlJoinHandle(joinColumn, joinType, joinSql));
        return this;
    }

    /**
     * 高级查询
     *
     * @return
     */
    public QueryHandle seniorSearch() {
        this.joinColumnSearch().dateTimeRangeSearch();
        return this;
    }


    private QueryHandle joinColumnSearch() {
        this.whereSql(generateJoinColumnSql());
        return this;
    }

    private QueryHandle dateTimeRangeSearch() {
        this.whereSql(generateDateTimeRangeSql());
        return this;
    }

    /**
     * 拼接多列模糊查询sql语句
     *
     * @return sql语句
     */
    private String generateJoinColumnSql() {
        //【知识点】，当涉及到拼接字符串的时候，应该使用StringBuilder优化性能
        StringBuilder sql = new StringBuilder();
        String joinColumns = WebContextUtils.getRequest().getParameter("_joinColumns"),
                joinColumnsValue = WebContextUtils.getRequest().getParameter("_joinColumnsValue");

        Assert.isTrue(StringUtils.isEmpty(joinColumns) || StringUtils.isEmpty(joinColumnsValue),
                "参数异常-------->前台传递的_joinColumns||_joinColumnsValue参数为空");

        String[] columns = joinColumns.split(",");

        this.expandData("_joinColumnsValue", joinColumnsValue);

        for (String column : columns) {
            concatColumnSql(sql, AND, column);
        }
        //【知识点】，replaceFirst的使用
        return "("+sql.toString().replaceFirst(AND,"")+")";
    }

    private String generateDateTimeRangeSql(){
        HttpServletRequest request = WebContextUtils.getRequest();

        StringBuilder sql = new StringBuilder();
        String columns = WebContextUtils.getRequest().getParameter("dateRangeColumnNames");

        String[] columnNames = columns.split(",");

        for(String columnName:columnNames){
            String _beginKey = columnName+"_begin",
            _endKey = columnName+"_end",
            _beginValue = request.getParameter(_beginKey),
            _endValue = request.getParameter(_beginKey);

            this.expandData(_beginKey,_beginValue);
            this.expandData(_endKey,_endValue);
            concatDateTimeSql(sql,AND,columnName,_beginKey,_endKey);
        }

        return "("+sql.toString().replaceFirst(AND,"")+")";

    }

    //【知识点】，mysql的CONCAT函数使用，String.join的使用。通过它们使代码更加清晰
    private void concatColumnSql(StringBuilder sql, String expression, String columnName) {
        sql.append(expression)
                .append(" t." + columnName)
                .append(" like CONCAT('%',#{")
                .append(String.join(".", MapperSupport.Data, "_joinColumnsValue"))
                .append("},'%'");
    }

    private void concatDateTimeSql(StringBuilder sql,String expression,String columnName,String beginKey,String endKey){
        sql.append(expression)
                .append(" (t."+columnName)
                .append(">="+String.join(".",MapperSupport.Data,beginKey))
                .append(" AND t."+columnName)
                .append("<"+String.join(".",MapperSupport.Data,endKey))
                .append(")");
    }


    /**
     * 去重
     *
     * @return
     */
    public QueryHandle distinct() {
        this.distinct = true;
        return this;
    }

    /**
     * 添加where查询语句
     *
     * @param sql
     * @return
     */
    public QueryHandle whereSql(String sql) {
        this.whereSql.add(sql);
        return this;
    }

    /**
     * 添加字段辅助查询
     *
     * @param column      字段
     * @param operateType 操作类型
     * @param values      值
     * @return
     */
    public QueryHandle operatorHandle(String column, OperatorHandle.OperateType operateType, Object... values) {
        Assert.isTrue(operateType == null || StringUtils.isEmpty(column), "参数错误，column或者operateType不能为空");
        operatorHandles.add(new OperatorHandle(column, operateType, values));
        return this;
    }

    /**
     * 添加查询参数
     *
     * @param key
     * @param value
     * @return
     */
    public QueryHandle expandData(String key, Object value) {
        expendData.put(key, value);
        return this;
    }

    /**
     * 添加查询参数
     *
     * @param expendData
     * @return
     */
    public QueryHandle expandData(Map<String, Object> expendData) {
        this.expendData = expendData;
        return this;
    }

    public LinkedHashMap<String, String> getOrderBy() {
        return orderBy;
    }

    public LinkedList<SqlJoinHandle> getSqlJoinHandles() {
        return sqlJoinHandles;
    }

    public LinkedList<OperatorHandle> getOperatorHandles() {
        return operatorHandles;
    }

    public Map<String, Object> getExpendData() {
        return expendData;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public List<String> getWhereSql() {
        return whereSql;
    }

    public int getStart() {
        return start;
    }

    public QueryHandle setStart(int start) {
        this.start = start;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public QueryHandle setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
