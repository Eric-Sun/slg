package com.h13.slg.core.cache.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-23
 * Time: 下午6:35
 * To change this template use File | Settings | File Templates.
 */
public class WaitingEventCO {
    private long userId;
    private String value;
    // 用来检车这个event是否已经完成的class
    private Class handlerClazz;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class getHandlerClazz() {
        return handlerClazz;
    }

    public void setHandlerClazz(Class handlerClazz) {
        this.handlerClazz = handlerClazz;
    }
}
