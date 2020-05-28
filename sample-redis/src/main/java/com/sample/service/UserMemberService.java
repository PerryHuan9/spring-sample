package com.sample.service;

public interface UserMemberService {
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
}
