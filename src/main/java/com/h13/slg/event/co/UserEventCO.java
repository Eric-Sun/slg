package com.h13.slg.event.co;

import java.util.Map;

/**
 * 用户事件对象
 */
public class UserEventCO {

    private int uid;
    private com.h13.slg.event.EventType EventType;
    private Map<String,Object> data;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public com.h13.slg.event.EventType getEventType() {
        return EventType;
    }

    public void setEventType(com.h13.slg.event.EventType eventType) {
        EventType = eventType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
