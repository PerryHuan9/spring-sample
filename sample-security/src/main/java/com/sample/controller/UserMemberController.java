package com.sample.controller;

import com.sample.controller.exception.InvalidParameter;
import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;
import com.sample.mbg.model.User;
import com.sample.service.UserMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "User", description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserMemberController {

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

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

    @ApiOperation("获取用户所有角色")
    @GetMapping("/roles")
    public List<Role> getRoles(Long userId) {
        if (userId == null) {
            throw new InvalidParameter("userID不能为空");
        }
        return userMemberService.getUserRoles(userId);
    }

    @ApiOperation("获取用户所有权限")
    @GetMapping("/permissions")
    public List<Permission> getPermissions(Long userId) {
        if (userId == null) {
            throw new InvalidParameter("userID不能为空");
        }
        return userMemberService.getUserPermissions(userId);
    }


    @ApiOperation("注册新用户")
    @PostMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "form")
    })
    public boolean register(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidParameter("username 或 password不能为空");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMemberService.register(user);
        return user.getId() != null;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "form")
    })
    public String login(String username, String password) {
        if (username == null || password == null) {
            throw new InvalidParameter("username 或 password不能为空");
        }
        String token = userMemberService.login(username, password);
        return tokenPrefix + token;
    }


}
