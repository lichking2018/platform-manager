package com.wt.common.security.interceptor;


import com.google.gson.Gson;
import com.wt.common.core.exception.BaseErrorException;
import com.wt.common.core.exception.BaseLogicException;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.core.result.HttpResultHandle;
import com.wt.common.core.util.ServletNativeObjectUtils;
import com.wt.common.security.handler.HttpSessionHandler;
import com.wt.common.security.model.SysUser;
import com.wt.common.security.model.SyslogPerformance;
import com.wt.common.security.service.SyslogPerformanceService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: syInfo
 * @Package: com.wt.common.core.interceptor
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/5/16 下午4:14
 * @Version: v1.0
 */

@Component
@Aspect
@Order(3)
public class LogInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private SyslogPerformanceService syslogPerformanceService;


    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public HttpResultEntity logRecord(ProceedingJoinPoint pjp) {
        Gson gson = new Gson();
        HttpServletRequest request = ServletNativeObjectUtils.getRequest();
        SyslogPerformance syslogPerformance = this.setLog(request);
        syslogPerformance.setParameters(gson.toJson(pjp.getArgs()));

        long startTime = System.currentTimeMillis(), endTime = 0, consume = 0;

        String requestInfo = String.format("⭐️{User-Agent:[%s],Protocol:[%s],Remote Addr:[%s],Method:[%s],uri:[%s],Cookie:[%s],operator:[%s],parameters:[%s]}⭐️",
                request.getHeader("User-Agent"), request.getProtocol(), request.getRemoteAddr(),
                request.getMethod(), request.getRequestURI(), request.getHeader("Cookie"),
                "ceshi",
                gson.toJson(pjp.getArgs()));
        try {
            logger.info(requestInfo);
            HttpResultEntity result = (HttpResultEntity) pjp.proceed();
            endTime = System.currentTimeMillis();
            return result;
        } catch (Throwable throwable) {
            endTime = System.currentTimeMillis();
            if (throwable instanceof BaseLogicException) {
                String errorMessage = ((BaseLogicException) throwable).getExceptionBody().getMessage();
                String errorCode = ((BaseLogicException) throwable).getExceptionBody().getMessage();
                logger.error(StringUtils.join(requestInfo, errorMessage), throwable);
                return HttpResultHandle.getErrorResult(errorCode, errorMessage);
            }
            if (throwable instanceof BaseErrorException) {
                logger.error(StringUtils.join(requestInfo, throwable.getMessage()), throwable);
                return HttpResultHandle.getErrorResult(HttpResultHandle.ERROR_CODE,throwable.getMessage());
            }

            logger.error(StringUtils.join(requestInfo, throwable.getMessage()), throwable);
            return HttpResultHandle.getErrorResult();

        } finally {
            consume = endTime - startTime;
            syslogPerformance.setTimeConsuming(String.valueOf(consume));
            syslogPerformanceService.save(syslogPerformance);
        }
    }

    private SyslogPerformance setLog(HttpServletRequest request) {
        SysUser currentUser = (SysUser) request.getSession().getAttribute(HttpSessionHandler.Items.LOGINUSER.name());
        SyslogPerformance syslogPerformance = new SyslogPerformance();
        syslogPerformance
                .setRemoteHost(request.getRemoteHost())
                .setRemotePort(request.getRemotePort())
                .setRequestType(request.getMethod())
                .setRequestURI(request.getRequestURI());
        if(currentUser!=null){
            syslogPerformance.setOperatorId(currentUser.getUserId()).setOperatorName(currentUser.getUserName());
        }
        return syslogPerformance;
    }
}
