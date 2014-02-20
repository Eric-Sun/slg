package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-21
 * Time: 上午12:07
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "monsterList/monster")
public class MonsterCO {
    @BeanPropertySetter(pattern = "monsterList/monster/id")
    private int id;
    @BeanPropertySetter(pattern = "monsterList/monster/name")
    private String name;
    @BeanPropertySetter(pattern = "monsterList/monster/level")
    private int level;
    @BeanPropertySetter(pattern = "monsterList/monster/soldier")
    private int soldier;
    @BeanPropertySetter(pattern = "monsterList/monster/soldierLevel")
    private int soldierLevel;
    @BeanPropertySetter(pattern = "monsterList/monster/skill")
    private int skill;
    @BeanPropertySetter(pattern = "monsterList/monster/skillLevel")
    private int skillLevel;
    @BeanPropertySetter(pattern = "monsterList/monster/fightForce")
    private int fightForce;
    @BeanPropertySetter(pattern = "monsterList/monster/attack")
    private int attack;
    @BeanPropertySetter(pattern = "monsterList/monster/health")
    private int health;
    @BeanPropertySetter(pattern = "monsterList/monster/defence")
    private int defence;
    @BeanPropertySetter(pattern = "monsterList/monster/magicDefence")
    private int magicDefence;
    @BeanPropertySetter(pattern = "monsterList/monster/miss")
    private int miss;
    @BeanPropertySetter(pattern = "monsterList/monster/critdamage")
    private int critdamage;
    @BeanPropertySetter(pattern = "monsterList/monster/hit")
    private int hit;
    @BeanPropertySetter(pattern = "monsterList/monster/unblock")
    private int unblock;
    @BeanPropertySetter(pattern = "monsterList/monster/block")
    private int block;
    @BeanPropertySetter(pattern = "monsterList/monster/fortitude")
    private int fortitude;
    @BeanPropertySetter(pattern = "monsterList/monster/uRL")
    private String uRL;
    @BeanPropertySetter(pattern = "monsterList/monster/gold")
    private int gold;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getSoldierLevel() {
        return soldierLevel;
    }

    public void setSoldierLevel(int soldierLevel) {
        this.soldierLevel = soldierLevel;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public void setMagicDefence(int magicDefence) {
        this.magicDefence = magicDefence;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getCritdamage() {
        return critdamage;
    }

    public void setCritdamage(int critdamage) {
        this.critdamage = critdamage;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getUnblock() {
        return unblock;
    }

    public void setUnblock(int unblock) {
        this.unblock = unblock;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getFortitude() {
        return fortitude;
    }

    public void setFortitude(int fortitude) {
        this.fortitude = fortitude;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
