package com.sample.controller.exception;

public class UnauthorizedException extends RestException {
    public UnauthorizedException() {
        super(401, "未授权，请登录");
    }

    public UnauthorizedException(String msg) {
        super(401, msg);
    }
}
