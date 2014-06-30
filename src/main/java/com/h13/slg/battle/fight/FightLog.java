package com.h13.slg.battle.fight;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-27
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class FightLog {
    private int attack;
    private PosInfo attackPos;
    private LinkedList<PosInfo> defencePos;
    private FightStatus attackStatus = new FightStatus();
    private FightStatus defenceStatus = new FightStatus();

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
