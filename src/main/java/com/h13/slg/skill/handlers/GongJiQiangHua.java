package com.h13.slg.skill.handlers;

import com.h13.slg.battle.fight.Fighter;
import com.h13.slg.battle.fight.FightUnit;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.springframework.stereotype.Service;


/**
 * 攻击强化
 * 将领普通技能
 * 自身攻击力 提高1%
 * 需要点数 3
 */
@Service
public class GongJiQiangHua extends BaseRoleSkillHandler {


    @Override
    public void beforeFight(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit) {
    }

    @Override
    public void afterFight(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeAttack(long uid, FightUnit attackFightUnit, FightUnit defenceFightUnit, int attackPos, int defencePos) {
        Fighter attackPosition = attackFightUnit.getAllPos()[attackPos];

        int attack = attackPosition.getAttack();
        RoleSkillCO roleSkillCO = attackPosition.getRoleSkillCO();
        int rate = new Integer(roleSkillCO.getArg1());
        int newAttack = add(attack, rate);

        attackPosition.setAttack(newAttack);
        SlgLogger.info(SlgLoggerEntity.p("fight", "skillHandler", uid, "")
                .addParam("oldAttack", attack)
                .addParam("newAttack", newAttack));
    }
}
