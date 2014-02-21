package com.h13.slg.user.hepler;

import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
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
    FarmHelper farmHelper;
    @Autowired
    CastleHelper castleHelper;


    /**
     * 获得用户状态数据
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
     * @param userStatusCO
     */
    public void updateUserStatus(UserStatusCO userStatusCO) {
        userStatusCache.set(userStatusCO);
        userStatusDAO.update(userStatusCO);
    }
}
