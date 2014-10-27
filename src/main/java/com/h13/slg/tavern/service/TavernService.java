package com.h13.slg.tavern.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-11
 * Time: 上午12:07
 * To change this template use File | Settings | File Templates.
 */
public interface    TavernService {

    /**
     * 送走招来的贤者
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData leave(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;


    /**
     * 开始招贤
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData invite(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;


    /**
     * 雇佣
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData enroll(SlgRequestDTO request) throws RequestFatalException;


}
