package com.hfg.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Zero
 * @Date: 2022/3/31 12:35
 * @Description:
 */
public class AutoDeliverConfig {
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new
                HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new
                ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
