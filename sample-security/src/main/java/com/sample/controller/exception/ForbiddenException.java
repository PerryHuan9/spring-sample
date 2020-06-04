package com.sample.controller.exception;

public class ForbiddenException extends RestException {
    public ForbiddenException() {
        super(403, "无此权限， 禁止访问");
    }

    public ForbiddenException(String msg) {
        super(403, msg);
    }
}
