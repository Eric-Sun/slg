package com.h13.slg.fight;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.h13.slg.fight.log.FightLog;

import java.util.List;

/**
 * 战斗的context，其中包含战斗的双方的对象等
 */
public class FContext {


    private FUser attack;
    private FUser defence;
    private int currentRound = 1;

    /**
     * 当前行动方
     */
    private String currentAction = FightConstants.Action.NO;

    /**
     * 当前action的pos
     */
    private int currentPos = 0;

    private String fightStatus = FightConstants.Status.RUNNING;

    private List<List<FightLog>> logList = Lists.newLinkedList();

    private String win;

    public void setLogList(List<List<FightLog>> logList) {
        this.logList = logList;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void addLog(int round, FightLog fightLog) {
        if (logList.size() == round - 1) {
            List<FightLog> list = Lists.newLinkedList();
            logList.add(list);
        }

        logList.get(round - 1).add(fightLog);

    }

    public List<List<FightLog>> getLogList() {
        return logList;
    }

    public String getFightStatus() {
        return fightStatus;
    }

    public void setFightStatus(String fightStatus) {
        this.fightStatus = fightStatus;
    }

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
