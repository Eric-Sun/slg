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


    /**
     * {"code":0,"desc":"","mod":"role","act":"wear",
     * "args":{"rid":6,"isskill":0,"id":10},"data":{},"serverTime":1394009707}
     * r
     *
     * @param requestDTO
     * @return
     */
    public SlgData wear(SlgRequestDTO requestDTO) throws RequestErrorException;


    /**
     * {"code":0,"desc":"","mod":"role","act":"take_off",
     * "args":{"erid":6,"id":10},"data":{},"serverTime":1394009742}
     *
     * @param requestDTO
     * @return
     */
    public SlgData takeOff(SlgRequestDTO requestDTO) throws RequestErrorException;


    public SlgData userRoleList(SlgRequestDTO requestDTO) throws RequestErrorException;


    public SlgData userRole(SlgRequestDTO requestDTO) throws RequestErrorException;

}
