package com.h13.slg.event.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;

/**
 * 内部类的调用
 */
public interface EventService {


    /**
     * 出发某个用户的所有的任务监听
     * @param uid
     * @param slgData
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public void triggerTasks(int uid,SlgData slgData)throws RequestFatalException;



}
