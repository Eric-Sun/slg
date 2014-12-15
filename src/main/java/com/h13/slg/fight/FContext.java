package com.h13.slg.fight;

/**
 * 战斗的context，其中包含战斗的双方的对象等
 */
public class FContext {


    private FUser attack;
    private FUser defence;

    /**
     * 当前行动方
     */
    private String currentAction = FightConstants.Action.NO;

    /**
     * 当前action的pos
     */
    private int currentPos = 0;


    private String fightStatus;

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public String getFightStatus() {
        return fightStatus;
    }

    public void setFightStatus(String fightStatus) {
        this.fightStatus = fightStatus;
    }

    public FUser getAttack() {
        return attack;
    }

    public void setAttack(FUser attack) {
        this.attack = attack;
    }

    public FUser getDefence() {
        return defence;
    }

    public void setDefence(FUser defence) {
        this.defence = defence;
    }
}
