package com.sample.controller.exception;

import com.sample.controller.common.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ThrowableHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ApiResult handleThrowable() {
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
