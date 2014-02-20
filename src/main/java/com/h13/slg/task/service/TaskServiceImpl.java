package com.h13.slg.task.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
public class TaskServiceImpl implements TaskService {

    @Override
    public SlgData get(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();




        return null;
    }
}
