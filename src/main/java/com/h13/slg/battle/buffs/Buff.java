package com.h13.slg.battle.buffs;

/**
 * buff基础类，每个将领都会有一个buff的hash，启动包括了buff的对象
 * buff类为基础的buff，每个skill可能会造成多个buff，和debuff
 * buff永远都是给一个人的，skill在发动的时候会在对方释放buff，然后在回合开始，或者战斗开始前出发此buf
 */
public abstract class Buff {

    protected int roleSkillId;

    protected BuffTimeType buffTimeType = BuffTimeType.GLOBAL;

    protected int onwerUserRoleId;
    protected int curRoundCount = 0;
    protected boolean inited = false;


    /**
     * 此buff是不是已经初始化了
     *
     * @return
     */
    protected boolean isInited() {
        return inited;
    }

    /**
     * 如果触发的过程中，出现stop的异常，则是buff已经结束
     *
     * @param event
     * @param object
     * @throws BuffStoppedException
     */
    public abstract void trigger(BuffEvent event, Object object) throws BuffStoppedException;


    /**
     * 判断是否是全局时长的buff
     *
     * @return
     */
    public boolean isGlobalTimeType() {
        if (buffTimeType == BuffTimeType.GLOBAL)
            return true;
        else
            return false;
    }


}

enum BuffEvent {
    /**
     * 战斗开始前
     */
    BEFORE_FIGHT,
    /**
     * 战斗结束后
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


/**
 * buff持续时间的类型
 */
enum BuffTimeType {
    /**
     * 全局
     */
    GLOBAL,
    /**
     * 回合
     */
    ROUND
}
