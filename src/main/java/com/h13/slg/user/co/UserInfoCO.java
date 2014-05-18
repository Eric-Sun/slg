package com.h13.slg.user.co;

import com.h13.slg.user.vo.CastleForLoginVO;
import com.h13.slg.user.vo.FarmForLoginVO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-13
 * Time: 下午10:02
 * To change this template use File | Settings | File Templates.
 */
public class UserInfoCO {
    private CastleForLoginVO castle;
    private FarmForLoginVO farm;
    private UserStatusCO userStatus;

    public CastleForLoginVO getCastle() {
        return castle;
    }

    public void setCastle(CastleForLoginVO castle) {
        this.castle = castle;
    }

    public FarmForLoginVO getFarm() {
        return farm;
    }

    public void setFarm(FarmForLoginVO farm) {
        this.farm = farm;
    }

    public UserStatusCO getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusCO userStatus) {
        this.userStatus = userStatus;
    }
}
