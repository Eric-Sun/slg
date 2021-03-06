package com.h13.slg.battle.buffs;

import com.h13.slg.battle.old_fight.FightResult;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-8-1
 * Time: 下午6:17
 * To change this template use File | Settings | File Templates.
 */
public class HuangHuBuff extends Buff {

    private int roundCount = 0;

    public HuangHuBuff(int roundCount) {
        this.roundCount = roundCount;
        buffTimeType = BuffTimeType.ROUND;
    }

    public HuangHuBuff() {

    }


    @Override
    public void trigger(BuffEvent event, Object object, FightResult fightResult, int round) throws BuffStoppedException {
        switch (event) {
            case BEFORE_FIGHT:
                if (!isGlobalTimeType())
                    break;
                if (isInited())
                    break;
                inited = true;
                break;
            case BEFORE_ROUND:
                if (isGlobalTimeType())
                    break;
                curRoundCount++;
                break;
            case AFTER_ROUND:
                if (isGlobalTimeType())
                    break;
                if (curRoundCount == roundCount) {
                    throw new BuffStoppedException();
                }
                break;
            case AFTER_FIGHT:
                if (!isGlobalTimeType())
                    break;
                throw new BuffStoppedException();
        }

    }
}
