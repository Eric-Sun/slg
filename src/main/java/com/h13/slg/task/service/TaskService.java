package com.h13.slg.task.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 上午1:00
 * To change this template use File | Settings | File Templates.
 */
public interface TaskService {

    public SlgData get(SlgRequestDTO request) throws RequestFatalException;

}
