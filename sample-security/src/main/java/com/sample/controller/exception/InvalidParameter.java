package com.sample.controller.exception;

public class InvalidParameter extends RestException {
    public InvalidParameter() {
        super("参数错误");
    }

    public InvalidParameter(String msg) {
        super(msg);
    }
}
