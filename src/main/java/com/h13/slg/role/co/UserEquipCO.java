package com.h13.slg.role.co;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-24
 * Time: 下午7:38
 * To change this template use File | Settings | File Templates.
 */
public class UserEquipCO {
    private long id;
    private int type;
    private int level;
    private String gems;
    private int strength;
    private int fail;
    private int refine;
    private int star;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, String> getGems() {
        return JSON.parseObject(gems, Map.class);
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
