package com.h13.slg.fight;

import com.h13.slg.core.exception.RequestFatalException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-16
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public class AttackUtil {


    /**
     * 普通战斗，计算伤害
     * <p/>
     * 普攻伤害 = INT((双方兵力和)/1000)+(1+(双方兵力和)/1000)*5/3*(1+(攻击方攻击力-防守方防御力)/50)
     *
     * @param attack
     * @param defence
     * @return
     */
    public static int commonAttack(FUserRole attack, FUserRole defence) {
        int damage = attack.getFinalAttack() - defence.getFinalDefence();

        return damage;
    }


    /**
     * 0>1>2>0
     *
     * @param attack
     * @param defence
     * @return
     */
    public static boolean checkRestraint(FUserRole attack, FUserRole defence) throws RequestFatalException {
        if (attack.getSoldier() == 0) {
            if (defence.getSoldier() == 1) {
                return true;
            } else
                return false;
        }

        if (attack.getSoldier() == 1) {
            if (defence.getSoldier() == 2) {
                return true;
            } else
                return false;
        }


        if (attack.getSoldier() == 2) {
            if (defence.getSoldier() == 0) {
                return true;
            } else
                return false;
        }

        throw new RequestFatalException(String.format(
                "soldier type is error. attackSoldier=%s,defenceSoldier=%s", attack.getSoldier(), defence.getSoldier()));

    }
}
