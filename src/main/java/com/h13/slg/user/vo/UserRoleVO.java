package com.h13.slg.user.vo;

import com.h13.slg.role.vo.UserRoleAccessoryVO;
import com.h13.slg.role.vo.UserRoleArmorVO;
import com.h13.slg.role.vo.UserRoleWeaponVO;
import com.h13.slg.skill.vo.UserRoleSkillVO;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-21
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleVO {
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
    private int soldier;
    private String roleName;
    private int xp;
    private int putongSkillId;
    private int tianfuSkillId;

    public long getAccessory() {
        return accessory;
    }

    public void setAccessory(long accessory) {
        this.accessory = accessory;
    }

    public long getArmor() {
        return armor;
    }

    public void setArmor(long armor) {
        this.armor = armor;
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

    public int getFightForce() {
        return fightForce;
    }

    public void setFightForce(int fightForce) {
        this.fightForce = fightForce;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPutongSkillId() {
        return putongSkillId;
    }

    public void setPutongSkillId(int putongSkillId) {
        this.putongSkillId = putongSkillId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getTianfuSkillId() {
        return tianfuSkillId;
    }

    public void setTianfuSkillId(int tianfuSkillId) {
        this.tianfuSkillId = tianfuSkillId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getWeapon() {
        return weapon;
    }

    public void setWeapon(long weapon) {
        this.weapon = weapon;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
