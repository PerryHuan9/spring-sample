package com.sample.service;

import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;
import com.sample.mbg.model.User;

import java.util.List;

public interface UserMemberService {

    /**
     * 获取用户角色
     *
     * @param userId
     * @return
     */
    List<Role> getUserRoles(long userId);


    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    List<Permission> getUserPermissions(long userId);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 获取验证码
     *
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 验证验证码
     *
     * @param telephone
     * @param authCode
     * @return
     */
    boolean verifyAuthCode(String telephone, String authCode);


    /**
     * 注册新用户
     */
    User register(User user);


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

}
