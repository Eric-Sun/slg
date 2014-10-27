package com.h13.slg.tavern.helper;

import com.h13.slg.config.co.TavernConfigCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.config.fetcher.TavernConfigFetcher;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.tavern.cache.UserTavernCache;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import com.h13.slg.tavern.dao.TavernDAO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 招贤馆
 */
@Service
public class TavernHelper {

    private static List<TavernRoleCO> DEFAULT_ROLE_LIST = new ArrayList<TavernRoleCO>();

    @Autowired
    TavernDAO tavernDAO;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;
    @Autowired
    GlobalConfigFetcher globalConfigFetcher;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    TavernConfigFetcher tavernConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    UserTavernCache userTavernCache;

    /**
     * 创建一个人物的时候创建一个招贤馆对象
     *
     * @param uid
     */
    public TavernCO create(long uid) {
        TavernCO tavernCO = new TavernCO();
        tavernCO.setId(uid);
        tavernCO.setRoleList(DEFAULT_ROLE_LIST);
        tavernDAO.insert(tavernCO.getId(), tavernCO.getRoleList());
        userTavernCache.set(tavernCO);
        return tavernCO;
    }

    /**
     * 获取当前招贤馆的信息
     *
     * @param uid
     * @return
     */
    public TavernCO get(long uid) {
        TavernCO tavernCO = userTavernCache.get(uid);
        if (tavernCO == null) {
            tavernCO = tavernDAO.get(uid);
            userTavernCache.set(tavernCO);
            return tavernCO;
        } else {
            return tavernCO;
        }
    }


    /**
     * 更新tavern
     *
     * @param tavernCO
     */
    public void update(TavernCO tavernCO) {
        tavernDAO.update(tavernCO.getId(), tavernCO.getRoleList());
        userTavernCache.set(tavernCO);
    }

    /**
     * 检查tavern是否已经准备好了，如果其中已经有数据则为为准备好，如果没有数据则为准备好了
     *
     * @param uid
     * @return
     */
    public boolean checkTavernIsReady(int uid) {
        TavernCO co = get(uid);
        if (co.getRoleList().size() == 0)
            return true;
        else
            return false;
    }


    public int inviteGold(TavernConfigCO tavernConfigCO) {
        return tavernConfigCO.getGold();
    }


    public String randomQuality(TavernConfigCO tavernConfigCO) {
        int min = 1;
        int max = 100;
        Random random = new Random();
        int result = random.nextInt(max) % (max - min + 1) + min;

        if (result < tavernConfigCO.getOrange()) {
            return "orange";
        } else if (result < tavernConfigCO.getBlue() +
                tavernConfigCO.getOrange()) {
            return "blue";
        } else if (result < tavernConfigCO.getPurple() +
                tavernConfigCO.getBlue() +
                tavernConfigCO.getOrange()) {
            return "purple";
        } else {
            return "white";
        }
    }

    public int randomNextLevel(int curLevel, TavernConfigCO tavernConfigCO) {
        int rate = tavernConfigCO.getUpRate();
        int min = 1;
        int max = 100;
        Random random = new Random();
        int result = random.nextInt(max) % (max - min + 1) + min;
        if (result > rate) {
            return (curLevel - 1) == 0 ? 1 : curLevel - 1;
        } else {
            curLevel++;
            return curLevel;
        }
    }


    public int randomId(long max) {
        Random random = new Random();
        return random.nextInt(new Integer(max + ""));
    }



}

