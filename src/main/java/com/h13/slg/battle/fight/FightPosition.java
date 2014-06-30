package com.h13.slg.battle.fight;

/**
 * 战斗位
 */
public class FightPosition {
    public final static int ROLE = 0;
    public final static int MONSTER = 1;

    // 对应的对方的将领或者是怪物
    private int id;
    private int attack;
    private int defence;
    private int health;
    private int type;
    private String name;

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
