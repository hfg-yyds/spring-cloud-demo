package com.hfg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Zero
 * @Date: 2022/4/5 22:43
 * @Description:主要处理用户名和密码的校验等事情
 */
@Configuration
public class SecurityConfiger extends WebSecurityConfigurerAdapter {

    //

    /**
     * 注册一个认证管理器对象到容器
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 处理用户名和密码验证事宜
     * 1、客户端传递username和password参数到认证服务器
     * 2、查询数据库  验证合法性
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //这个方法可以去关联数据库  但为了方便测试 现在先把用户信息配置到内存中去
        //实例化一个对象
        //相当于查询出来的记录  然后赋值给它
        UserDetails user = new User("admin","123456",null);

        auth.inMemoryAuthentication()
                .withUser(user)  //把用户添加进去进行校验
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 定义一个密码加密器---
     */
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  //密码不进行加密处理
    }
}
