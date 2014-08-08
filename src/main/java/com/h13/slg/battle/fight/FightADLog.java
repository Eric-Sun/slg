package com.h13.slg.battle.fight;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 攻击防御的相关日志
 */
public class FightADLog {
    private String type="ad";
    private int attack;
    private PosInfo attackPos;
    private LinkedList<PosInfo> defencePos;
    private FightStatus attackStatus = new FightStatus();
    private FightStatus defenceStatus = new FightStatus();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public PosInfo getAttackPos() {
        return attackPos;
    }

    public void setAttackPos(PosInfo attackPos) {
        this.attackPos = attackPos;
    }

    public LinkedList<PosInfo> getDefencePos() {
        return defencePos;
    }

    public void setDefencePos(LinkedList<PosInfo> defencePos) {
        this.defencePos = defencePos;
    }

    public FightStatus getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(FightStatus attackStatus) {
        this.attackStatus = attackStatus;
    }

    public FightStatus getDefenceStatus() {
        return defenceStatus;
    }

    public void setDefenceStatus(FightStatus defenceStatus) {
        this.defenceStatus = defenceStatus;
    }
}
