package com.h13.slg.battle.fight;

import java.util.HashMap;
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
    private int attackPos;
    private int[] defencePos;
    private Map<Integer, Integer[]> attackStatus = new HashMap<Integer, Integer[]>();
    private Map<Integer, Integer[]> defenceStatus = new HashMap<Integer, Integer[]>();

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

    public int[] getDefencePos() {
        return defencePos;
    }

    public void setDefencePos(int[] defencePos) {
        this.defencePos = defencePos;
    }

    public Map<Integer, Integer[]> getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(Map<Integer, Integer[]> attackStatus) {
        this.attackStatus = attackStatus;
    }

    public Map<Integer, Integer[]> getDefenceStatus() {
        return defenceStatus;
    }

    public void setDefenceStatus(Map<Integer, Integer[]> defenceStatus) {
        this.defenceStatus = defenceStatus;
    }
}
