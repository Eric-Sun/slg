package com.h13.slg.role.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-5
 * Time: 下午8:06
 * To change this template use File | Settings | File Templates.
 */
public interface RoleService {

    public SlgData wear(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

    public SlgData takeOff(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

}
