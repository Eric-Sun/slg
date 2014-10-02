package com.h13.slg.task.handler;

import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.EventType;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.helper.UserTaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-7
 * Time: 下午7:37
 * To change this template use File | Settings | File Templates.
 */
@Service("HarvestGoldHandler")
public class HarvestGoldHandler implements EventHandler {

    @Autowired
    UserTaskHelper userTaskHelper;

    @Override
    public void handleEvent(UserEventCO evtData,  UserSmallTaskCO task) {
        int uid = evtData.getUid();
        if (evtData.getEventType() != EventType.HarvestGold) {
            SlgLogger.debug(SlgLoggerEntity.p("task", "handler", uid, "evtType is not match. HandlerType=" + EventType.WearEquip + " userEventType=" + evtData.getEventType()));
            return;
        }
        int order = task.getOrder();
        UserTaskCO userTaskCO = userTaskHelper.getTask(uid);
        int curValue = userTaskCO.getProgress().get(order + "");
        int expectValue = new Integer(task.getTaskTarget());
        if (curValue == expectValue) {
            return;
        } else {
            curValue++;
            userTaskCO.getProgress().put(order + "", curValue);
        }
        userTaskHelper.updateProgress(userTaskCO);

    }
}
