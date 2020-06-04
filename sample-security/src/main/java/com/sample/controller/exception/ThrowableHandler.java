package com.sample.controller.exception;

import com.sample.common.ApiResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ThrowableHandler {

    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public ApiResult handleThrowable(Exception e) throws AccessDeniedException {
        // AccessDeniedException异常交给security处理
        if (e instanceof AccessDeniedException) {
            throw (AccessDeniedException) e;
        }
        e.printStackTrace();
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(500);
        apiResult.setMsg("服务端内部错误");
        apiResult.setData(null);
        return apiResult;
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ApiResult handleRestException(RestException e) {
        return e.getApiResult();
    }
}
