package com.h13.slg.user.hepler;

import com.h13.slg.config.co.LevelCO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.ResourceCalUtil;
import com.h13.slg.core.util.TimeUtil;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.user.co.FarmCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.FarmDAO;
import com.h13.slg.user.vo.FarmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 农场资源
 */
@Service
public class FarmHelper {

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;
    @Autowired
    FarmDAO farmDAO;
    @Autowired
    UserEventHelper userEventHelper;


    /**
     * 收获资源
     *
     * @param uid
     */
    public FarmVO harvest(long uid) {
        FarmVO farmVO = new FarmVO();
        FarmCO farmCO = getFarmInfo(uid);
        long lastTimer = farmCO.getTimer();
        long currentTimer = TimeUtil.currentTimeStamp();

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int foodPerHour = level.getFoodPerHour();
        int maxFood = level.getFoodMax();
        int curFood = userStatusCO.getFood();
        int finalFood = ResourceCalUtil.calResource4Harvest(curFood, lastTimer, currentTimer, foodPerHour, maxFood);

        if (finalFood != curFood) {
            userStatusCO.setFood(finalFood);
            userStatusHelper.updateUserStatus(userStatusCO);
            updateFarmInfo(uid, currentTimer);
        }
        SlgLogger.info(SlgLoggerEntity.p("farm", "harvest", uid, "do harvest")
                .addParam("curFood", curFood)
                .addParam("finalFood", finalFood));
        farmVO.setTimer(currentTimer);
        farmVO.setFood(finalFood - curFood);
        return farmVO;
    }


    /**
     * 创建用户的农场数据
     *
     * @param uid
     */
    public void create(long uid) {
        farmDAO.add(uid, TimeUtil.currentTimeStamp());
        SlgLogger.info(SlgLoggerEntity.p("farm", "create", uid, "create farm"));
    }

    /**
     * 更新用户的农场数据
     *
     * @param uid
     * @param timer
     */
    public void updateFarmInfo(long uid, long timer) {
        farmDAO.update(uid, timer);
        SlgLogger.info(SlgLoggerEntity.p("farm", "update", uid, "update farm")
                .addParam("timer", timer));
    }

    /**
     * 获得用户的农场信息
     *
     * @param uid
     * @return
     */
    public FarmCO getFarmInfo(long uid) {
        FarmCO farmCO = farmDAO.get(uid);
        SlgLogger.info(SlgLoggerEntity.p("farm", "get", uid, "get farm")
                .addParam("farm", farmCO));
        return farmCO;
    }
}
