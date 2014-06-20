package com.h13.slg.equip.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-20
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
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
    private EquipInfoVO equipInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getGems() {
        return gems;
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

    public EquipInfoVO getEquipInfo() {
        return equipInfo;
    }

    public void setEquipInfo(EquipInfoVO equipInfo) {
        this.equipInfo = equipInfo;
    }
}
