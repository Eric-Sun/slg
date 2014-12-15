package com.h13.slg.business.helper;

import com.h13.slg.business.cache.UserBusinessCache;
import com.h13.slg.business.co.UserBusinessCO;
import com.h13.slg.business.dao.UserBusinessDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-8
 * Time: 下午7:30
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserBusinessHelper {

    @Autowired
    UserBusinessDAO userBusinessDAO;

    @Autowired
    UserBusinessCache userBusinessCache;


    public UserBusinessCO get(int uid) {
        UserBusinessCO userBusinessCO = userBusinessCache.get(uid);
        if (userBusinessCO == null) {
            userBusinessCO = userBusinessDAO.get(uid);
            userBusinessCache.set(userBusinessCO);
        }
        return userBusinessCO;
    }


    public void update(UserBusinessCO userBusinessCO) {
        userBusinessDAO.update(userBusinessCO);
        userBusinessCache.set(userBusinessCO);
    }

}
