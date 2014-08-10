package com.h13.slg.battle.buffs;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-8-4
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public enum BuffEvent {
    /**
     * 战斗开始前
     */
    BEFORE_FIGHT,
    /**
     * 战斗结束后,去掉所有的round和global的buff
     */
    AFTER_FIGHT,
    /**
     * 用于在每回合开始前触发，比如提高攻击力，如果立即生效的话，需要立即出发before_round事件
     */
    BEFORE_ROUND,
    /**
     * 如果在回合结束时候需要停止的buff，就会停止
     */
    AFTER_ROUND,
    /**
     * 开始攻击前
     */
    BEFORE_ATTACK,
    /**
     * 开始攻击后
     */
    AFTER_ATTACK,
    /**
     * 开始防御前
     */
    BEFORE_DEFENCE,
    /**
     * 结束防御后
     */
    AFTER_DEFENCE

}
