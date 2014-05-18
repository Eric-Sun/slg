package com.h13.slg.user.hepler;

import com.h13.slg.user.co.UserInfoCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.vo.CastleForLoginVO;
import com.h13.slg.user.vo.FarmForLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-13
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserHelper {

    @Autowired
    FarmHelper farmHelper;
    @Autowired
    CastleHelper castleHelper;
    @Autowired
    UserStatusHelper userStatusHelper;


    public UserInfoCO getInfo(long userId){
        // 获得farm,castle 的上一次收获的时间
        long farmTimer = farmHelper.getFarmInfo(userId).getTimer();
        long castleTimer = castleHelper.getCastleInfo(userId).getTimer();
        FarmForLoginVO farmVO = new FarmForLoginVO(farmTimer);
        CastleForLoginVO castleVO = new CastleForLoginVO(castleTimer);

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(userId);
        UserInfoCO userInfoCO = new UserInfoCO();
        userInfoCO.setUserStatus(userStatusCO);
        userInfoCO.setFarm(farmVO);
        userInfoCO.setCastle(castleVO);
        return userInfoCO;
    }

}
