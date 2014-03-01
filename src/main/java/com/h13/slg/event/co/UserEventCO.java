package com.h13.slg.event.co;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-28
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class UserEventCO {

    private long uid;
    private com.h13.slg.event.EventType EventType;
    private Map<String,Object> data;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
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
