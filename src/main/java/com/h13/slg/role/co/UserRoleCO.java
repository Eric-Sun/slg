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
    private long weapon;
    private long armor;
    private long accessory;
    private int fightForce;

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
