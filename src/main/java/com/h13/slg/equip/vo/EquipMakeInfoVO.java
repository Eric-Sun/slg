package com.h13.slg.equip.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-14
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class EquipMakeInfoVO {


    private String materialType1Name;
    private int materialType1Id;
    private int materialType1NeedCount;
    private int materialType1CurrentCount;

    private String materialType2Name;
    private int materialType2Id;
    private int materialType2NeedCount;
    private int materialType2CurrentCount;

    public int getMaterialType1CurrentCount() {
        return materialType1CurrentCount;
    }

    public void setMaterialType1CurrentCount(int materialType1CurrentCount) {
        this.materialType1CurrentCount = materialType1CurrentCount;
    }

    public int getMaterialType1Id() {
        return materialType1Id;
    }

    public void setMaterialType1Id(int materialType1Id) {
        this.materialType1Id = materialType1Id;
    }

    public String getMaterialType1Name() {
        return materialType1Name;
    }

    public void setMaterialType1Name(String materialType1Name) {
        this.materialType1Name = materialType1Name;
    }

    public int getMaterialType1NeedCount() {
        return materialType1NeedCount;
    }

    public void setMaterialType1NeedCount(int materialType1NeedCount) {
        this.materialType1NeedCount = materialType1NeedCount;
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

    public String getMaterialType2Name() {
        return materialType2Name;
    }

    public void setMaterialType2Name(String materialType2Name) {
        this.materialType2Name = materialType2Name;
    }

    public int getMaterialType2NeedCount() {
        return materialType2NeedCount;
    }

    public void setMaterialType2NeedCount(int materialType2NeedCount) {
        this.materialType2NeedCount = materialType2NeedCount;
    }
}
