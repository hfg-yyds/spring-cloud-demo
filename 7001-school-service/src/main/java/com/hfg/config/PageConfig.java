package com.hfg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Zero
 * @Date: 2022/4/1 16:14
 * @Description:
 */
@Configuration
@MapperScan("com.hfg.mapper")
public class PageConfig {
    //MybatisPlus分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    //PageHelper分页插件
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
