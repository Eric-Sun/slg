package com.h13.slg.user.service;

import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.SlgResponseDTO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
@Service("CastleService")
public class CastleServiceImpl implements CastleService {

    @Autowired
    CastleHelper castleHelper;
    @Autowired
    UserStatusHelper userStatusHelper;

    @Override
    public SlgData harvest(SlgRequestDTO request) {
        long uid = request.getUid();

        // flush gold and farm
        userStatusHelper.flushUserStatus(uid);


        castleHelper.harvest(uid);
        return SlgData.getData();
    }
}
