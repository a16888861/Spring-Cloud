package com.lucky.kali.userinfo.vo.resp;

import com.lucky.kali.common.dto.RoleDTO;
import com.lucky.kali.userinfo.vo.req.GroupVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 组别角色表VO
 *
 * @author Elliot
 */

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GroupRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别表VO
     */
    @ApiModelProperty(value = "组别表VO", name = "groupVO")
    private GroupVO groupVO;

    /**
     * 角色表DTO集合
     */
    @ApiModelProperty(value = "角色表DTO集合", name = "roleList")
    private List<RoleDTO> roleList;
}
