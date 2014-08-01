package com.h13.slg.battle.fight;

/**
 * 战斗单元
 */
public class FightUnit {

    private Fighter[] allPos = new Fighter[9];

    public Fighter[] getAllPos() {
        return allPos;
    }

    public void setAllPos(Fighter[] allPos) {
        this.allPos = allPos;
    }

    public void add(int index, Fighter fighter) {
        allPos[index] = fighter;
    }
}
