package com.h13.slg.user.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-8
 * Time: 上午1:09
 * To change this template use File | Settings | File Templates.
 */
public class UserStatusVO {
    private int uid;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * 元宝
     */
    private int cash;
    /**
     * 战力
     */
    private int fightForce;
    /**
     * 粮食
     */
    private int food;
    /**
     * 金币
     */
    private int gold;
    /**
     * 军功
     */
    private int honor;
    /**
     * 用户等级
     */
    private int level;
    /**
     * 魂
     */
    private int soul;
    /**
     * vip登记
     */
    private int vip;
    /**
     * 经验
     */
    private int xp;

    private long castleTimer;
    private long farmTimer;

    private long levelUpXp;

    public long getLevelUpXp() {
        return levelUpXp;
    }

    public void setLevelUpXp(long levelUpXp) {
        this.levelUpXp = levelUpXp;
    }

    public long getCastleTimer() {
        return castleTimer;
    }

    public void setCastleTimer(long castleTimer) {
        this.castleTimer = castleTimer;
    }

    public long getFarmTimer() {
        return farmTimer;
    }

    public void setFarmTimer(long farmTimer) {
        this.farmTimer = farmTimer;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getFightForce() {
        return fightForce;
    }

    public void setFightForce(int fightForce) {
        this.fightForce = fightForce;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSoul() {
        return soul;
    }

    public void setSoul(int soul) {
        this.soul = soul;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
