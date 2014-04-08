package com.h13.slg.user.hepler;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.ResourceCalUtil;
import com.h13.slg.core.util.TimeUtil;
import com.h13.slg.user.co.CastleCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.CastleDAO;
import com.h13.slg.user.vo.CastleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城堡
 */
@Service
public class CastleHelper {

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    LevelHelper levelHelper;

    @Autowired
    CastleDAO castleDAO;

    /**
     * 收获城堡中的金币
     *
     * @param uid
     */
    public CastleVO harvest(long uid) {
        CastleVO castleVO = new CastleVO();
        CastleCO castleCO = getCastleInfo(uid);
        long lastTimer = castleCO.getTimer();
        long currentTimer = TimeUtil.currentTimeStamp();

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);

        LevelCO level = levelHelper.getLevelInfo(userStatusCO.getLevel());
        int goldPerHour = level.getGoldPerHour();
        int maxGold = level.getGoldMax();
        int curGold = userStatusCO.getGold();
        // 计算能收获的最大值
        int finalGold = ResourceCalUtil.calResource4Harvest(curGold, lastTimer, currentTimer, goldPerHour, maxGold);
        if (finalGold != curGold) {
            userStatusCO.setGold(finalGold);
            userStatusHelper.updateUserStatus(userStatusCO);
            updateCastleInfo(uid, currentTimer);
        }
        SlgLogger.info(SlgLoggerEntity.p("castle", "harvest", uid, "").addParam("finalGold", finalGold)
                .addParam("curGold", curGold));
        castleVO.setGold(finalGold - curGold);
        castleVO.setTimer(currentTimer);
        return castleVO;
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
        SlgLogger.info(SlgLoggerEntity.p("castle", "create", uid, "create a new castle."));
    }

    /**
     * 更新用户的城堡数据
     *
     * @param uid
     * @param timer
     */
    public void updateCastleInfo(long uid, long timer) {
        castleDAO.update(uid, timer);
        SlgLogger.info(SlgLoggerEntity.p("castle", "update", uid, "update castle.").addParam("timer", timer));
    }

}
