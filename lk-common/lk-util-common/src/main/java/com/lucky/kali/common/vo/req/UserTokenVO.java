package com.lucky.kali.common.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ApiModel("用户Token相关信息")
public class UserTokenVO {

    /**
     * token信息
     */
    @ApiModelProperty(value = "token信息", name = "token")
    private String token;

    /**
     * token过期时间
     */
    @ApiModelProperty(value = "token过期时间", name = "expiredTime")
    private Integer tokenExpiredTime;

    /**
     * 刷新token
     */
    @ApiModelProperty(value = "刷新token", name = "refreshToken")
    private String refreshToken;

    /**
     * 刷新token过期时间
     */
    @ApiModelProperty(value = "刷新token过期时间", name = "refreshTokenExpiredTime")
    private Integer refreshTokenExpiredTime;
}
