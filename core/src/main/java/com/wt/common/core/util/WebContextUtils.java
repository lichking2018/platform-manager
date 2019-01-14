package com.wt.common.core.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.util
 * @Description: 请求工具类
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/23 上午9:32
 * @Version: v1.0
 */
public class WebContextUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) getRequestAttributes()).getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.getRequestAttributes();
    }
}
