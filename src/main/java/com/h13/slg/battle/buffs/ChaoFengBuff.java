package com.h13.slg.battle.buffs;

import com.h13.slg.battle.fight.FightResult;

/**
 * 嘲讽，让这个人始终攻击一个人，除非这个人死亡
 */
public class ChaoFengBuff extends Buff {

    private int targetUserRoleId;
    protected int roundCount = 0;

    public ChaoFengBuff(int targetUserRoleId, int roundCount) {
        this.targetUserRoleId = targetUserRoleId;
        this.roundCount = roundCount;
        this.buffTimeType = BuffTimeType.ROUND;
    }


    @Override
    public void trigger(BuffEvent event, Object object, FightResult fightResult, int round) throws BuffStoppedException {
        switch (event) {
            case BEFORE_ROUND:
                if (isInited())
                    break;
                inited = true;
                curRoundCount++;
                break;
            case AFTER_ROUND:
                if (curRoundCount == roundCount) {
                    throw new BuffStoppedException();
                }
                break;
        }

    }
}
