package com.h13.slg.event.service;

import com.h13.slg.core.SlgData;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-2
 * Time: 下午11:11
 * To change this template use File | Settings | File Templates.
 */
public interface EventService {
    public void triggerTasks(long uid,SlgData slgData);
}
