package com.sample.service.impl;

import com.sample.service.RedisService;
import com.sample.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserMemberServiceImpl implements UserMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        String authCode = stringBuilder.toString();
        redisService.set(key, authCode);
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
        return authCode;
    }

    @Override
    public boolean verifyAuthCode(String telephone, String authCode) {
        if (telephone == null || authCode == null) {
            return false;
        }
        String redisAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        System.out.println("redisAuthCode:" + redisAuthCode);
        System.out.println("authCode:" + authCode);
        return authCode.equals(redisAuthCode);
    }
}
