package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-13
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "roleList/role")
public class RoleCO {
    @BeanPropertySetter(pattern = "roleList/role/id")
    private int id;
    @BeanPropertySetter(pattern = "roleList/role/name")
    private String name;
    @BeanPropertySetter(pattern = "roleList/role/quantity")
    private String quantity;
    @BeanPropertySetter(pattern = "roleList/role/soldier")
    private int soldier;
    @BeanPropertySetter(pattern = "roleList/role/health")
    private int health;
    @BeanPropertySetter(pattern = "roleList/role/healthGrouth")
    private int healthGrouth;
    @BeanPropertySetter(pattern = "roleList/role/fightForce")
    private int fightForce;
    @BeanPropertySetter(pattern = "roleList/role/attack")
    private int attack;
    @BeanPropertySetter(pattern = "roleList/role/attackGrouth")
    private int attackGrouth;
    @BeanPropertySetter(pattern = "roleList/role/defence")
    private int defence;
    @BeanPropertySetter(pattern = "roleList/role/defenceGrouth")
    private int defenceGrouth;
    @BeanPropertySetter(pattern = "roleList/role/magicDefence")
    private int magicDefence;
    @BeanPropertySetter(pattern = "roleList/role/magicDefenceGrouth")
    private int magicDefenceGrouth;
    @BeanPropertySetter(pattern = "roleList/role/block")
    private int block;
    @BeanPropertySetter(pattern = "roleList/role/fortitude")
    private int fortitude;
    @BeanPropertySetter(pattern = "roleList/role/hit")
    private int hit;
    @BeanPropertySetter(pattern = "roleList/role/unblock")
    private int unblock;
    @BeanPropertySetter(pattern = "roleList/role/critdamage")
    private int critdamage;
    @BeanPropertySetter(pattern = "roleList/role/miss")
    private int miss;
    @BeanPropertySetter(pattern = "roleList/role/grouth")
    private int grouth;
    @BeanPropertySetter(pattern = "roleList/role/url")
    private int url;
    @BeanPropertySetter(pattern = "roleList/role/camp")
    private int camp;
    @BeanPropertySetter(pattern = "roleList/role/period")
    private int period;
    @BeanPropertySetter(pattern = "roleList/role/receiveMode")
    private int receiveMode;
    @BeanPropertySetter(pattern = "roleList/role/goldUrl1")
    private int goldUrl1;
    @BeanPropertySetter(pattern = "roleList/role/godUrl2")
    private int godUrl2;
    @BeanPropertySetter(pattern = "roleList/role/openDays1")
    private int openDays1;
    @BeanPropertySetter(pattern = "roleList/role/openDays2")
    private int openDays2;
    @BeanPropertySetter(pattern = "roleList/role/mergeId")
    private int mergeId;
    @BeanPropertySetter(pattern = "roleList/role/mergeNum")
    private int mergeNum;
    @BeanPropertySetter(pattern = "roleList/role/specialSkill")
    private int specialSkill;


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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthGrouth() {
        return healthGrouth;
    }

    public void setHealthGrouth(int healthGrouth) {
        this.healthGrouth = healthGrouth;
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

    public int getAttackGrouth() {
        return attackGrouth;
    }

    public void setAttackGrouth(int attackGrouth) {
        this.attackGrouth = attackGrouth;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDefenceGrouth() {
        return defenceGrouth;
    }

    public void setDefenceGrouth(int defenceGrouth) {
        this.defenceGrouth = defenceGrouth;
    }

    public int getMagicDefence() {
        return magicDefence;
    }

    public void setMagicDefence(int magicDefence) {
        this.magicDefence = magicDefence;
    }

    public int getMagicDefenceGrouth() {
        return magicDefenceGrouth;
    }

    public void setMagicDefenceGrouth(int magicDefenceGrouth) {
        this.magicDefenceGrouth = magicDefenceGrouth;
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

    public int getCritdamage() {
        return critdamage;
    }

    public void setCritdamage(int critdamage) {
        this.critdamage = critdamage;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public int getGrouth() {
        return grouth;
    }

    public void setGrouth(int grouth) {
        this.grouth = grouth;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getReceiveMode() {
        return receiveMode;
    }

    public void setReceiveMode(int receiveMode) {
        this.receiveMode = receiveMode;
    }

    public int getGoldUrl1() {
        return goldUrl1;
    }

    public void setGoldUrl1(int goldUrl1) {
        this.goldUrl1 = goldUrl1;
    }

    public int getGodUrl2() {
        return godUrl2;
    }

    public void setGodUrl2(int godUrl2) {
        this.godUrl2 = godUrl2;
    }

    public int getOpenDays1() {
        return openDays1;
    }

    public void setOpenDays1(int openDays1) {
        this.openDays1 = openDays1;
    }

    public int getOpenDays2() {
        return openDays2;
    }

    public void setOpenDays2(int openDays2) {
        this.openDays2 = openDays2;
    }

    public int getMergeId() {
        return mergeId;
    }

    public void setMergeId(int mergeId) {
        this.mergeId = mergeId;
    }

    public int getMergeNum() {
        return mergeNum;
    }

    public void setMergeNum(int mergeNum) {
        this.mergeNum = mergeNum;
    }

    public int getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(int specialSkill) {
        this.specialSkill = specialSkill;
    }
}
