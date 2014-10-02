package com.h13.slg.task.helper;

import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserSmallTaskCO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-3
 * Time: 上午12:45
 * To change this template use File | Settings | File Templates.
 */
public class UserTaskHolder {

    private EventHandler handler;

    public EventHandler getHandler() {
        return handler;
    }

    public void setHandler(EventHandler handler) {
        this.handler = handler;
    }


    public UserSmallTaskCO smallTask;

    public UserSmallTaskCO getSmallTask() {
        return smallTask;
    }

    public void setSmallTask(UserSmallTaskCO smallTask) {
        this.smallTask = smallTask;
    }


    public void doHandler(UserEventCO userEventCO){
        handler.handleEvent(userEventCO,smallTask);
    }


}


