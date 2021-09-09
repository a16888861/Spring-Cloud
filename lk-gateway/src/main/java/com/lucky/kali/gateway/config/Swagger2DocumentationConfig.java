package com.lucky.kali.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
@Component
@Primary
@Slf4j
public class Swagger2DocumentationConfig implements SwaggerResourcesProvider {

//    private final RouteLocator routeLocator;
//
//    public Swagger2DocumentationConfig(RouteLocator routeLocator) {
//        this.routeLocator = routeLocator;
//    }
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        Flux<Route> routes = routeLocator.getRoutes();
//        /*在这里遍历的时候，可以排除掉敏感微服务的路由*/
//        routes.toStream().forEach(route -> resources.add(swaggerResource(route.getId(), route.getUri().toString().replace("**", "v2/api-docs"), "1.0")));
//        return resources;
//    }
//
//
//    /**
//     * swagger新建项目
//     *
//     * @param name     名称
//     * @param location 地址
//     * @param version  版本号
//     * @return 新建的地址
//     */
//    private SwaggerResource swaggerResource(String name, String location, String version) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion(version);
//        return swaggerResource;
//    }

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public Swagger2DocumentationConfig(RouteLocator routeLocator,GatewayProperties gatewayProperties){
        this.routeLocator=routeLocator;
        this.gatewayProperties=gatewayProperties;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(route -> {
            route.getPredicates().stream()
                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                    .replace("**", "v2/api-docs"))));
        });

        return resources;
    }

    /**
     * swagger新建项目
     *
     * @param name     名称
     * @param location 地址
     * @return 新建的地址
     */
    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}",name,location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("1.0");
        return swaggerResource;
    }
}
