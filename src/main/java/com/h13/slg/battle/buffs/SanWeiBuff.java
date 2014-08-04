package com.h13.slg.battle.buffs;

import com.h13.slg.battle.fight.Fighter;

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


    /**
     * 全局的三围buf
     *
     * @param urid
     * @param attackRate
     * @param defenceRate
     * @param healthRate
     */
    public SanWeiBuff(int urid, int attackRate, int defenceRate, int healthRate) {
        this(urid, attackRate, defenceRate, healthRate, 0);
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
    public SanWeiBuff(int urid, int attackRate, int defenceRate, int healthRate, int roundCount) {

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
    public void trigger(BuffEvent event, Object object) throws BuffStoppedException {

        switch (event) {
            case BEFORE_FIGHT:
                if (!isGlobalTimeType())
                    break;
                if (isInited())
                    break;
                if (object instanceof Fighter) {
                    Fighter fighter = (Fighter) object;
                    int newAttack = cul(fighter.getAttack(), attackRate);
                    int newDefence = cul(fighter.getDefence(), defenceRate);
                    int newHealth = cul(fighter.getHealth(), healthRate);
                    this.addAttack = newAttack - fighter.getAttack();
                    this.addDefence = newDefence - fighter.getDefence();
                    this.addHealth = newHealth - fighter.getHealth();

                    fighter.setAttack(newAttack);
                    fighter.setDefence(newDefence);
                    fighter.setHealth(newHealth);
                    curRoundCount++;
                }
                inited = true;
                break;
            case BEFORE_ROUND:
                if (isGlobalTimeType())
                    break;
                if (isInited())
                    break;
                if (object instanceof Fighter) {
                    Fighter fighter = (Fighter) object;
                    int newAttack = cul(fighter.getAttack(), attackRate);
                    int newDefence = cul(fighter.getDefence(), defenceRate);
                    int newHealth = cul(fighter.getHealth(), healthRate);
                    this.addAttack = newAttack - fighter.getAttack();
                    this.addDefence = newDefence - fighter.getDefence();
                    this.addHealth = newHealth - fighter.getHealth();

                    fighter.setAttack(newAttack);
                    fighter.setDefence(newDefence);
                    fighter.setHealth(newHealth);
                    curRoundCount++;
                }
                inited = true;
                break;
            case AFTER_ROUND:
                if (isGlobalTimeType())
                    break;
                if (curRoundCount == roundCount) {
                    if (object instanceof Fighter) {
                        Fighter fighter = (Fighter) object;
                        fighter.setAttack(fighter.getAttack() - addAttack);
                        fighter.setDefence(fighter.getDefence() - addDefence);
                        fighter.setHealth(fighter.getHealth() - addHealth);
                    }
                }
                throw new BuffStoppedException();
            case AFTER_FIGHT:
                if (!isGlobalTimeType())
                    break;
                if (object instanceof Fighter) {
                    Fighter fighter = (Fighter) object;
                    fighter.setAttack(fighter.getAttack() - addAttack);
                    fighter.setDefence(fighter.getDefence() - addDefence);
                    fighter.setHealth(fighter.getHealth() - addHealth);
                }
                throw new BuffStoppedException();

        }


    }


    private int cul(int old, int rate) {
        return (old * (100 + rate)) / 100;

    }

}
