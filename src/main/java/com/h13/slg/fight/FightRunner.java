package com.h13.slg.fight;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.fight.buff.Buff;
import com.h13.slg.fight.buff.BuffTriggerTime;
import com.h13.slg.fight.log.FUserRoleStatusLog;
import com.h13.slg.fight.log.FightLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 战斗的运行着
 */
@Service
public class FightRunner {


    private static List<Integer> l1 = Arrays.asList(0, 3, 6);
    private static List<Integer> l2 = Arrays.asList(1, 4, 7);
    private static List<Integer> l3 = Arrays.asList(2, 5, 8);

    @Autowired

    private FCreator fCreator;


    /**
     * 战斗的方法，Runner对象对外暴露的方法就是此方法
     *
     * @param request
     * @return
     */
    public FResponse doFight(int uid, FRequest request) throws RequestUnexpectedException, RequestFatalException {
        FResponse response = new FResponse();

        // init context .
        FContext fContext = initContext(request);
        SlgLogger.info(SlgLoggerEntity.p("fight", "doFight", uid,
                String.format("Begin Do fight. fightType=%s , defenceId=%s",
                        request.getFightType(),
                        request.getDefenceId())));

        // 战斗前触发
        triggerFContext(BuffTriggerTime.BEFORE_FIGHT, fContext);

        // 开始战斗
        // 每个round进攻方开始攻击
        int round = 1;
        fContext.setCurrentRound(round);
        while (true) {
            triggerFContext(BuffTriggerTime.BEFORE_ROUND, fContext);
            fContext.setCurrentAction(FightConstants.Action.ATTACK);
            fightRound(fContext);
            if (fContext.getFightStatus().equals(FightConstants.Status.END)) {
                fContext.setWin(FightConstants.Win.ATTACK);
                break;
            }
            fContext.setCurrentAction(FightConstants.Action.DEFENCE);
            fightRound(fContext);
            triggerFContext(BuffTriggerTime.AFTER_ROUND, fContext);
            round++;
            fContext.setCurrentRound(round);
            if (fContext.getFightStatus().equals(FightConstants.Status.END)) {
                fContext.setWin(FightConstants.Win.DEFENCE);
                break;
            }
        }

        // 战斗后触发
        triggerFContext(BuffTriggerTime.AFTER_FIGHT, fContext);

        SlgLogger.info(SlgLoggerEntity.p("fight", "doFight", uid,
                String.format("End Do fight. fightType=%s , defenceId=%s",
                        request.getFightType(),
                        request.getDefenceId())));


        response.setLogList(fContext.getLogList());
        response.setWin(fContext.getWin());
        return response;

    }


    /**
     * 每回合的战斗,进攻方战斗
     *
     * @param fContext
     */
    private void fightRound(FContext fContext) throws RequestFatalException {
        FUser attackFUser = null;
        FUser defenceFUser = null;
        if (fContext.getCurrentAction().equals(FightConstants.Action.ATTACK)) {
            attackFUser = fContext.getAttack();
            defenceFUser = fContext.getDefence();
        } else {
            attackFUser = fContext.getDefence();
            defenceFUser = fContext.getAttack();
        }
        // attack first
        for (int i = 0; i < attackFUser.getUserRoleList().size(); i++) {
            if (attackFUser.getUserRoleList().get(i) == null)
                continue;
            FUserRole attackFUserRole = attackFUser.getUserRoleList().get(i);
            int defencePos = selectDefencePos(i, defenceFUser);
            if (defencePos == -1) {
                fContext.setFightStatus(FightConstants.Status.END);
                return;
            }
            FUserRole defenceFUserRole = defenceFUser.getUserRoleList().get(defencePos);
            // 攻击
            int damage = AttackUtil.commonAttack(attackFUserRole, defenceFUserRole);
            int specialAttack = 0;
            // 特殊攻击相关
            boolean doSpacialAttack = AttackUtil.checkRestraint(attackFUserRole, defenceFUserRole);
            if (doSpacialAttack) {
                specialAttack = new Double(AttackUtil.commonAttack(attackFUserRole, defenceFUserRole) * FightConstants.Parameter.SPECIAL_ATTACK_FIRST_RATE).intValue();

                if (attackFUserRole.getAttack() > FightConstants.Parameter.ENHANCE_SPECIAL_ATTACK_ATTACK) {
                    int specialAttack2 = new Double(damage * 0.15).intValue();
                    specialAttack = specialAttack + specialAttack2;
                }
            }

            defenceFUserRole.addSubedHealth(damage + specialAttack);

            // second log
            FightLog fightLog = new FightLog();
            fightLog.setAciton(fContext.getCurrentAction());
            fightLog.setAttackPos(attackFUserRole.getPos());
            fightLog.setDefencePos(new int[]{defenceFUserRole.getPos()});

            // set status
            FUserRoleStatusLog attackStatus = new FUserRoleStatusLog();
            attackStatus.setDamage(0);
            attackStatus.setRemainHealth(attackFUserRole.getFinalHealth());

            FUserRoleStatusLog defenceStatus = new FUserRoleStatusLog();
            defenceStatus.setDamage(damage);
            defenceStatus.setRemainHealth(defenceFUserRole.getFinalHealth());
            fightLog.setDefenceStatus(new FUserRoleStatusLog[]{defenceStatus});

            fContext.addLog(fContext.getCurrentRound(), fightLog);
        }
    }


