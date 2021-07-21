package com.lucky.kali.consumer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name")
    @NotEmpty(message = "用户名不允许为空")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    @NotEmpty(message = "密码不允许为空")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "mail")
    @NotEmpty(message = "邮箱不允许为空")
    private String mail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", name = "phone")
    @NotEmpty(message = "手机号不允许为空")
    private String phone;

    /**
     * 用户名-别名
     */
    @ApiModelProperty(value = "用户名-别名", name = "screenName")
    @NotEmpty(message = "用户别名不允许为空")
    private String screenName;

    /**
     * 用户组别
     */
    @ApiModelProperty(value = "用户组别", name = "group")
    @NotEmpty(message = "用户组别不允许为空")
    private String group;
}
