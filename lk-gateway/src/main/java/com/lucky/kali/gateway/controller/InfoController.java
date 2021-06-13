package com.lucky.kali.gateway.controller;

import com.lucky.kali.common.response.Response;
import com.lucky.kali.common.response.ResponseInfo;
import com.lucky.kali.common.vo.DiscoveryVO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Elliot
 * @date 2021-06-01 23:00
 */
@RestController
@RequestMapping("info")
public class InfoController {

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有实例Info
     * @return 获取所有实例Info
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
     * 获取所有服务名称
     * @return  所有注册的服务
     */
    @GetMapping("getServices")
    public ResponseInfo<List<String>> getServices(){
        return Response.success("common.response.success",discoveryClient.getServices());
    }
}
