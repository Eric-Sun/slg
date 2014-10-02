package com.h13.slg.user.vo;

import com.h13.slg.equip.vo.EquipMakeInfoVO;
import com.h13.slg.equip.vo.EquipStrengthenInfoVO;

/**
 * 用户装备vo
 */
public class UserEquipVO {
    private long id;
    private long uid;
    private long urid;
    private String type;
    private int level;
    private int strength;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUrid() {
        return urid;
    }

    public void setUrid(long urid) {
        this.urid = urid;
    }
}
