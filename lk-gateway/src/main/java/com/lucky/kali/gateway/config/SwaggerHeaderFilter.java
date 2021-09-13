package com.lucky.kali.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.StringJoiner;

/**
 * @author Elliot
 */
@Component
@Slf4j
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
    private static final String HEADER_NAME = "X-Forwarded-Prefix";

    private static final String URI = "/v2/api-docs";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            String[] split = path.split("/");
            split[1] = "lk-" + path.split("/")[1];
            StringJoiner sj = new StringJoiner("/", "", "");
            for (String s : split) {
                sj.add(s);
            }
            path = sj.toString();
            /*log.info("地址:" + path);*/
            if (!StringUtils.endsWithIgnoreCase(path, URI)) {
                return chain.filter(exchange);
            }
            /*String basePath = path.substring(0, path.lastIndexOf(URI));*/
            /*ServerHttpRequest newRequest = request.mutate().path(path).header(HEADER_NAME, basePath).build();*/
            ServerHttpRequest newRequest = request.mutate().path(path).build();
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            return chain.filter(newExchange);
        };
    }
}