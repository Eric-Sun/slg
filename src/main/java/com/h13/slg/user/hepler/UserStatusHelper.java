package com.h13.slg.user.hepler;

import com.h13.slg.config.co.LevelCO;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.util.ResourceCalUtil;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.CastleDAO;
import com.h13.slg.user.dao.UserStatusDAO;
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
    CastleDAO castleDAO;
    @Autowired
    FarmHelper farmHelper;
    @Autowired
    CastleHelper castleHelper;


    /**
     * 获得用户状态数据
     *
     * @param uid
     * @return
     */
    public UserStatusCO getUserStatus(long uid) {
        UserStatusCO userStatusCO = userStatusCache.get(uid);
        if (userStatusCO == null) {
            userStatusCO = userStatusDAO.get(uid);
        }
        return userStatusCO;
    }

    /**
     * 更新用户状态信息
     *
     * @param userStatusCO
     */
    public void updateUserStatus(UserStatusCO userStatusCO) {
        userStatusCache.set(userStatusCO);
        userStatusDAO.update(userStatusCO);
    }

    /**
     * 为用户增加food
     *
     * @param uid
     * @param food
     */
    public void addFood(long uid, int food) {
        UserStatusCO userStatusCO = getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int maxGold = level.getGoldMax();
        int curGold = userStatusCO.getGold();
        int finalGold = ResourceCalUtil.calResource4SimpleAdd(curGold, food, maxGold);
        if (finalGold != 0) {
            userStatusCO.setGold(finalGold);
            updateUserStatus(userStatusCO);
        }
    }

    /**
     * 为用户增加gold
     *
     * @param uid
     * @param gold
     */
    public void addGold(long uid, int gold) {
        UserStatusCO userStatusCO = getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int maxGold = level.getGoldMax();
        int curGold = userStatusCO.getGold();
        int finalGold = ResourceCalUtil.calResource4SimpleAdd(curGold, gold, maxGold);
        if (finalGold != 0) {
            userStatusCO.setGold(finalGold);
            updateUserStatus(userStatusCO);
        }
    }

    /**
     * 减少food
     *
     * @param uid
     * @param food
     * @throws RequestErrorException
     */
    public void subtractFood(long uid, int food) throws RequestErrorException {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curFood = userStatusCO.getFood();

        if (curFood < food)
            throw new RequestErrorException(ErrorCodeConstants.User.DONT_HAVE_ENOUGH_FOOD, "");

        userStatusCO.setFood(curFood - food);
        updateUserStatus(userStatusCO);
    }


    /**
     * 减少金币
     * @param uid
     * @param gold
     * @throws RequestErrorException
     */
    public void subtractGold(long uid, int gold) throws RequestErrorException {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curGold = userStatusCO.getGold();

        if (curGold < gold)
            throw new RequestErrorException(ErrorCodeConstants.User.DONT_HAVE_ENOUGH_GOLD, "");

        userStatusCO.setGold(curGold - gold);
        updateUserStatus(userStatusCO);
    }
}
