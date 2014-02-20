package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-10
 * Time: 下午11:29
 * To change this template use File | Settings | File Templates.
 */

@ObjectCreate(pattern = "levelList/level")
public class LevelCO {

    @BeanPropertySetter(pattern = "levelList/level/level")
    private int level;
    @BeanPropertySetter(pattern = "levelList/level/xp")
    private int xp;
    @BeanPropertySetter(pattern = "levelList/level/roleNum")
    private int roleNum;
    @BeanPropertySetter(pattern = "levelList/level/goldMax")
    private int goldMax;
    @BeanPropertySetter(pattern = "levelList/level/goldPerHour")
    private int goldPerHour;
    @BeanPropertySetter(pattern = "levelList/level/foodMax")
    private int foodMax;
    @BeanPropertySetter(pattern = "levelList/level/foodPerHour")
    private int foodPerHour;
    @BeanPropertySetter(pattern = "levelList/level/trainingXp")
    private int trainingXp;
    @BeanPropertySetter(pattern = "levelList/level/mineOutputFactor")
    private float mineOutputFactor;
    @BeanPropertySetter(pattern = "levelList/level/personOutput")
    private int personOutput;
    @BeanPropertySetter(pattern = "levelList/level/prisonOutput")
    private int prisonOutput;
    @BeanPropertySetter(pattern = "levelList/level/equipSorce")
    private int equipSorce;
    @BeanPropertySetter(pattern = "levelList/level/goldBuy")
    private int goldBuy;
    @BeanPropertySetter(pattern = "levelList/level/offlineXp")
    private int offlineXp;
    @BeanPropertySetter(pattern = "levelList/level/serverWarSupport")
    private int serverWarSupport;
    @BeanPropertySetter(pattern = "levelList/level/battleWorldSupport")
    private int battleWorldSupport;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(int roleNum) {
        this.roleNum = roleNum;
    }

    public int getGoldMax() {
        return goldMax;
    }

    public void setGoldMax(int goldMax) {
        this.goldMax = goldMax;
    }

    public int getGoldPerHour() {
        return goldPerHour;
    }

    public void setGoldPerHour(int goldPerHour) {
        this.goldPerHour = goldPerHour;
    }

    public int getFoodMax() {
        return foodMax;
    }

    public void setFoodMax(int foodMax) {
        this.foodMax = foodMax;
    }

    public int getFoodPerHour() {
        return foodPerHour;
    }

    public void setFoodPerHour(int foodPerHour) {
        this.foodPerHour = foodPerHour;
    }

    public int getTrainingXp() {
        return trainingXp;
    }

    public void setTrainingXp(int trainingXp) {
        this.trainingXp = trainingXp;
    }

    public float getMineOutputFactor() {
        return mineOutputFactor;
    }

    public void setMineOutputFactor(float mineOutputFactor) {
        this.mineOutputFactor = mineOutputFactor;
    }

    public int getPersonOutput() {
        return personOutput;
    }

    public void setPersonOutput(int personOutput) {
        this.personOutput = personOutput;
    }

    public int getPrisonOutput() {
        return prisonOutput;
    }

    public void setPrisonOutput(int prisonOutput) {
        this.prisonOutput = prisonOutput;
    }

    public int getEquipSorce() {
        return equipSorce;
    }

    public void setEquipSorce(int equipSorce) {
        this.equipSorce = equipSorce;
    }

    public int getGoldBuy() {
        return goldBuy;
    }

    public void setGoldBuy(int goldBuy) {
        this.goldBuy = goldBuy;
    }

    public int getOfflineXp() {
        return offlineXp;
    }

    public void setOfflineXp(int offlineXp) {
        this.offlineXp = offlineXp;
    }

    public int getServerWarSupport() {
        return serverWarSupport;
    }

    public void setServerWarSupport(int serverWarSupport) {
        this.serverWarSupport = serverWarSupport;
    }

    public int getBattleWorldSupport() {
        return battleWorldSupport;
    }

    public void setBattleWorldSupport(int battleWorldSupport) {
        this.battleWorldSupport = battleWorldSupport;
    }
}
