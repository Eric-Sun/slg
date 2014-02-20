package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
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
    LevelCache levelCache;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    FarmDAO farmDAO;
    @Autowired
    GlobalConfigFetcher globalConfigFetcher;

    public int addFood(long uid, int food) {

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        int userLevel = userStatusCO.getLevel();
        // 获得当前等级的最大粮食，金钱等信息
        LevelCO level = levelHelper.getLevelInfo(userLevel);
        int foodMax = level.getFoodMax();

        int foodCur = userStatusCO.getFood();

        int foodFinal = 0;
        if (foodCur + food >= foodMax) {
            foodFinal = foodMax;
        } else {
            foodFinal = foodCur + food;
        }
        updateFarmInfo(uid, foodFinal);
        return foodFinal;
    }

    public void create(long uid){
        farmDAO.add(uid,globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_FARM_FOOD));
    }

    public void updateFarmInfo(long uid, int food) {
        farmDAO.update(uid, food);
    }

    public FarmCO getFarmInfo(long uid){
        return farmDAO.get(uid);
    }

    public void harvest(long uid) {
        FarmCO farmCO = getFarmInfo(uid);
        int food = farmCO.getCurFood();
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        userStatusCO.setFood(userStatusCO.getFood() + food);
        updateFarmInfo(uid, 0);
    }
}
