package com.h13.slg.user.co;

import java.util.Date;

/**
 * 用户状态信息
 */
public class UserStatusCO {
    private long id;
    private int gold;
    private int food;
    private int cash;
    private int honor;
    private int level;
    private int xp;
    private String name ;
    private int soul;

    public int getSoul() {
        return soul;
    }

    public void setSoul(int soul) {
        this.soul = soul;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
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
}
