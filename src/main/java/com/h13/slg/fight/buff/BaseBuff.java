package com.h13.slg.fight.buff;

import com.h13.slg.fight.FContext;
import com.h13.slg.fight.FUserRole;
import com.h13.slg.fight.FightConstants;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-16
 * Time: 上午12:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseBuff implements Buff {

    protected String[] args = null;

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public void trigger(String currentTriggerTime, FContext fContext) {
        // check
        if (currentTriggerTime.equals(getCurrentTriggerTime())) {
            doTrigger(fContext);
        }
    }

    protected abstract void doTrigger(FContext fContext);

    protected abstract String getCurrentTriggerTime();


    protected FUserRole getCurrentFUserRole(FContext fContext) {
        FUserRole current = null;
        if (fContext.getCurrentAction().equals(FightConstants.Action.ATTACK)) {
            current = fContext.getAttack().getUserRoleList().get(fContext.getCurrentPos());
        } else {
            current = fContext.getDefence().getUserRoleList().get(fContext.getCurrentPos());
        }

        return current;
    }

}
