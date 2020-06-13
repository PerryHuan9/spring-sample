package com.sample.config;

import cn.hutool.json.JSONUtil;
import com.sample.common.ApiResult;
import com.sample.component.JwtAuthenticationTokenFilter;
import com.sample.dto.PermissionUserDetail;
import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;
import com.sample.mbg.model.User;
import com.sample.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserMemberService userMemberService;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf() // 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement() // 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                // 开放以下url
                .antMatchers("/user/login", "/user/register")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) //跨域请求会先进行一次options请求
                .permitAll()
                .antMatchers("/**")//测试时全部运行访问
                .permitAll()
                .anyRequest()
                .authenticated();
        httpSecurity.headers().cacheControl();
        // 在UsernamePasswordAuthenticationFilter filter之前增加jwtAuthenticationTokenFilter filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling()
                .accessDeniedHandler((request, response, authException) -> {
                    System.out.println("拒绝访问：");
                    ApiResult apiResult = new ApiResult(402, "改账号无此权限，请联系管理员", null);
                    handlerException(response, apiResult);
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    ApiResult apiResult = new ApiResult(401, "token过期或未登陆，请登录", null);
                    handlerException(response, apiResult);
                });

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userMemberService.getUserByUsername(username);
            if (user != null) {
                List<Role> roles = userMemberService.getUserRoles(user.getId());
                List<Permission> permissions = userMemberService.getUserPermissions(user.getId());
                return new PermissionUserDetail(user, roles, permissions);

            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    private void handlerException(HttpServletResponse response, ApiResult apiResult) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.println(JSONUtil.parse(apiResult));
        writer.flush();
    }
}
