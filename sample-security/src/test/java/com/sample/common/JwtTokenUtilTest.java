package com.sample.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JwtTokenUtilTest {

    @Test
    void testGenerateToken() {
        String username = "Perry";
        String token = JwtTokenUtil.generateToken(username);
        Assertions.assertEquals(username, JwtTokenUtil.getUsernameFromToken(token));
    }

    @Test
    void testIsTokenExpired() throws InterruptedException {
        String username = "Perry";
        String token = JwtTokenUtil.generateToken(username);
        Assertions.assertFalse(JwtTokenUtil.isTokenExpired(token));
//        这里需要修改配置文件的jwt.expiration为2才能执行后面断言
//        Thread.currentThread().sleep(3000);
//        Assertions.assertTrue(JwtTokenUtil.isTokenExpired(token));

    }

}
