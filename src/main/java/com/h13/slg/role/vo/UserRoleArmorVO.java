package com.h13.slg.role.vo;

/**
 * 用于在接口中获取头盔相关的信息
 * 其中的userEquipId对应user_equip表中的主键
 * 其中的level为当前这个装备的级别等
 */
public class UserRoleArmorVO {
    private long userEquipId;
    private String name;
    private int level;

    public long getUserEquipId() {
        return userEquipId;
    }

    public void setUserEquipId(long userEquipId) {
        this.userEquipId = userEquipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}