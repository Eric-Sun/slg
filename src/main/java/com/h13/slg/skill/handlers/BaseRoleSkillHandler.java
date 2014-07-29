package com.h13.slg.skill.handlers;

import com.h13.slg.battle.fight.FightUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-23
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseRoleSkillHandler {

    public static final String AFTER_FIGHT = "afterFight";
    public static final String BEFORE_FIGHT = "beforeFight";
    public static final String BEFORE_ATTACK = "beforeAttack";

    protected static final Logger LOG = LoggerFactory.getLogger(BaseRoleSkillHandler.class);

    public abstract void beforeFight(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit);

    public abstract void afterFight(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit);


    public abstract void beforeAttack(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit, int attackPos, int defencePos);


    /**
     * 增加i%的值
     *
     * @param i
     */
    protected int add(int origin, int i) {
        float f = i / 100;
        return new Float(origin * (1 + f)).intValue();
    }


    /**
     * 减少i%的值
     *
     * @param origin
     * @param i
     * @return
     */
    protected int substract(int origin, int i) {
        float f = i / 100;
        return new Float(origin * (1 - f)).intValue();
    }


}
