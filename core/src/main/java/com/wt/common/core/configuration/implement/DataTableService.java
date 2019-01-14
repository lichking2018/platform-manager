package com.wt.common.core.configuration.implement;

import com.wt.common.core.configuration.PageConvert;
import com.wt.common.core.handle.QueryHandle;
import com.wt.common.core.result.DataTableResult;
import com.wt.common.core.result.QueryResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.configuration.implement
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/9/19 下午3:30
 * @Version: v1.0
 */
public class DataTableService<T> implements PageConvert<T>{
    @Override
    public void convert(QueryHandle queryHandle, HttpServletRequest request) {
        String start = request.getParameter("iDisplayStart");
        String length = request.getParameter("iDisplayLength");

        if(StringUtils.isNotEmpty(start)){
            //【知识点】，字符串转数字
            queryHandle.setStart(Integer.valueOf(start));
        }

        if(StringUtils.isNotEmpty(length)){
            queryHandle.setPageSize(Integer.parseInt(length));
        }
    }

    @Override
    public QueryResult<T> createQueryResult() {
        return new DataTableResult<T>();
    }

    @Override
    public QueryResult<T> createQueryResult(List<T> data) {
        return new DataTableResult<T>(data);
    }
}
