package com.lucky.kali.common.enums;

/**
 * 组别信息枚举类
 *
 * @author Elliot
 */
public enum GroupEnums {

    /**
     * xx组别
     */
    GROUP_ADMIN("9999", "admin");

    /**
     * 组别Code
     */
    private final String groupCode;
    /**
     * 组别Name
     */
    private final String groupName;

    GroupEnums(String groupCode, String groupName){
        this.groupCode = groupCode;
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * 获取组别Name
     * @param groupCode     组别Code
     * @return              组别Name
     */
    public static String getGroupName(String groupCode) {
        for (GroupEnums g : GroupEnums.values()) {
            if (g.getGroupCode().equals(groupCode)) {
                return g.groupName;
            }
        }
        return null;
    }

    /**
     * 获取组别Code
     * @param groupName     组别Name
     * @return              组别Code
     */
    public static String getGroupCode(String groupName) {
        for (GroupEnums g : GroupEnums.values()) {
            if (g.getGroupName().equals(groupName)) {
                return g.groupCode;
            }
        }
        return null;
    }
}
