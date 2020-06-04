package com.sample.controller.exception;

import com.sample.common.ApiResult;
import lombok.Data;

@Data
public class RestException extends RuntimeException {
    protected ApiResult apiResult = new ApiResult(402, "客户端错误", null);

    public RestException() {
    }

    public RestException(int code) {
        apiResult.setCode(code);
    }

    public RestException(String msg) {
        apiResult.setMsg(msg);
    }

    public RestException(int code, String msg) {
        apiResult.setCode(code);
        apiResult.setMsg(msg);
    }


}
