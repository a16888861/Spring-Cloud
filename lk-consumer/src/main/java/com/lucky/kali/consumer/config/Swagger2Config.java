package com.lucky.kali.consumer.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * swagger配置类
 *
 * @author Elliot
 * @date 2021-06-24 11:47
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Value("${knife4j.enable}")
    public boolean knife4jEnable;

    @Bean(value = "服务消费者模块")
    public Docket api1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(knife4jEnable)
                /*分组名称(要和网关的路由id一致)*/
                .groupName("服务消费者模块")
                .select()
                //扫描全部
//                .apis(RequestHandlerSelectors.any())
                //采用包扫描的方式来确定要显示的接口
//                .apis(RequestHandlerSelectors.basePackage("com.lucky.kali.consumer.controller"))
                //采用包含注解的方式来确定要显示的接口(两种方式：根据类注释和根据方法注释，看情况选择)
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //扫描全部
                .paths(PathSelectors.any())
                //扫描指定
//                .paths(PathSelectors.regex("/*"))
                .build();
    }
//    @Bean(value = "用户信息")
//    public Docket api2() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                //分组名称
//                .groupName("用户信息")
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                //扫描全部
//                .paths(PathSelectors.regex("/consumer/user/*"))
//                .build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Consumer RESTFUL APIs(Swagger-2.0.8)")
                .description("服务消费者模块API文档")
                .contact(new Contact("Elliot", "https://xstrojan.top/about/", "a12888821@gmail.com"))
                .version("1.0")
                .termsOfServiceUrl("https://github.com/a16888861/Spring-Cloud")
                .build();
    }
}
