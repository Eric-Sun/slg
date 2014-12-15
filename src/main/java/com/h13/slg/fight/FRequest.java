package com.h13.slg.fight;

/**
 * 战斗请求对象，runner对象的输入为此对象
 */
public class FRequest {


    /**
     * 战斗类型，pvp还是pve
     */
    private String FightType ;

    private int attackUserId;
    private int defenceId;

    public int getAttackUserId() {
        return attackUserId;
    }

    public void setAttackUserId(int attackUserId) {
        this.attackUserId = attackUserId;
    }

    public int getDefenceId() {
        return defenceId;
    }

    public void setDefenceId(int defenceId) {
        this.defenceId = defenceId;
    }

    public String getFightType() {
        return FightType;
    }

    public void setFightType(String fightType) {
        FightType = fightType;
    }
}
