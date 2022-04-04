package com.hfg.fiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Zero
 * @Date: 2022/3/31 17:38
 * @Description:
 */
@Component
@Slf4j
public class TestFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getRemoteAddress().getHostString());
        System.out.println("进入了第一层拦截器");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        System.out.println("我是第一个");
        return 0;
    }
}
