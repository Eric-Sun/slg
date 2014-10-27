package com.h13.slg.skill.helper;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.co.ZuLingCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.config.fetcher.ZuLingConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.skill.cache.UserZuLingCache;
import com.h13.slg.skill.co.UserRoleSkillCO;
import com.h13.slg.skill.co.UserZuLingCO;
import com.h13.slg.skill.co.UserZuLingItemCO;
import com.h13.slg.skill.dao.UserZuLingDAO;
import com.h13.slg.skill.vo.RoleSkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-21
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserZuLingHelper {

    @Autowired
    UserZuLingDAO userZuLingDAO;
    @Autowired
    ZuLingConfigFetcher zuLingConfigFetcher;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;
    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    UserZuLingCache userZuLingCache;


    public UserZuLingCO get(long uid) {
        UserZuLingCO userZuLingCO = userZuLingCache.get(uid);
        if (userZuLingCO == null) {
            userZuLingCO = userZuLingDAO.get(uid);
            userZuLingCache.set(userZuLingCO);
            return userZuLingCO;
        } else {
            return userZuLingCO;
        }
    }

    /**
     * 判断zuling是否已经满了
     *
     * @param userZuLingCO
     * @return
     */
    public boolean isFull(UserZuLingCO userZuLingCO) {
        if (userZuLingCO.getList().size() == 0)
            return false;
        else
            return true;
    }

    public String randomQuality() {

        ZuLingCO zuLingCO = zuLingConfigFetcher.get("1");
        Random random = new Random();
        int result = random.nextInt(100);
        if (result < zuLingCO.getHuang()) {
            return SlgConstants.RoleSkillConstants.HUANG;
        } else if (result < zuLingCO.getHuang() + zuLingCO.getXuan()) {
            return SlgConstants.RoleSkillConstants.XUAN;
        } else if (result < zuLingCO.getHuang() + zuLingCO.getXuan() + zuLingCO.getDi()) {
            return SlgConstants.RoleSkillConstants.DI;
        } else {
            return SlgConstants.RoleSkillConstants.TIAN;
        }
    }


    public void update(UserZuLingCO userZuLingCO) {
        userZuLingDAO.update(userZuLingCO.getId(), userZuLingCO.getList());
        userZuLingCache.set(userZuLingCO);
    }
}
