package com.h13.slg.event.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.task.vo.FinishedPerTaskVO;
import com.h13.slg.task.vo.FinishedTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-2
 * Time: 下午11:00
 * To change this template use File | Settings | File Templates.
 */
@Service("EventService")
public class EventServiceImpl implements EventService {

    @Autowired
    UserEventHelper userEventHelper;
    @Autowired
    UserTaskHelper userTaskHelper;


    public void triggerTasks(int uid, SlgData slgData) throws RequestErrorException {
        List<UserEventCO> evtList = userEventHelper.getAllEvents(uid);
        if (evtList.size() == 0)
            return;
        List<FinishedPerTaskVO> list = new LinkedList<FinishedPerTaskVO>();
        boolean finished = false;
        while ((finished = userTaskHelper.handleEvents(uid, evtList, slgData, list)) != true) {

        }
    }


}
