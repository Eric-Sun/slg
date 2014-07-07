package com.h13.slg.task.handler;

import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
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
    @Override
    public void handleEvent(UserEventCO evtData, Object listenerData) {

    }
}
