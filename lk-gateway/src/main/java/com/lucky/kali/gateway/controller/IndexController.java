package com.lucky.kali.gateway.controller;

import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseEnum;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.DiscoveryVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取appName
     */
    @Value("${appName}")
    private String appName;
    /**
     * 获取Id
     */
    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * Index信息
     * @return  Index信息
     */
    @GetMapping("/")
    public ResponseInfo<List<String>> index(){
        List<String> result = new ArrayList<>();
        result.add("Welcome To The " + appName +" ~");
        result.add("Priority of services : " + discoveryClient.getOrder());
        result.add("Service Id : " + applicationId);
        return Response.success(ResponseEnum.SUCCESS.getMessage(),result);
    }

    /**
     * 获取所有实例Info
     * @return 所有实例Info
     */
    @GetMapping("getInstances")
    public ResponseInfo<List<DiscoveryVO>> getInstances(){
        List<DiscoveryVO> voList = new ArrayList<>();
        //获取所有服务Id
        List<String> serviceIdList = discoveryClient.getServices();
        serviceIdList.forEach(serviceId -> {
            //获取实例信息
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            DiscoveryVO vo = DiscoveryVO.builder()
                    .serviceId(serviceId)
                    .instances(instances).build();
            voList.add(vo);
        });
        return Response.success("common.response.success",voList);
    }

    /**
     * 获取description
     * @return  description
     */
    @GetMapping("getDescription")
    public ResponseInfo<String> description(){
        return Response.success(ResponseEnum.SUCCESS.getMessage(),discoveryClient.description());
    }

    /**
     * 获取所有注册的服务名称
     * @return  所有注册的服务
     */
    @GetMapping("getServices")
    public ResponseInfo<List<String>> getServices(){
        return Response.success("common.response.success",discoveryClient.getServices());
    }
}