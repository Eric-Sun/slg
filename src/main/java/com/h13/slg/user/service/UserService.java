package com.h13.slg.user.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.SlgResponseDTO;

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
    public SlgData login(SlgRequestDTO request) throws RequestErrorException;

    /**
     * 注册
     *
     * @param request
     * @return
     */
    public SlgData register(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 登陆之后获得用户信息，用于显示
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData getInfo(SlgRequestDTO request) throws RequestErrorException;





}
