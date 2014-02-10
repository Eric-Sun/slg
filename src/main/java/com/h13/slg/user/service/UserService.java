package com.h13.slg.user.service;

import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.SlgResponseDTO;

/**
 * 用户模块接口
 */
public interface UserService {


    /**
     * 登陆接口
     * @return
     */
    public SlgResponseDTO login(SlgRequestDTO request);

}
