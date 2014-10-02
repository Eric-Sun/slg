package com.h13.slg.equip.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

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
     * @throws RequestErrorException
     */
    public SlgData strengthen(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 升级
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData make(SlgRequestDTO request) throws RequestErrorException;


}
