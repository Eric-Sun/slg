package com.h13.slg.battle.fight;

import com.google.common.collect.Lists;
import com.h13.slg.battle.buffs.Buff;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.skill.co.UserRoleSkillCO;

import java.util.List;

/**
 * 战斗者，一般为将领，这些将领放在fightUnit当中
 */
public class Fighter {
    public final static int ROLE = 0;
    public final static int MONSTER = 1;

    // 对应的对方的将领或者是怪物
    private int id;
    private int attack;
    private int defence;
    private int health;
    private int type;
    private String soldierType ;
    private String name;
    private int point;
    private UserRoleSkillCO tianfu;
    private UserRoleSkillCO jiangling;
    private int pos;
    private String owner;
    private List<Buff> buffList = Lists.newLinkedList();

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public UserRoleSkillCO getJiangling() {
        return jiangling;
    }

    public void setJiangling(UserRoleSkillCO jiangling) {
        this.jiangling = jiangling;
    }

    public List<Buff> getBuffList() {
        return buffList;
    }

    public void setBuffList(List<Buff> buffList) {
        this.buffList = buffList;
    }

    public String getSoldierType() {
        return soldierType;
    }

    public void setSoldierType(String soldierType) {
        this.soldierType = soldierType;
    }

    public UserRoleSkillCO getTianfu() {
        return tianfu;
    }

    public void setTianfu(UserRoleSkillCO tianfu) {
        this.tianfu = tianfu;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    public static int getRole() {
        return ROLE;
    }

    public static int getMonster() {
        return MONSTER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
