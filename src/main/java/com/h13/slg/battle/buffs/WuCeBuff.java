package com.h13.slg.battle.buffs;

import com.h13.slg.battle.fight.FightResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 无策buff，可以让在指定的回合内对方无法释放将领技能
 */
public class WuCeBuff extends Buff {
    private static final Logger LOG = LoggerFactory.getLogger(WuCeBuff.class);
    private int roundCount = 0;

    public WuCeBuff(int urid, int roundCount) {
        super.onwerUserRoleId = urid;
        this.roundCount = roundCount;
        this.buffTimeType = BuffTimeType.ROUND;
    }


    @Override
    public void trigger(BuffEvent event, Object object, FightResult fightResult, int round) throws BuffStoppedException {
        switch (event) {
            case BEFORE_ROUND:
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
