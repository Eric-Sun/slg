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

    private List<Buff> buffList = Lists.newLinkedList();

    private int baseAttack;
    private int baseDefence;
    private int baseHealth;


    private int addedAttack;
    private int addedDefence;
    private int addedHealth;

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
}
