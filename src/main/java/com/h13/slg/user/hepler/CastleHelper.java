package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
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
    LevelCache levelCache;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    CastleDAO castleDAO;

    @Autowired
    GlobalConfigFetcher globalConfigFetcher;

    public int addGold(long uid, int gold) {

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        int userLevel = userStatusCO.getLevel();
        // 获得当前等级的最大粮食，金钱等信息
        LevelCO level = levelHelper.getLevelInfo(userLevel);
        int goldMax = level.getGoldMax();

        int goldCur = userStatusCO.getGold();

        int goldFinal = 0;
        if (goldCur + gold >= goldMax) {
            goldFinal = goldMax;
        } else {
            goldFinal = goldCur + gold;
        }

        updateCastleInfo(uid, goldFinal);
        return goldFinal;
    }

    public void updateCastleInfo(long uid, int gold) {
        castleDAO.update(uid, gold);
    }

    public CastleCO getCastleInfo(long uid) {
        return castleDAO.get(uid);
    }

    public void create(long uid) {
        castleDAO.add(uid, globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_CASTLE_GOLD));
    }


    public void harvest(long uid) {
        CastleCO castleCO = getCastleInfo(uid);
        int gold = castleCO.getCurGold();
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        userStatusCO.setGold(userStatusCO.getGold() + gold);
        updateCastleInfo(uid, 0);
    }

}
