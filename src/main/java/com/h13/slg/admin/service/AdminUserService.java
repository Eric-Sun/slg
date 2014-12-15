package com.h13.slg.admin.service;

import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.AdminUserStatusHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunbo on 14-11-13.
 */
@Service("AdminUserService")
public class AdminUserService {


    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    AdminUserStatusHelper adminUserStatusHelper;

    public SlgData getUserInfo(SlgRequestDTO requestDTO) {

        int uid = requestDTO.getUid();
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        return SlgData.getData().add("userStatus", userStatusCO);

    }

    public SlgData getUserInfoByName(SlgRequestDTO requestDTO){
        String name = requestDTO.getArgs().get("name").toString();
        UserStatusCO userStatusCO = adminUserStatusHelper.getByName(name);
        return SlgData.getData().add("userStatus", userStatusCO);


    }

}
