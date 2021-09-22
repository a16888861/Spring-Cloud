package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Elliot
 * @date 2021/9/22 10:17
 */
@Data
@NoArgsConstructor
@ApiModel(description = "角色菜单关系实体",subTypes = Object.class)
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class RoleMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", name = "roleId", hidden = false, required = true, position = 0)
    @NotEmpty(message = "role.roleId.NotEmpty")
    private String roleId;

    @ApiModelProperty(value = "菜单id集合", name = "menuIdList", hidden = false, required = true, position = 1)
    @NotEmpty(message = "menu.menuIdList.NotEmpty")
    private List<String> menuIdList;

    @ApiModelProperty(value = "操作者id", name = "userId", hidden = true, required = false, position = 2)
    private String userId;
}
