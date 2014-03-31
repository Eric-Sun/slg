package com.h13.slg.battle.fight;

import com.h13.slg.battle.FightConstants;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-26
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FightHandler {

    private List<Integer> l1 = Arrays.asList(0, 3, 6);
    private List<Integer> l2 = Arrays.asList(1, 4, 7);
    private List<Integer> l3 = Arrays.asList(2, 5, 8);

    private List<Integer> l4 = Arrays.asList(0, 1, 2);
    private List<Integer> l5 = Arrays.asList(3, 4, 5);
    private List<Integer> l6 = Arrays.asList(6, 7, 8);


    /**
     * 战斗方法
     *
     * @param attackFightUnit
     * @param defenceFightUnit
     * @return
     */
    public FightResult fight(FightUnit attackFightUnit, FightUnit defenceFightUnit) {

        boolean finished = false;

        FightResult fightResult = new FightResult();
        int round = 1;
        while (!finished) {

            finished = attackByAttack(attackFightUnit, defenceFightUnit, fightResult, round);
            if (finished) {
                fightResult.setStatus(FightResult.WIN);
                break;
            }

            finished = attackByDefence(defenceFightUnit, attackFightUnit, fightResult, round);
            if (finished) {
                fightResult.setStatus(FightResult.LOSE);
                break;
            }

            round++;

        }
        return fightResult;
    }


    public boolean attackByAttack(FightUnit attackFightUnit, FightUnit defenceFightUnit,
                                  FightResult fightResult, int round) {
        // 每个回合从0开始到9，依次攻击
        for (int attackPos = 0; attackPos < 9; attackPos++) {
            if (attackFightUnit.getAllPos()[attackPos] == null)
                continue;

            // Todo:确定这次攻击是否需要群体攻击

            int defencePos = selectDefencePos(attackPos, defenceFightUnit);
            if (defencePos == -1) {
                return true;
            }

            // 开始战斗
            FightPosition attackPosition = attackFightUnit.getAllPos()[attackPos];
            FightPosition defencePosition = defenceFightUnit.getAllPos()[defencePos];

            // 开始计算伤害
            // Todo: 未来需要详细处理这块内容，现在就是简单的计算方法
            int attack = attackPosition.getAttack();
            int defence = defencePosition.getDefence();
            int damage = 0;
            if (attack > defence)
                damage = (attack - defence) / 2;
            else
                damage = 10;

            int remainHealth = defencePosition.getHealth() - damage;
            defencePosition.setHealth(remainHealth <= 0
                    ? 0 : remainHealth);

            FightLog fightLog = new FightLog();
            fightLog.setAttack(FightConstants.ATTACK_ATTACK);
            fightLog.setAttackPos(attackPos);
            fightLog.setDefencePos(new int[]{defencePos});
            Map<Integer, Integer[]> attackStatus = new HashMap<Integer, Integer[]>();
            attackStatus.put(attackPos, new Integer[]{attackPosition.getHealth(), 0});
            Map<Integer, Integer[]> defenceStatus = new HashMap<Integer, Integer[]>();
            defenceStatus.put(defencePos, new Integer[]{defencePosition.getHealth(), damage * -1});
            fightLog.setAttackStatus(attackStatus);
            fightLog.setDefenceStatus(defenceStatus);

            fightResult.addLog(round, fightLog);
        }


        // 检查是否defence方是否都死了
        boolean allDead = true;
        for (int defencePos = 0; defencePos < 9; defencePos++) {
            if (defenceFightUnit.getAllPos()[defencePos] != null
                    && defenceFightUnit.getAllPos()[defencePos].getHealth() > 0) {
                allDead = false;
                break;
            }
        }
        if (allDead)
            return true;

        return false;
    }


    public boolean attackByDefence(FightUnit attackFightUnit, FightUnit defenceFightUnit,
                                   FightResult fightResult, int round) {
        // 每个回合从0开始到9，依次攻击
        for (int attackPos = 0; attackPos < 9; attackPos++) {
            if (attackFightUnit.getAllPos()[attackPos] == null)
                continue;

            // Todo:确定这次攻击是否需要群体攻击

            int defencePos = selectDefencePos(attackPos, defenceFightUnit);
            if (defencePos == -1) {
                return true;
            }

            // 开始战斗
            FightPosition attackPosition = attackFightUnit.getAllPos()[attackPos];
            FightPosition defencePosition = defenceFightUnit.getAllPos()[defencePos];

            // 开始计算伤害
            // Todo: 未来需要详细处理这块内容，现在就是简单的计算方法
            int attack = attackPosition.getAttack();
            int defence = defencePosition.getDefence();
            int damage = 0;
            if (attack > defence)
                damage = (attack - defence) / 2;
            else
                damage = 10;


            int remainHealth = defencePosition.getHealth() - damage;
            defencePosition.setHealth(remainHealth <= 0
                    ? 0 : remainHealth);

            FightLog fightLog = new FightLog();
            fightLog.setAttack(FightConstants.DEFENCE_ATTACK);
            fightLog.setAttackPos(attackPos);
            fightLog.setDefencePos(new int[]{defencePos});
            Map<Integer, Integer[]> attackStatus = new HashMap<Integer, Integer[]>();
            attackStatus.put(attackPos, new Integer[]{attackPosition.getHealth(), 0});
            Map<Integer, Integer[]> defenceStatus = new HashMap<Integer, Integer[]>();
            defenceStatus.put(defencePos, new Integer[]{defencePosition.getHealth(), damage * -1});
            fightLog.setAttackStatus(attackStatus);
            fightLog.setDefenceStatus(defenceStatus);

            fightResult.addLog(round, fightLog);

        }


        // 检查是否attack方是否都死了
        boolean allDead = true;
        for (int attackPos = 0; attackPos < 9; attackPos++) {
            if (attackFightUnit.getAllPos()[attackPos] != null
                    && attackFightUnit.getAllPos()[attackPos].getHealth() > 0) {
                allDead = false;
                break;
            }
        }
        if (allDead)
            return true;


        return false;
    }

    /**
     * 为对应的攻击者选择一个防守它这次攻击的人
     * 如果返回-1标示没有人存活，战斗结束
     *
     * @param attackPos
     * @param defenceFightUnit
     * @return
     */
    private int selectDefencePos(int attackPos, FightUnit defenceFightUnit) {
        // 查看竖排有攻击可能性么？
        // 查看 她到底在那一竖排上，看看她对应的竖排有没有可能被攻击的人
        if (l1.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l1) {
                if (defenceFightUnit.getAllPos()[i] != null &&
                        defenceFightUnit.getAllPos()[i].getHealth() > 0)
                    return i;
            }
        }
        if (l2.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l2) {
                if (defenceFightUnit.getAllPos()[i] != null &&
                        defenceFightUnit.getAllPos()[i].getHealth() > 0)
                    return i;
            }
        }
        if (l3.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l3) {
                if (defenceFightUnit.getAllPos()[i] != null &&
                        defenceFightUnit.getAllPos()[i].getHealth() > 0)
                    return i;
            }
        }

        // l1 ,l2 , l3 一次查看
        for (int i : l1) {
            if (defenceFightUnit.getAllPos()[i] != null &&
                    defenceFightUnit.getAllPos()[i].getHealth() > 0)
                return i;
        }
        for (int i : l2) {
            if (
                    defenceFightUnit.getAllPos()[i] != null &&
                            defenceFightUnit.getAllPos()[i].getHealth() > 0)
                return i;
        }
        for (int i : l3) {
            if (defenceFightUnit.getAllPos()[i] != null &&
                    defenceFightUnit.getAllPos()[i].getHealth() > 0)
                return i;
        }

        return -1;
    }
}
