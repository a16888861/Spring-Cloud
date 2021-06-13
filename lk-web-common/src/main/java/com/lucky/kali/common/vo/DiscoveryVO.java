package com.lucky.kali.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author Elliot
 * @date 2021-06-02 22:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class DiscoveryVO {
    /**
     * 服务Id
     */
    private String serviceId;

    /**
     * 实例信息
     */
    private List<ServiceInstance> instances;
}
