package com.lucky.kali.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 *
 * @author Elliot
 * @date 2021-09-09 11:47
 */
@Configuration
@Primary
@Slf4j
public class Swagger2DocumentationConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public Swagger2DocumentationConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        Flux<Route> routes = routeLocator.getRoutes();
        routes.toStream().forEach(route -> log.info("dsadsadasd" + route.getId()));
        /*在这里遍历的时候，可以排除掉敏感微服务的路由*/
        routes.toStream().forEach(route -> resources.add(swaggerResource(route.getId(), route.getUri().toString().replace("**", "/doc.html"), "1.0")));
        return resources;
    }

    /**
     * swagger新建项目
     *
     * @param name     名称
     * @param location 地址
     * @param version  版本号
     * @return 新建的地址
     */
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
