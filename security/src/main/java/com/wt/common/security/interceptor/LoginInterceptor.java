package com.wt.common.security.interceptor;

import com.wt.common.core.annotations.IgnoreAuth;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.core.result.HttpResultHandle;
import com.wt.common.core.util.ServletNativeObjectUtils;
import com.wt.common.security.handler.HttpSessionHandler;
import com.wt.common.security.model.SysUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.interceptor
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/16 上午8:20
 * @Version: v1.0
 */

@Component
@Order(2)
@Aspect
public class LoginInterceptor {
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public HttpResultEntity loginCheck(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ServletNativeObjectUtils.getRequest();
        SysUser loginUser = (SysUser) request.getSession().getAttribute(HttpSessionHandler.Items.LOGINUSER.name());

        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        final Method method = methodSignature.getMethod();
        boolean ignoreAuth = method.isAnnotationPresent(IgnoreAuth.class);

        if ((null == loginUser)&&!ignoreAuth) {
            return new HttpResultEntity(HttpResultHandle.HttpResultEnum.NOTLOG);
        }
        return (HttpResultEntity) pjp.proceed();
    }
}
