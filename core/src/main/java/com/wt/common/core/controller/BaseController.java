package com.wt.common.core.controller;

import com.wt.common.core.result.HttpResultEntity;
import com.wt.common.core.result.HttpResultHandle;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: src
 * @Package: com.wt.common.core.controller
 * @Description:
 * @Author: lichking2017@aliyun.com
 * @CreateDate: 2018/4/12 下午3:20
 * @Version: v1.0
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected HttpResultEntity getSuccessResult() {
        return HttpResultHandle.getSuccessResult();
    }

    protected HttpResultEntity getSuccessResult(Object result) {
        return HttpResultHandle.getSuccessResult(result);
    }

    protected HttpResultEntity getErrorResult() {
        return HttpResultHandle.getErrorResult();
    }

    protected HttpResultEntity getErrorResult(String errorCode, String errorMessage) {
        return HttpResultHandle.getErrorResult(errorCode, errorMessage);
    }

    protected HttpResultEntity getErrorResult(String message) {
        return HttpResultHandle.getErrorResult(message);
    }

    protected HttpResultEntity getErrorResult(Object result) {
        return HttpResultHandle.getErrorResult(result);
    }

    protected HttpResultEntity getErrorResult(String errorCode, String message, Object result) {
        return HttpResultHandle.getErrorResult(errorCode, message, result);
    }

    @ExceptionHandler
    public HttpResultEntity exceptionHandler(HttpServletRequest request, Exception ex) {
        String message = StringUtils.EMPTY;
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                message = StringUtils.join(message, error.getDefaultMessage(), ",");
            }
            logger.error(message);

            message = StringUtils.stripEnd(message, ",");

        }
        return getErrorResult(HttpResultHandle.ERROR_CODE, message);
    }

}
