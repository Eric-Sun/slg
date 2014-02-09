package com.h13.slg.user.service;

import com.h13.slg.core.ResponseDTO;

/**
 * 用户模块接口
 */
public interface UserService {


    /**
     * 登陆接口
     * @return
     */
    public ResponseDTO login(long uid, int seq, String args);

}
