package com.hfg.fiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zero
 * @Date: 2022/3/31 17:31
 * @Description:
 */
@Component
@Slf4j
public class BlackListFilter implements GlobalFilter, Ordered {
    // 模拟⿊名单（实际可以去数据库或者redis中查询）
    private static List<String> blackList = new ArrayList<>();

    static {
        blackList.add("0:0:0:0:0:0:0:1"); // 模拟本机地址
    }

    /**
     * @param exchange 封装了request和response对象的上下⽂
     * @param chain  ⽹关过滤器链（包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入了第二层拦截器");
        // 从上下⽂中取出request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //从request对象中获取客户端ip
        String hostString = request.getRemoteAddress().getHostString();
        if (blackList.contains(hostString)) {
            // 决绝访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            log.debug("=====>IP:" + hostString + " 在⿊名单中，将被拒绝访问！");
            String data = "Request be denied!,你已经进入黑名单";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(wrap));
        }
        return chain.filter(exchange);
    }

    /**
     * 返回值表示当前过滤器的顺序(优先级)，数值越⼩，优先级越⾼
     * @return
     */
    @Override
    public int getOrder() {
        System.out.println("我是第二个");
        return 1;
    }
}
