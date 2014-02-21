package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.core.util.ResourceCalUtil;
import com.h13.slg.core.util.TimeUtil;
import com.h13.slg.user.co.CastleCO;
import com.h13.slg.user.co.FarmCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.FarmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FarmHelper {

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    FarmDAO farmDAO;


    public void harvest(long uid) {
        FarmCO farmCO = getFarmInfo(uid);
        long lastTimer = farmCO.getTimer();
        long currentTimer = TimeUtil.currentTimeStamp();

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int foodPerHour = level.getFoodPerHour();
        int maxFood = level.getFoodMax();
        int curFood = userStatusCO.getFood();
        int finalFood = ResourceCalUtil.calResource(curFood, lastTimer, currentTimer, foodPerHour, maxFood);

        if (finalFood != 0) {
            userStatusCO.setFood(finalFood);
            userStatusHelper.updateUserStatus(userStatusCO);
            updateFarmInfo(uid, currentTimer);
        }
    }


    /**
     * 创建用户的农场数据
     *
     * @param uid
     */
    public void create(long uid) {
        farmDAO.add(uid, TimeUtil.currentTimeStamp());
    }

    /**
     * 更新用户的农场数据
     *
     * @param uid
     * @param timer
     */
    public void updateFarmInfo(long uid, long timer) {
        farmDAO.update(uid, timer);
    }

    /**
     * 获得用户的农场信息
     *
     * @param uid
     * @return
     */
    public FarmCO getFarmInfo(long uid) {
        return farmDAO.get(uid);
    }
}
