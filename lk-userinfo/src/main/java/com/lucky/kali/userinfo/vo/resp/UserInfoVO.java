package com.lucky.kali.userinfo.vo.resp;

import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.common.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Elliot
 */
@Data
@SuperBuilder
@NoArgsConstructor
@ApiModel("用户相关信息")
public class UserInfoVO {

    /**
     * 用户信息
     */
    private UserDTO userDTO;

    /**
     * 组别信息
     */
    private GroupDTO groupDTO;

    /**
     * 角色信息
     */
    private RoleDTO roleDTO;
}