    /**
     * 为对应的攻击者选择一个防守它这次攻击的人
     * 如果返回-1标示没有人存活，战斗结束
     *
     * @return
     */
    private int selectDefencePos(int attackPos, FUser defenceFUser) {
        // 查看竖排有攻击可能性么？
        // 查看 她到底在那一竖排上，看看她对应的竖排有没有可能被攻击的人
        if (l1.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l1) {
                if (defenceFUser.getUserRoleList().get(i) != null &&
                        defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                    return i;
            }
        }
        if (l2.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l2) {
                if (defenceFUser.getUserRoleList().get(i) != null &&
                        defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                    return i;
            }
        }
        if (l3.contains(attackPos)) {
            // 这一竖排是否有可以攻击的
            for (int i : l3) {
                if (defenceFUser.getUserRoleList().get(i) != null &&
                        defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                    return i;
            }
        }

        // l1 ,l2 , l3 一次查看
        for (int i : l1) {
            if (defenceFUser.getUserRoleList().get(i) != null &&
                    defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                return i;
        }
        for (int i : l2) {
            if (
                    defenceFUser.getUserRoleList().get(i) != null &&
                            defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                return i;
        }
        for (int i : l3) {
            if (defenceFUser.getUserRoleList().get(i) != null &&
                    defenceFUser.getUserRoleList().get(i).getFinalHealth() > 0)
                return i;
        }

        return -1;
    }

    private void triggerFContext(String triggerTime, FContext fContext) {

        fContext.setCurrentAction(FightConstants.Action.ATTACK);
        for (int i = 0; i < fContext.getAttack().getUserRoleList().size(); i++) {
            fContext.setCurrentPos(i);
            FUserRole fUserRole = fContext.getAttack().getUserRoleList().get(i);
            if (fUserRole == null)
                continue;
            triggerFUserRole(triggerTime, fUserRole, fContext);
        }


        fContext.setCurrentAction(FightConstants.Action.DEFENCE);
        for (int i = 0; i < fContext.getDefence().getUserRoleList().size(); i++) {
            fContext.setCurrentPos(i);
            FUserRole fUserRole = fContext.getDefence().getUserRoleList().get(i);
            if (fUserRole == null)
                continue;
            triggerFUserRole(triggerTime, fUserRole, fContext);
        }


    }


    private void triggerFUserRole(String triggerTime, FUserRole fUserRole, FContext fContext) {
        for (Buff buff : fUserRole.getBuffList()) {
            buff.trigger(triggerTime, fContext);
        }

    }


    private FContext initContext(FRequest request) throws RequestUnexpectedException, RequestFatalException {
        FContext fContext = new FContext();

        // new F Users
        // init attack always User

        FUser defenceUser = null;
        FUser attackUser = fCreator.createByUser(request.getAttackUserId());
        if (request.getFightType().equals(FightConstants.FightType.PVE)) {
            defenceUser = fCreator.createByBattle(request.getDefenceId());
        } else {
            defenceUser = fCreator.createByUser(request.getDefenceId());
        }


        fContext.setAttack(attackUser);
        fContext.setDefence(defenceUser);
        return fContext;
    }


}
