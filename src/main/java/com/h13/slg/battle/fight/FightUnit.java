package com.h13.slg.battle.fight;

/**
 * 战斗单元
 */
public class FightUnit {

    private FightPosition[] allPos = new FightPosition[9];

    public FightPosition[] getAllPos() {
        return allPos;
    }

    public void setAllPos(FightPosition[] allPos) {
        this.allPos = allPos;
    }

    public void add(int index, FightPosition fightPosition) {
        allPos[index] = fightPosition;
    }
}
