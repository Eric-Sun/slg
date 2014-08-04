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


