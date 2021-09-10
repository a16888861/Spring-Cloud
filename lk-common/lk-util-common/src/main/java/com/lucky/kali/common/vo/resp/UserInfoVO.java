package com.lucky.kali.common.vo.resp;

import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.common.dto.UserDTO;
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
@ApiModel("登陆用户信息")
public class UserInfoVO {

    /**
     * 用户DTO信息
     */
    @ApiModelProperty(value = "用户DTO信息", name = "userDTO")
    private UserDTO userDTO;

    /**
     * 组别表DTO信息
     */
    @ApiModelProperty(value = "组别表DTO信息", name = "groupDTO")
    private GroupDTO groupDTO;

    /**
     * 角色DTO信息
     */
    @ApiModelProperty(value = "角色DTO信息", name = "roleDTO")
    private RoleDTO roleDTO;

}
