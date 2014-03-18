package com.h13.slg.tavern.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-14
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */

import java.util.Map;

public class InvitedRoleCO {
    private long id ;
    private int xp;
    private int level;
    private int soldierLevel;
    private int grouth;
    private int attack;
    private int defence;
    private int mdefence;
    private long weapon;
    private long armor;
    private long accessory;
    private int skill;
    private Map<String,Integer> skillLevels;
    private int soul;
    private int fightForce;
    private int god;
    private int soulBreak;
    private int grouthBreak;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getSoldierLevel() {
        return soldierLevel;
    }

    public void setSoldierLevel(int soldierLevel) {
        this.soldierLevel = soldierLevel;
    }

    public int getGrouth() {
        return grouth;
    }

    public void setGrouth(int grouth) {
        this.grouth = grouth;
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

    public int getMdefence() {
        return mdefence;
    }

    public void setMdefence(int mdefence) {
        this.mdefence = mdefence;
    }

    public long getWeapon() {
        return weapon;
    }

    public void setWeapon(long weapon) {
        this.weapon = weapon;
    }

    public long getArmor() {
        return armor;
    }

    public void setArmor(long armor) {
        this.armor = armor;
    }

    public long getAccessory() {
        return accessory;
    }

    public void setAccessory(long accessory) {
        this.accessory = accessory;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Map<String, Integer> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(Map<String, Integer> skillLevels) {
        this.skillLevels = skillLevels;
    }

    public int getSoul() {
        return soul;
    }

    public void setSoul(int soul) {
        this.soul = soul;
    }

    public int getFightForce() {
        return fightForce;
    }

    public void setFightForce(int fightForce) {
        this.fightForce = fightForce;
    }

    public int getGod() {
        return god;
    }

    public void setGod(int god) {
        this.god = god;
    }

    public int getSoulBreak() {
        return soulBreak;
    }

    public void setSoulBreak(int soulBreak) {
        this.soulBreak = soulBreak;
    }

    public int getGrouthBreak() {
        return grouthBreak;
    }

    public void setGrouthBreak(int grouthBreak) {
        this.grouthBreak = grouthBreak;
    }
}
