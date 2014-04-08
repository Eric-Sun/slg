package com.h13.slg.user.service;

import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.hepler.FarmHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import com.h13.slg.user.vo.FarmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午6:08
 * To change this template use File | Settings | File Templates.
 */
@Service("FarmService")
public class FarmServiceImpl implements FarmService {

    @Autowired
    FarmHelper farmHelper;

    @Autowired
    UserStatusHelper userStatusHelper;

    @Override
    public SlgData harvest(SlgRequestDTO request) {
        long uid = request.getUid();

        FarmVO farmVO = farmHelper.harvest(uid);
        return SlgData.getData().add("food", farmVO.getFood()).add("timer", farmVO.getTimer());
    }

}
