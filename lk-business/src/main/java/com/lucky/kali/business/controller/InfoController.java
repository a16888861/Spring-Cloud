package com.lucky.kali.business.controller;

import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Elliot
 * @date 2021-06-01 13:31
 */
@RestController
@RequestMapping("business/info")
@Slf4j
public class InfoController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取description
     * @return  description
     */
    @GetMapping("getDescription")
    public ResponseInfo<String> description(){
        return Response.success("common.response.success",discoveryClient.description());
    }
}
