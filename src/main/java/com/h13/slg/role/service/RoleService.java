package com.h13.slg.role.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-5
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public interface RoleService {

    public SlgData wear(SlgRequestDTO requestDTO) throws RequestErrorException;

    public SlgData takeOff(SlgRequestDTO requestDTO) throws RequestErrorException;

}
