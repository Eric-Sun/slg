package com.h13.slg.battle.fight;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.h13.slg.battle.FightConstants;
import com.h13.slg.battle.buffs.Buff;
import com.h13.slg.battle.buffs.BuffEvent;
import com.h13.slg.battle.buffs.BuffStoppedException;
import com.h13.slg.battle.buffs.SanWeiBuff;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.core.util.SlgStrings;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.skill.helper.RoleSkillHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在以下两种情况的时候，这个类发挥作用
 * 1. 战斗开始前，触发双方的天赋技能
 * 2. 战斗进行时，当将领技能被出发的时候
 * <p/>
 * 主要功能为解析roleSkill，然后解析相关参数，给对方或者己方增加buff
 */
@Service
public class RoleSkillRunner {

    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;


    /**
     * 发送天赋技能的时候触发
     *
     * @param tianfuRoleSkillId 天赋技能id
     * @param attack            发动技能方
     * @param defence           被动方
     */
    public void run(Fighter originFighter, int tianfuRoleSkillId, FightUnit attack, FightUnit defence) {
        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(tianfuRoleSkillId + "");

        String runAttack = roleSkillCO.getRunParamsAttack();
        String runDefence = roleSkillCO.getRunParamsDefence();
        String runHealth = roleSkillCO.getRunParamsHealth();
        String runDamage = roleSkillCO.getRunParamsDamage();
        String runRound = roleSkillCO.getRunRound();
        String runType = roleSkillCO.getRunType();
        String runTarget = roleSkillCO.getRunTarget();
        String runStart = roleSkillCO.getRunStart();


        // 获取buff释放对象
        List<Fighter> fighterList = getBuffTargetList(originFighter, runTarget, runType, attack, defence);


        // 如果三个其中有一个不为0的话
        // 则开启 三围的buff
        if (!SlgStrings.isZeroOrEmptyOrNull(runAttack)
                || !SlgStrings.isZeroOrEmptyOrNull(runDefence)
                || !SlgStrings.isZeroOrEmptyOrNull(runHealth)) {

            addSanWeiBuff(fighterList, new SanWeiBuff(
                    originFighter.getId(),
                    new Integer(runAttack), new Integer(runDefence), new Integer(runHealth),
                    new Integer(runRound)
            ));

        }

        //
        if (runStart.equals(FightConstants.BuffStartTime.NOW)) {
            if (runRound.equals(FightConstants.Round.GLOBAL)) {
                event(BuffEvent.BEFORE_FIGHT, fighterList);
            } else {
                event(BuffEvent.BEFORE_ROUND, fighterList);
            }

        }


    }

    /**
     * 触发事件
     *
     * @param buffEvent
     * @param fights
     */
    public void event(BuffEvent buffEvent, List<Fighter> fights) {

        for (Fighter f : fights) {
            for (Buff buff : f.getBuffList()) {
                try {
                    buff.trigger(buffEvent, f);
                } catch (BuffStoppedException e) {
                    // 去掉某个buff
                    f.getBuffList().remove(buff);
                }
            }

        }


    }


    private void addSanWeiBuff(List<Fighter> fighterList, SanWeiBuff sanWeiBuff) {

        for (Fighter f : fighterList) {
            f.getBuffList().add(sanWeiBuff);
        }

    }

    private List<Fighter> getBuffTargetList(Fighter originFighter,
                                            String runTarget,
                                            String runType,
                                            FightUnit attack,
                                            FightUnit defence) {
        List<Fighter> fighters = Lists.newLinkedList();
        FightUnit fightUnit = null;
        if (runTarget.equals(FightConstants.Target.ZIJI)) {
            fightUnit = attack;
        } else {
            fightUnit = defence;
        }
        if (runType.equals(FightConstants.BuffType.DANTI)) {
            fighters.add(originFighter);
        } else if (runType.equals(FightConstants.BuffType.GONG)
                || runType.equals(FightConstants.BuffType.QI)
                || runType.equals(FightConstants.BuffType.QIANG)) {
            fighters = findXi(runType, fightUnit);
        } else if (runType.equals(FightConstants.BuffType.ALL)) {
            fighters = Lists.newArrayList(fightUnit.getAllPos());
        } else {
            // 十字
        }

        return fighters;
    }

    private List<Fighter> findXi(String xi, FightUnit fightUnit) {
        List<Fighter> fighters = Lists.newLinkedList();
        for (Fighter f : fightUnit.getAllPos()) {
            if (f.getSoldierType().equals(xi)) {
                fighters.add(f);
            }
        }
        return fighters;
    }

}
