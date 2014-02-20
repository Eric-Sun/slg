package com.h13.slg.user.hepler;

import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.co.UserTimerCO;
import com.h13.slg.user.dao.UserStatusDAO;
import com.h13.slg.user.dao.UserTimerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:52
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserStatusHelper {

    @Autowired
    UserStatusCache userStatusCache;
    @Autowired
    UserStatusDAO userStatusDAO;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    UserTimerDAO userTimerDAO;


    @Autowired
    FarmHelper farmHelper;
    @Autowired
    CastleHelper castleHelper;


    @Autowired
    UserTimerHelper userTimerHelper;

    public void flushUserStatus(long uid) {
        long currentSystemTime = System.currentTimeMillis();

        UserTimerCO userTimerCO = userTimerDAO.get(uid);
        UserStatusCO userStatusCO = getUserStatus(uid);
        long lastFoodTimer = userTimerCO.getFood();
        long lastGoldTimer = userTimerCO.getGold();

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int foodPerHour = level.getFoodPerHour();
        int goldPerHour = level.getGoldPerHour();

        int foodAdd = calFoodOrGold(currentSystemTime, lastFoodTimer, foodPerHour);
        int goldAdd = calFoodOrGold(currentSystemTime, lastGoldTimer, goldPerHour);

        int foodFinal = farmHelper.addFood(uid, foodAdd);
        int goldFinal = castleHelper.addGold(uid, goldAdd);
        userStatusCO.setGold(goldFinal);
        userStatusCO.setFood(foodFinal);
        updateUserStatus(userStatusCO);

        userTimerHelper.flushTimer(uid);
    }


    private int calFoodOrGold(long current, long last, int perHour) {
        return new Long((current - last) / (1000 * 60 * 60) * perHour).intValue();
    }


    public UserStatusCO getUserStatus(long uid) {
        UserStatusCO userStatusCO = userStatusCache.get(uid);
        if (userStatusCO == null) {
            userStatusCO = userStatusDAO.get(uid);
        }
        return userStatusCO;
    }

    public void updateUserStatus(UserStatusCO userStatusCO) {
        userStatusCache.set(userStatusCO);
        userStatusDAO.update(userStatusCO);
    }
}
