package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.core.util.ResourceCalUtil;
import com.h13.slg.core.util.TimeUtil;
import com.h13.slg.user.co.CastleCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.CastleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CastleHelper {

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    CastleDAO castleDAO;

    public void harvest(long uid) {
        CastleCO castleCO = getCastleInfo(uid);
        long lastTimer = castleCO.getTimer();
        long currentTimer = TimeUtil.currentTimeStamp();

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int goldPerHour = level.getGoldPerHour();
        int maxGold = level.getGoldMax();
        int curGold = userStatusCO.getGold();
        int finalGold = ResourceCalUtil.calResource(curGold, lastTimer, currentTimer, goldPerHour, maxGold);
        if (finalGold != 0) {
            userStatusCO.setGold(finalGold);
            userStatusHelper.updateUserStatus(userStatusCO);
            updateCastleInfo(uid, currentTimer);
        }
    }


    /**
     * 获得关于城堡的信息
     *
     * @param uid
     * @return
     */
    public CastleCO getCastleInfo(long uid) {
        return castleDAO.get(uid);
    }

    /**
     * 创建user的城堡数据
     *
     * @param uid
     */
    public void create(long uid) {
        castleDAO.add(uid, TimeUtil.currentTimeStamp());
    }

    /**
     * 更新用户的城堡数据
     *
     * @param uid
     * @param timer
     */
    public void updateCastleInfo(long uid, long timer) {
        castleDAO.update(uid, timer);
    }

}
