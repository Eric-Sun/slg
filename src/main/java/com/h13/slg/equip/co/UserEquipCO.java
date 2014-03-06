package com.h13.slg.equip.co;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * 用户装备
 */
public class UserEquipCO {
    private long id;
    private int eid;
    private long uid;
    private String type;
    private int level;
    private String gems;
    private int strength;
    private int fail;
    private int refine;
    private int star;

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, String> getGemsMap() {
        return JSON.parseObject(gems, Map.class);
    }

    public String getGems() {
        return gems;
    }

    public void setGemsMap(Map<String, String> data) {
        this.gems = JSON.toJSONString(data);
    }

    public void setGems(String gems) {
        this.gems = gems;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getRefine() {
        return refine;
    }

    public void setRefine(int refine) {
        this.refine = refine;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
