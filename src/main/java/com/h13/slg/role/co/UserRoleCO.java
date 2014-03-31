package com.h13.slg.role.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-21
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleCO {
    private long id;
    private long roleId;
    private long uid;
    private int level;
    private long weapon;
    private long armor;
    private long accessory;
    private int fightForce;
    private int attack;
    private int defence;
    private int health;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

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

    public int getFightForce() {
        return fightForce;
    }

    public void setFightForce(int fightForce) {
        this.fightForce = fightForce;
    }
}
