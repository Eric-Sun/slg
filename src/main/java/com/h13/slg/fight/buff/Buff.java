package com.h13.slg.fight.buff;

import com.h13.slg.fight.FContext;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-15
 * Time: 下午9:47
 * To change this template use File | Settings | File Templates.
 */
public interface Buff {

    public void setArgs(String[] args);

    public void trigger(BuffTriggerTime triggerTime, FContext fContext);
}
