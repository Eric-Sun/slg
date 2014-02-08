package com.h13.slg.user.helper;

import com.h13.slg.core.exceptions.ObjectLoadErrorException;
import com.h13.slg.user.cache.UserLevelCache;
import com.h13.slg.user.co.UserLevelCO;
import com.h13.slg.user.dao.UserLevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserLevelHelper {

    @Autowired
    UserLevelCache userLevelCache;

    @Autowired
    UserLevelDAO userLevelDAO;


    public UserLevelCO get(int userLevelId) throws ObjectLoadErrorException {
        UserLevelCO userLevelCO = userLevelCache.get(userLevelId);
        if (userLevelCO == null)
            userLevelCO = userLevelDAO.get(userLevelId);

        if (userLevelCO == null)
            throw new ObjectLoadErrorException(" userLevelId=" + userLevelId);

        return userLevelCO;

    }

}
