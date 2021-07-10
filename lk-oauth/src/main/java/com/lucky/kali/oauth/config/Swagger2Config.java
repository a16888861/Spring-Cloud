package com.lucky.kali.oauth.config;

import io.swagger.annotations.ApiOperation;
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
 * @author Elliot
 * @date 2021-06-24 11:47
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Bean(value = "基本信息(鉴权)")
    public Docket api1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("基本信息(鉴权)")
                .select()
                //采用包扫描的方式来确定要显示的接口
//                .apis(RequestHandlerSelectors.basePackage("com.lucky.kali.oauth.controller"))
                //采用包含注解的方式来确定要显示的接口(两种方式：根据类注释和根据方法注释，看情况选择)
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //扫描全部
                .paths(PathSelectors.any())
                //扫描指定
//                .paths(PathSelectors.regex("/index/*"))
                .build();
    }
    @Bean(value = "xx模块")
    public Docket api2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("xx模块")
                .select()
                //采用包扫描的方式来确定要显示的接口
//                .apis(RequestHandlerSelectors.basePackage("com.lucky.kali.oauth.controller"))
                //采用包含注解的方式来确定要显示的接口(两种方式：根据类注释和根据方法注释，看情况选择)
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //扫描全部
//                .paths(PathSelectors.any())
                //扫描指定
                .paths(PathSelectors.regex("/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Business RESTFUL APIs(Swagger-2.0.8)")
                .description("鉴权模块API文档")
                .contact(new Contact("Elliot","https://xstrojan.top/about/","a12888821@gmail.com"))
                .version("1.0")
                .termsOfServiceUrl("https://github.com/a16888861/Spring-Cloud")
                .build();
    }
}
