package com.lucky.kali.common.enums;

/**
 * 角色信息枚举类
 *
 * @author Elliot
 */
public enum RoleEnums {
    /**
     * 超级管理员
     */
    ROLE_SUPER_ADMIN("9999", "超级管理员", "superAdmin"),

    /**
     * 角色开启状态
     */
    ROLE_TYPE_ENABLE("0", "开启", "enable"),
    /**
     * 角色关闭状态
     */
    ROLE_TYPE_DISABLE("1", "关闭", "disable");

    /**
     * 角色Code
     */
    private final String roleCode;
    /**
     * 角色Name
     */
    private final String roleName;
    /**
     * 角色英文名
     */
    private final String roleEnName;

    RoleEnums(String roleCode, String roleName, String roleEnName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleEnName = roleEnName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleEnName() {
        return roleEnName;
    }

    /**
     * 获取角色Name
     *
     * @param roleCode 角色Code
     * @return 角色Name
     */
    public static String getRoleName(String roleCode) {
        for (RoleEnums g : RoleEnums.values()) {
            if (g.getRoleCode().equals(roleCode)) {
                return g.roleName;
            }
        }
        return null;
    }

    /**
     * 获取角色Code
     *
     * @param roleName 角色Name
     * @return 角色Code
     */
    public static String getRoleCodeByName(String roleName) {
        for (RoleEnums g : RoleEnums.values()) {
            if (g.getRoleName().equals(roleName)) {
                return g.roleCode;
            }
        }
        return null;
    }

    /**
     * 获取角色Code
     *
     * @param roleEnName 角色英文名称
     * @return 角色Code
     */
    public static String getRoleCodeByRoleEnName(String roleEnName) {
        for (RoleEnums g : RoleEnums.values()) {
            if (g.getRoleEnName().equals(roleEnName)) {
                return g.roleCode;
            }
        }
        return null;
    }
}
