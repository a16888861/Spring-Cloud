package com.lucky.kali.userinfo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 组别表VO
 *
 * @author Elliot
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GroupVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组别表ID
     */
    @ApiModelProperty(value = "组别表ID", name = "id", hidden = true, position = 0)
    private String id;

    /**
     * 组别CODE
     */
    @NotEmpty(message = "user.userGroup.NotEmpty")
    @ApiModelProperty(value = "组别CODE", name = "userGroup", required = true, position = 1)
    private String userGroup;

    /**
     * 组别名称
     */
    @NotEmpty(message = "group.userGroupName.NotEmpty")
    @ApiModelProperty(value = "组别名称", name = "userGroupName", required = true, position = 2)
    private String userGroupName;

    /**
     * 权限表ID
     */
    @ApiModelProperty(value = "权限表ID", name = "permissionId", hidden = true, position = 3)
    private String permissionId;
}
