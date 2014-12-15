package com.h13.slg.battle.old_fight;

import java.util.List;

/**
 * 攻击防御的相关日志
 */
public class FightADLog {
    private String type="ad";
    private int attack;
    private int attackPos;
    private List<Integer> defencePos;
    private FightStatus attackStatus = new FightStatus();
    private FightStatus defenceStatus = new FightStatus();

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttackPos() {
        return attackPos;
    }

    public void setAttackPos(int attackPos) {
        this.attackPos = attackPos;
    }

    public FightStatus getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(FightStatus attackStatus) {
        this.attackStatus = attackStatus;
    }

    public List<Integer> getDefencePos() {
        return defencePos;
    }

    public void setDefencePos(List<Integer> defencePos) {
        this.defencePos = defencePos;
    }

    public FightStatus getDefenceStatus() {
        return defenceStatus;
    }

    public void setDefenceStatus(FightStatus defenceStatus) {
        this.defenceStatus = defenceStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
