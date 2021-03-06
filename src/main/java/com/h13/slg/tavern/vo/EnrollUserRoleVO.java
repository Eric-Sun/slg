package com.h13.slg.tavern.vo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-4-9
 * Time: 下午6:56
 * To change this template use File | Settings | File Templates.
 */
public class EnrollUserRoleVO {
    private long id;
    private long roleId;
    private long uid;
    private int level;
    private int fightForce;
    private int attack;
    private int defence;
    private int health;
    private int soldier;
    private int curSkill;
    private Map<Integer, Integer> skillLevels;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFightForce() {
        return fightForce;
    }

    public void setFightForce(int fightForce) {
        this.fightForce = fightForce;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getCurSkill() {
        return curSkill;
    }

    public void setCurSkill(int curSkill) {
        this.curSkill = curSkill;
    }

    public Map<Integer, Integer> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(Map<Integer, Integer> skillLevels) {
        this.skillLevels = skillLevels;
    }
}
