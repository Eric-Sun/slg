package com.h13.slg.user.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

import java.lang.reflect.InvocationTargetException;

/**
 * 用户模块接口
 */
public interface UserService {

    String MOD = "user";

    /**
     * 登陆接口
     *
     * @return
     */
    public SlgData login(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;

    /**
     * 注册
     *
     * @param request
     * @return
     */
    public SlgData register(SlgRequestDTO request) throws RequestFatalException;


    /**
     * 登陆之后获得用户信息，用于显示
     * @param request
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public SlgData getInfo(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException, InvocationTargetException, IllegalAccessException;





}
