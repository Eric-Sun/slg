package com.h13.slg.equip.vo;

/**
 * 用户装备vo
 */
public class UserEquipVO {
    private long id;
    private long uid;
    private long urid;
    private String type;
    private int level;
    private String gems;
    private int strength;
    private int fail;
    private int refine;
    private int star;
    private EquipStrengthenInfoVO strengthenInfo;
    private EquipMakeInfoVO makeInfo;

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public String getGems() {
        return gems;
    }

    public void setGems(String gems) {
        this.gems = gems;
    }

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

    public EquipMakeInfoVO getMakeInfo() {
        return makeInfo;
    }

    public void setMakeInfo(EquipMakeInfoVO makeInfo) {
        this.makeInfo = makeInfo;
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public EquipStrengthenInfoVO getStrengthenInfo() {
        return strengthenInfo;
    }

    public void setStrengthenInfo(EquipStrengthenInfoVO strengthenInfo) {
        this.strengthenInfo = strengthenInfo;
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
