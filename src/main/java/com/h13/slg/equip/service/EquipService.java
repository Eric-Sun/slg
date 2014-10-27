package com.h13.slg.equip.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-24
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */
public interface EquipService {


    /**
     * 强化
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData strengthen(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;


    /**
     * 升级
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData make(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;


}
