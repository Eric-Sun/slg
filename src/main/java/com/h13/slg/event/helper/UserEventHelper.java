package com.h13.slg.event.helper;

import com.h13.slg.event.EventType;
import com.h13.slg.event.cache.UserEventCache;
import com.h13.slg.event.co.UserEventCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-28
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserEventHelper {

    @Autowired
    UserEventCache userEventCache;

    public void addEvent(long uid, EventType userEventType, Map<String, Object> data) {
        UserEventCO userEventCO = new UserEventCO();
        userEventCO.setUid(uid);
        userEventCO.setData(data);
        userEventCO.setEventType(userEventType);
        userEventCache.set(userEventCO);

    }


    public List<UserEventCO> getAllEvnets(long uid) {
        return userEventCache.get(uid);
    }



}
