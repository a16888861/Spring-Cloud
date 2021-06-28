package com.lucky.kali.business.config;

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

    @Bean(value = "BusinessAndAPI")
    public Docket createBusinessAndApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("服务提供者模块")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lucky.kali.business.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Business RESTFUL APIs(Swagger-2.0.8)")
                .description("服务提供者模块API文档")
                .contact(new Contact("Elliot","https://xstrojan.top/about/","a12888821@gmail.com"))
                .version("1.0")
                .termsOfServiceUrl("https://github.com/a16888861/Spring-Cloud")
                .build();
    }
}
