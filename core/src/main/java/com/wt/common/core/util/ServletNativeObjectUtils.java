package com.wt.common.core.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description: 获取 Servlet内建对象
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/16 上午10:35
 * @Version: v1.0
 */
public class ServletNativeObjectUtils {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse(){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        response.setCharacterEncoding("UTF-8");
        return response;
    }

    public static ServletContext getContext(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
}
