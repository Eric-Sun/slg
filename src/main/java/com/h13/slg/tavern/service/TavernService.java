package com.h13.slg.tavern.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @throws RequestErrorException
     */
    public SlgData leave(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 开始招贤
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData invite(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 雇佣
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData enroll(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 获取上次的酒馆内容
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData get(SlgRequestDTO request) throws RequestErrorException;
}
