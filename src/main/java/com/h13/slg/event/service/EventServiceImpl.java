package com.h13.slg.event.service;

import com.h13.slg.core.SlgData;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-2
 * Time: 下午11:00
 * To change this template use File | Settings | File Templates.
 */
@Service("EventService")
public class EventServiceImpl {

    @Autowired
    UserEventHelper userEventHelper;
    @Autowired
    UserTaskHelper userTaskHelper;


    public void triggerTasks(long uid, SlgData slgData) {
        List<UserEventCO> evtList = userEventHelper.getAllEvents(uid);
        userTaskHelper.handleEvents(uid, evtList, slgData);
    }


}
