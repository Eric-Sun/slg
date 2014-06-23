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
    private int curGold;
    private int needGold;

    private String materialType1Name;
    private int materialType1Id;
    private int materialType1NeedCount;
    private int materialType1CurrentCount;

    private String materialType2Name;
    private int materialType2Id;
    private int materialType2NeedCount;
    private int materialType2CurrentCount;


    public String getMaterialType1Name() {
        return materialType1Name;
    }

    public void setMaterialType1Name(String materialType1Name) {
        this.materialType1Name = materialType1Name;
    }

    public String getMaterialType2Name() {
        return materialType2Name;
    }

    public void setMaterialType2Name(String materialType2Name) {
        this.materialType2Name = materialType2Name;
    }

    public int getMaterialType1Id() {
        return materialType1Id;
    }

    public void setMaterialType1Id(int materialType1Id) {
        this.materialType1Id = materialType1Id;
    }

    public int getMaterialType1NeedCount() {
        return materialType1NeedCount;
    }

    public void setMaterialType1NeedCount(int materialType1NeedCount) {
        this.materialType1NeedCount = materialType1NeedCount;
    }

    public int getMaterialType1CurrentCount() {
        return materialType1CurrentCount;
    }

    public void setMaterialType1CurrentCount(int materialType1CurrentCount) {
        this.materialType1CurrentCount = materialType1CurrentCount;
    }

    public int getMaterialType2CurrentCount() {
        return materialType2CurrentCount;
    }

    public void setMaterialType2CurrentCount(int materialType2CurrentCount) {
        this.materialType2CurrentCount = materialType2CurrentCount;
    }

    public int getMaterialType2Id() {
        return materialType2Id;
    }

    public void setMaterialType2Id(int materialType2Id) {
        this.materialType2Id = materialType2Id;
    }

    public int getMaterialType2NeedCount() {
        return materialType2NeedCount;
    }

    public void setMaterialType2NeedCount(int materialType2NeedCount) {
        this.materialType2NeedCount = materialType2NeedCount;
    }

    public int getCurGold() {
        return curGold;
    }

    public void setCurGold(int curGold) {
        this.curGold = curGold;
    }

    public int getNeedGold() {
        return needGold;
    }

    public void setNeedGold(int needGold) {
        this.needGold = needGold;
    }

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
