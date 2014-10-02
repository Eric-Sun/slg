package com.h13.slg.event;

import com.h13.slg.core.SlgData;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserSmallTaskCO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-28
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public interface EventHandler {

     String TARGET = "target";

    public void handleEvent(UserEventCO evtData, UserSmallTaskCO smallTaskCO);
}
