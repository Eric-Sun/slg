package com.h13.slg.event.helper;

import com.h13.slg.event.EventType;
import com.h13.slg.event.cache.UserEventCache;
import com.h13.slg.event.co.UserEventCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户事件相关操作
 * <p>用户事件是在用户的某些操作之后会发送事件，然后所有的监听会对这个事件进行响应</p>
 */
@Service
public class UserEventHelper {

    @Autowired
    UserEventCache userEventCache;

    /**
     * 添加用户事件，用户发送事件的时候调用此方法
     * @param uid
     * @param userEventType
     * @param data
     */
    public void addEvent(int uid, EventType userEventType, Map<String, Object> data) {
        UserEventCO userEventCO = new UserEventCO();
        userEventCO.setUid(uid);
        userEventCO.setData(data);
        userEventCO.setEventType(userEventType);
        userEventCache.set(userEventCO);

    }


    /**
     * 获得一个用户所有的时间，通过cache中的poppush方法，进行获取，获取结束之后cache是没有事件了
     * @param uid
     * @return
     */
    public List<UserEventCO> getAllEvents(long uid) {
        List<UserEventCO> list = new LinkedList<UserEventCO>();
        UserEventCO userEventCO = null;
        while ((userEventCO = userEventCache.get(uid)) != null) {
            list.add(userEventCO);
        }
        return list;
    }



}
