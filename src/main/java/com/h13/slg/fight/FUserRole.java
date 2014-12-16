package com.h13.slg.fight;

import com.google.common.collect.Lists;
import com.h13.slg.fight.buff.Buff;
import com.h13.slg.role.co.UserRoleCO;

import java.util.List;

/**
 * 参战的用户将领对象
 */
public class FUserRole {

    private int urId;

    private UserRoleCO userRoleCO;
    private int pos;

    private List<Buff> buffList = Lists.newLinkedList();

    private int attack = 0;
    private int defence = 0;
    private int health = 0;

    private int baseAttack;
    private int baseDefence;
    private int baseHealth;


    private int addedAttack;
    private int addedDefence;
    private int addedHealth;

    /**
     * 兵种类型，0克制
     */
    private int soldier;

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

    private int subedHealth = 0;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setSubedHealth(int subedHealth) {
        this.subedHealth = subedHealth;
    }

    public int getSubedHealth() {
        return subedHealth;
    }

    public void addSubedHealth(int newSubedHealth) {
        this.subedHealth = subedHealth + newSubedHealth;
    }

    public int getAddedAttack() {
        return addedAttack;
    }

    public void setAddedAttack(int addedAttack) {
        this.addedAttack = addedAttack;
    }

    public int getAddedDefence() {
        return addedDefence;
    }

    public void setAddedDefence(int addedDefence) {
        this.addedDefence = addedDefence;
    }

    public int getAddedHealth() {
        return addedHealth;
    }

    public void setAddedHealth(int addedHealth) {
        this.addedHealth = addedHealth;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public void setBaseDefence(int baseDefence) {
        this.baseDefence = baseDefence;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public List<Buff> getBuffList() {
        return buffList;
    }

    public void setBuffList(List<Buff> buffList) {
        this.buffList = buffList;
    }

    public UserRoleCO getUserRoleCO() {
        return userRoleCO;
    }

    public void setUserRoleCO(UserRoleCO userRoleCO) {
        this.userRoleCO = userRoleCO;
    }

    public int getUrId() {
        return urId;
    }

    public void setUrId(int urId) {
        this.urId = urId;
    }

    public int getFinalAttack() {
        return baseAttack + addedAttack;
    }

    public int getFinalDefence() {
        return baseDefence + addedDefence;
    }

    public int getFinalHealth() {
        return baseHealth + addedHealth - subedHealth;
    }

}
