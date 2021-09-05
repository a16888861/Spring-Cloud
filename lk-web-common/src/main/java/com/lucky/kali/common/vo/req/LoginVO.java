package com.lucky.kali.common.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 登陆VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@ApiModel("登陆信息")
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别ID
     */
    @NotEmpty(message = "group.groupId.NotEmpty")
    @ApiModelProperty(value = "组别ID", name = "groupId", hidden = false, required = true, position = 0)
    private String groupId;

    /**
     * 角色ID
     */
    @NotEmpty(message = "role.roleId.NotEmpty")
    @ApiModelProperty(value = "角色ID", name = "roleId", hidden = false, required = true, position = 1)
    private String roleId;

    /**
     * 邮箱/手机号
     */
    @NotEmpty(message = "user.mail.NotEmpty")
    @ApiModelProperty(value = "邮箱/手机号", name = "mailOrPhone", hidden = false, required = true, position = 2)
    private String mailOrPhone;

    /**
     * 密码
     */
    @NotEmpty(message = "user.password.NotEmpty")
    @ApiModelProperty(value = "密码", name = "password", hidden = false, required = true, position = 3)
    private String password;
}
