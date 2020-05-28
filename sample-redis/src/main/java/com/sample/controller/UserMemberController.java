package com.sample.controller;

import com.sample.controller.exception.InvalidParameter;
import com.sample.service.UserMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "User", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserMemberController {
    @Autowired
    private UserMemberService userMemberService;

    @ApiOperation("获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "用户手机号码", paramType = "query")
    })
    @GetMapping("/authCode")
    public String getAuthCode(String telephone) {
        if (telephone == null) {
            throw new InvalidParameter("用户手机号码不能为空");
        }
        return userMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("验证验证码")
    @PostMapping("/verifyAuthCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "用户手机号码", paramType = "form"),
            @ApiImplicitParam(name = "authCode", value = "验证码", paramType = "form"),
    })
    public boolean verifyAuthCode(String telephone, String authCode) {
        if (telephone == null || authCode == null) {
            throw new InvalidParameter("telephone或authCode参数不能为null");
        }
        return userMemberService.verifyAuthCode(telephone, authCode);
    }
}
