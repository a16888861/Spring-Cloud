package com.lucky.kali.userinfo.vo.resp;

import com.lucky.kali.common.dto.GroupDTO;
import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.common.dto.UserDTO;
import com.lucky.kali.userinfo.dto.MenuDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    /**
     * 菜单信息
     */
    private List<MenuDTO> menuDTOList;
}
