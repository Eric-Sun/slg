package com.h13.slg.battle.buffs;

import com.google.common.collect.Lists;
import com.h13.slg.battle.fight.FightBuffLog;
import com.h13.slg.battle.fight.FightResult;
import com.h13.slg.battle.fight.Fighter;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * 增加fighter三围类型技能
 */
public class SanWeiBuff extends Buff {

    private int attackRate;
    private int defenceRate;
    private int healthRate;
    private int roundCount = 0;

    private int addAttack;
    private int addDefence;
    private int addHealth;
    private int uid;

    /**
     * 全局的三围buf
     *
     * @param urid
     * @param attackRate
     * @param defenceRate
     * @param healthRate
     */
    public SanWeiBuff(int uid, int urid, int attackRate, int defenceRate, int healthRate) {
        this(uid, urid, attackRate, defenceRate, healthRate, 0);
    }

    /**
     * 回合的三围buf
     *
     * @param urid
     * @param attackRate
     * @param defenceRate
     * @param healthRate
     * @param roundCount
     */
    public SanWeiBuff(int uid, int urid, int attackRate, int defenceRate, int healthRate, int roundCount) {

        this.uid = uid;
        onwerUserRoleId = urid;
        this.attackRate = attackRate;
        this.defenceRate = defenceRate;
        this.healthRate = healthRate;
        this.roundCount = roundCount;
        if (roundCount != 0) {
            buffTimeType = BuffTimeType.ROUND;
        } else {
            buffTimeType = BuffTimeType.GLOBAL;
        }
    }


    @Override
    public void trigger(BuffEvent event, Object object, FightResult fightResult, int round) throws BuffStoppedException {
        Fighter fighter = null;
        int newAttack = 0;
        int newDefence = 0;
        int newHealth = 0;
        switch (event) {
            case BEFORE_FIGHT:
                if (!isGlobalTimeType()) {
                    break;
                }
                if (isInited())
                    break;
                fighter = (Fighter) object;
                newAttack = cal(fighter.getAttack(), attackRate);
                newDefence = cal(fighter.getDefence(), defenceRate);
                newHealth = cal(fighter.getHealth(), healthRate);
                this.addAttack = newAttack - fighter.getAttack();
                this.addDefence = newDefence - fighter.getDefence();
                this.addHealth = newHealth - fighter.getHealth();

                fighter.setAttack(newAttack);
                fighter.setDefence(newDefence);
                fighter.setHealth(newHealth);
                curRoundCount++;
                SlgLogger.info(SlgLoggerEntity.p("battle", "fight", uid, "trigger sanwei buff when BEFORE_FIGHT")
                        .addParam("fighter.id", fighter.getId())
                        .addParam("addAttack", this.addAttack)
                        .addParam("addDefence", this.addDefence)
                        .addParam("addHealth", this.addHealth));
                startLog(round, fightResult, fighter);
                inited = true;

                break;
            case BEFORE_ROUND:
                if (isGlobalTimeType())
                    break;
                if (isInited())
                    break;
                fighter = (Fighter) object;
                newAttack = cal(fighter.getAttack(), attackRate);
                newDefence = cal(fighter.getDefence(), defenceRate);
                newHealth = cal(fighter.getHealth(), healthRate);
                this.addAttack = newAttack - fighter.getAttack();
                this.addDefence = newDefence - fighter.getDefence();
                this.addHealth = newHealth - fighter.getHealth();

                fighter.setAttack(newAttack);
                fighter.setDefence(newDefence);
                fighter.setHealth(newHealth);
                curRoundCount++;

                SlgLogger.info(SlgLoggerEntity.p("battle", "fight", uid, "trigger sanwei buff when BEFORE_ROUND")
                        .addParam("fighter.id", fighter.getId())
                        .addParam("addAttack", this.addAttack)
                        .addParam("addDefence", this.addDefence)
                        .addParam("addHealth", this.addHealth)
                        .addParam("curRoundCount", curRoundCount));
                startLog(round, fightResult, fighter);
                inited = true;
                break;
            case AFTER_ROUND:
                if (isGlobalTimeType())
                    break;
                if (curRoundCount == roundCount) {
                    fighter = (Fighter) object;
                    fighter.setAttack(fighter.getAttack() - addAttack);
                    fighter.setDefence(fighter.getDefence() - addDefence);
                    fighter.setHealth(fighter.getHealth() - addHealth);
                    SlgLogger.info(SlgLoggerEntity.p("battle", "fight", uid, "release sanwei buff when AFTER_ROUND")
                            .addParam("fighter.id", fighter.getId())
                    );

                }
                fighter = (Fighter) object;
                stopLog(round, fightResult, fighter);
                throw new BuffStoppedException();
            case AFTER_FIGHT:
                fighter = (Fighter) object;
                fighter.setAttack(fighter.getAttack() - addAttack);
                fighter.setDefence(fighter.getDefence() - addDefence);
                fighter.setHealth(fighter.getHealth() - addHealth);
                SlgLogger.info(SlgLoggerEntity.p("battle", "fight", uid, "release sanwei buff when AFTER_FIGHT")
                        .addParam("fighter.id", fighter.getId())
                );
                stopLog(round, fightResult, fighter);
                throw new BuffStoppedException();

        }


    }


    private int cal(int old, int rate) {
        return (old * (100 + rate)) / 100;
    }


    private void startLog(int round, FightResult fightResult, Fighter fighter) {
        FightBuffLog buffLog = new FightBuffLog();
        buffLog.setSkillType("sanwei");
        buffLog.setOwner(fighter.getOwner());
        buffLog.setPos(fighter.getPos());
        buffLog.setType("startBuff");
        buffLog.setRoleName(fighter.getName());
        List<Object> list = Lists.newLinkedList();
        buffLog.setStatus(list);
        Integer[] attack = new Integer[]{attackRate, addAttack, fighter.getAttack()};
        Integer[] defence = new Integer[]{defenceRate, addDefence, fighter.getDefence()};
        Integer[] health = new Integer[]{healthRate, addHealth, fighter.getHealth()};
        list.add(attack);
        list.add(defence);
        list.add(health);
        fightResult.addLog(round, buffLog);
    }


    private void stopLog(int round, FightResult fightResult, Fighter fighter) {
        FightBuffLog buffLog = new FightBuffLog();
        buffLog.setSkillType("sanwei");
        buffLog.setOwner(fighter.getOwner());
        buffLog.setPos(fighter.getPos());
        buffLog.setType("stopBuff");
        buffLog.setRoleName(fighter.getName());
        List<Object> list = Lists.newLinkedList();
        buffLog.setStatus(list);
        Integer[] attack = new Integer[]{attackRate, addAttack, fighter.getAttack()};
        Integer[] defence = new Integer[]{defenceRate, addDefence, fighter.getDefence()};
        Integer[] health = new Integer[]{healthRate, addHealth, fighter.getHealth()};
        list.add(attack);
        list.add(defence);
        list.add(health);
        fightResult.addLog(round, buffLog);
    }
}
