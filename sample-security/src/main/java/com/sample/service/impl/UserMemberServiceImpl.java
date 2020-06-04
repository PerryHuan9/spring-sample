package com.sample.service.impl;

import com.sample.common.JwtTokenUtil;
import com.sample.controller.exception.InvalidParameter;
import com.sample.dao.UserRolePermissionRelationDao;
import com.sample.mbg.mapper.UserMapper;
import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;
import com.sample.mbg.model.User;
import com.sample.mbg.model.UserExample;
import com.sample.service.RedisService;
import com.sample.service.UserMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class UserMemberServiceImpl implements UserMemberService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRolePermissionRelationDao userRolePermissionRelationDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public List<Role> getUserRoles(long userId) {
        return userRolePermissionRelationDao.getRoleByUserId(userId);
    }

    @Override
    public List<Permission> getUserPermissions(long userId) {
        return userRolePermissionRelationDao.getPermissionByUserId(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

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

    @Override
    public User register(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            throw new InvalidParameter("该用户已经被注册");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(Instant.now().getEpochSecond());
        userMapper.insert(user);
        return user;
    }

    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new InvalidParameter("密码错误");
            }
            token = JwtTokenUtil.generateToken(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常：{}", e.getMessage());
            throw new InvalidParameter("用户名或密码错误");
        }
        return token;
    }
}
