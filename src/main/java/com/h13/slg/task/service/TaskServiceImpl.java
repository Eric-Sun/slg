package com.h13.slg.task.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class TaskServiceImpl implements TaskService {

    @Override
    public SlgData get(SlgRequestDTO request) throws RequestFatalException {
        long uid = request.getUid();




        return null;
    }
}
