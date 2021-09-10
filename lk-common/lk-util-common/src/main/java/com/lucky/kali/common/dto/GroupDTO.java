package com.lucky.kali.common.dto;

import com.lucky.kali.common.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 组别表
 *
 * @author Elliot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GroupDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组别
     */
    private String userGroup;

    /**
     * 用户组别名称
     */
    private String userGroupName;

    /**
     * 权限表id
     */
    private String permissionId;

    /**
     * 年份
     */
    private String year;


}
