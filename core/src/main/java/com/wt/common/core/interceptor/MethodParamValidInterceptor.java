package com.wt.common.core.interceptor;

import com.wt.common.core.exception.BaseErrorException;
import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.core.result.HttpResultHandle;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @ProjectName: myProject
 * @Package: com.wt.common.core.interceptor
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/7/20 下午1:25
 * @Version: v1.0
 */
@Component
@Aspect
@Order(10)
public class MethodParamValidInterceptor {

    Logger logger = LoggerFactory.getLogger(MethodParamValidInterceptor.class);

    @Around(value = "@within(org.springframework.web.bind.annotation.RestController)||" +
            "@within(org.springframework.stereotype.Controller)")
    public HttpResultEntity paramsValid(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return (HttpResultEntity) pjp.proceed();
        } catch (ConstraintViolationException e) {
            String message = StringUtils.EMPTY;
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for(ConstraintViolation violation:violations){
                message = StringUtils.join(message,violation.getMessage(),",");
            }

            logger.error("入参不合法:{}",message);

            return HttpResultHandle.getErrorResult(StringUtils.stripEnd(message,","));
        }
    }


    @Around(value = "@within(org.springframework.stereotype.Service)")
    public Object serviceParamValid(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (ConstraintViolationException e) {
            String message = StringUtils.EMPTY;
            Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
            for(ConstraintViolation constraintViolation:constraintViolations){
                message = StringUtils.join(message,constraintViolation.getMessage(),",");
            }
            throw new BaseErrorException(StringUtils.stripEnd(message,","),e);
        }
    }

}
