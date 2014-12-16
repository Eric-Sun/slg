package com.h13.slg.fight.log;

/**
 *
 */
public class FightLog {

    private String aciton;

    private int attackPos;
    private int[] defencePos;

    private FUserRoleStatusLog attackStatus;
    private FUserRoleStatusLog[] defenceStatus;

    public String getAciton() {
        return aciton;
    }

    public void setAciton(String aciton) {
        this.aciton = aciton;
    }

    public int getAttackPos() {
        return attackPos;
    }

    public void setAttackPos(int attackPos) {
        this.attackPos = attackPos;
    }

    public FUserRoleStatusLog getAttackStatus() {
        return attackStatus;
    }

    public void setAttackStatus(FUserRoleStatusLog attackStatus) {
        this.attackStatus = attackStatus;
    }

    public int[] getDefencePos() {
        return defencePos;
    }

    public void setDefencePos(int[] defencePos) {
        this.defencePos = defencePos;
    }

    public FUserRoleStatusLog[] getDefenceStatus() {
        return defenceStatus;
    }

    public void setDefenceStatus(FUserRoleStatusLog[] defenceStatus) {
        this.defenceStatus = defenceStatus;
    }
}



