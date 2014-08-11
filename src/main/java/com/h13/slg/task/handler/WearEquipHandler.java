package com.h13.slg.task.handler;

import com.h13.slg.core.SlgData;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.EventType;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.helper.UserTaskHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 判断是否穿戴装备，如果穿戴装备的话，更新progress
 */
@Service("WearEquipHandler")
public class WearEquipHandler implements EventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WearEquipHandler.class);

    @Autowired
    UserTaskHelper userTaskHelper;

    @Override
    public void handleEvent(UserEventCO evtData, Object smallTask) {
        if (evtData.getEventType() != EventType.WearEquip) {
            return;
        }

        UserSmallTaskCO smallTaskCO = (UserSmallTaskCO) smallTask;
        int target = new Integer(smallTaskCO.getTaskTarget());
        long uid = evtData.getUid();
        UserTaskCO userTaskCO = userTaskHelper.getTask(uid);
        Map<String, Integer> progress = userTaskCO.getProgress();
        int order = smallTaskCO.getOrder();
        int num = progress.get(order + "");
        if (target == num) {
            return;
        } else {
            num++;
            progress.put(order + "", num);
        }

        userTaskHelper.updateProgress(userTaskCO);
    }
}
