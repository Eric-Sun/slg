package com.h13.slg.task.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-3
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class FinishedPerTaskVO {
    private int id;
    private int gold;
    private int xp;
    private int level;
    private List<List<String>> awards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<List<String>> getAwards() {
        return awards;
    }

    public void setAwards(List<List<String>> awards) {
        this.awards = awards;
    }
}
