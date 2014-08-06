package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserStatusDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOG = LoggerFactory.getLogger(UserStatusHelper.class);

    @Autowired
    UserStatusCache userStatusCache;
    @Autowired
    UserStatusDAO userStatusDAO;

    @Autowired
    LevelHelper levelHelper;

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

        int curFood = userStatusCO.getFood();
        int finalFood = curFood + food;
        userStatusCO.setGold(finalFood);
        updateUserStatus(userStatusCO);
    }

    /**
     * 为用户增加gold
     *
     * @param uid
     * @param gold
     */
    public void addGold(long uid, int gold) {
        UserStatusCO userStatusCO = getUserStatus(uid);

        int curGold = userStatusCO.getGold();
        int finalGold = gold + curGold;
        userStatusCO.setGold(finalGold);
        updateUserStatus(userStatusCO);

        SlgLogger.info(SlgLoggerEntity.p("user", "addGold", uid, "")
                .addParam("gold", gold)
                .addParam("curGold", curGold)
                .addParam("finalGold", finalGold)

        );
    }

    /**
     * 添加荣誉值
     *
     * @param uid
     * @param honor
     */
    public void addHonor(int uid, int honor) {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int finalHonor = userStatusCO.getHonor() + honor;
        SlgLogger.info(SlgLoggerEntity.p("user", "addHonor", uid, "")
                .addParam("honor", honor)
                .addParam("curHonor", userStatusCO.getHonor())
                .addParam("finalHonor", finalHonor));
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
            throw new RequestErrorException(CodeConstants.User.DONT_HAVE_ENOUGH_FOOD, "");

        userStatusCO.setFood(curFood - food);
        updateUserStatus(userStatusCO);
    }


    /**
     * 减少金币
     *
     * @param uid
     * @param gold
     * @throws RequestErrorException
     */
    public void subtractGold(long uid, int gold) throws RequestErrorException {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curGold = userStatusCO.getGold();

        if (curGold < gold)
            throw new RequestErrorException(CodeConstants.User.DONT_HAVE_ENOUGH_GOLD, "");

        userStatusCO.setGold(curGold - gold);
        updateUserStatus(userStatusCO);
        SlgLogger.info(SlgLoggerEntity.p("user", "subtractGold", uid, "")
                .addParam("gold", gold)
                .addParam("curGold", curGold).addParam("finalGold", curGold - gold));

    }


    public void addXp(long uid, int xp) {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curLevel = userStatusCO.getLevel();
        int maxXP = levelHelper.getLevelInfo(userStatusCO.getLevel()).getXp();
        int curXp = userStatusCO.getXp();
        int finalXp = 0;
        if (maxXP > curXp + xp) {
            finalXp = curXp + xp;
            userStatusCO.setXp(finalXp);
        } else {
            finalXp = curXp + xp;
            curLevel++;
            userStatusCO.setXp(finalXp);
            userStatusCO.setLevel(curLevel);
        }
        updateUserStatus(userStatusCO);
        SlgLogger.info(SlgLoggerEntity.p("user", "addXp", uid, "")
                .addParam("xp", xp)
                .addParam("curXP", curXp)
                .addParam("finalXp", finalXp)
                .addParam("finalLevel", curLevel));
    }

    public void addCash(long uid, int cash) {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curCash = userStatusCO.getCash();
        int finalCash = curCash + cash;
        userStatusCO.setCash(finalCash);
        updateUserStatus(userStatusCO);
        SlgLogger.info(SlgLoggerEntity.p("user", "addCash", uid, "")
                .addParam("cash", cash)
                .addParam("curCash", curCash)
                .addParam("finalCash", finalCash));
    }


    public void addSoul(long uid, int soul) {
        UserStatusCO userStatusCO = getUserStatus(uid);
        int curSoul = userStatusCO.getSoul();
        int finalSoul = curSoul + soul;
        userStatusCO.setSoul(finalSoul);
        updateUserStatus(userStatusCO);
        SlgLogger.info(SlgLoggerEntity.p("user", "addSoul", uid, "")
                .addParam("curSoul", curSoul)
                .addParam("soul", soul).addParam("finalSoul", finalSoul));
    }

    public void updateFightForce(long uid, int oldFightForce, int finalFightForce) {
        UserStatusCO userStatusCO = getUserStatus(uid);
        userStatusCO.setFightForce(userStatusCO.getFightForce() - oldFightForce + finalFightForce);
        SlgLogger.info(SlgLoggerEntity.p("user", "updateFightForce", uid, "")
                .addParam("oldFightForce", oldFightForce)
                .addParam("finalFightForce", finalFightForce));
    }


    /**
     * 创建新用户的时候初始化用户信息
     *
     * @param userId
     * @param name
     * @return
     */
    public UserStatusCO initUserStatus(long userId, String name) {

        UserStatusCO userStatusCO = new UserStatusCO();
        userStatusCO.setName(name);
        userStatusCO.setCash(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_CASH));
        userStatusCO.setFood(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_FOOD));
        userStatusCO.setGold(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_GOLD));
        userStatusCO.setHonor(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_HORNOR));
        userStatusCO.setLevel(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_LEVEL));

        userStatusCO.setFood(500000);
        userStatusCO.setGold(50000000);
        userStatusCO.setHonor(500000);
        userStatusCO.setCash(50000);

        userStatusCO.setId(userId);
        userStatusCO.setXp(0);
        userStatusCO.setSoul(0);
        userStatusCO.setFightForce(0);
        return userStatusCO;
    }

}
