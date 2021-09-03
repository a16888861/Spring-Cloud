package com.lucky.kali.gateway.controller;

import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 */
@RestController
public class IndexController {
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * buildName
     */
    @Value("${info.build.name}")
    private String buildName;
    /**
     * 获取Id
     */
    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * Index信息
     *
     * @return Index信息
     */
    @GetMapping("/")
    public ResponseInfo<List<String>> index() {
        List<String> result = new ArrayList<>();
        result.add("Welcome To The " + buildName + " ~");
        result.add("Priority of services : " + discoveryClient.getOrder());
        result.add("Service Id : " + applicationId);
        return Response.success(ResponseEnum.SUCCESS.getMessage(), result);
    }
}
