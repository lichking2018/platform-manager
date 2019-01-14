package com.wt.common.core.configuration;

import com.wt.common.core.handle.QueryHandle;
import com.wt.common.core.result.QueryResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.configuration
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午3:19
 * @Version: v1.0
 */
public interface PageConvert<T> {
    String DataTable = "datatable";
    String EasayUi = "easyui";


    /**
     * 前台表格分页信息解析
     * @param queryHandle
     * @param request
     */
    void convert(QueryHandle queryHandle, HttpServletRequest request);

    /**
     * 返回结果对象
     * @return
     */
    QueryResult<T> createQueryResult();

    /**
     * 返回结果对象，直接赋值
     * @param data
     * @return
     */
    QueryResult<T> createQueryResult(List<T> data);
}
