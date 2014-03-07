package com.h13.slg.task.handler;

import com.h13.slg.core.SlgData;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.helper.UserTaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 判断是否穿戴装备，如果穿戴装备的话，更新progress
 */
@Service("WearEquipHandler")
public class WearEquipHandler implements EventHandler {

    @Autowired
    UserTaskHelper userTaskHelper;

    @Override
    public void handleEvent(UserEventCO evtData, Object smallTask) {
        UserSmallTaskCO smallTaskCO = (UserSmallTaskCO) smallTask;
        int target = new Integer(smallTaskCO.getTaskTarget());
        int uid = evtData.getUid();
        UserTaskCO userTaskCO = userTaskHelper.getTask(uid);
        Map<String, Integer> progress = userTaskCO.getProgress();
        int order = smallTaskCO.getOrder();
        int num = progress.get(order + "");
        if (target == num) {
            return;
        } else {
            progress.put(order + "", target);
        }

        userTaskHelper.updateProgress(userTaskCO);
    }
}
