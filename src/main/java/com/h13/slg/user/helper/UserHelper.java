package com.h13.slg.user.helper;

import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserStatusDAO;
import com.h13.slg.user.exception.UserNamePwdWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserHelper {

    @Autowired
    UserStatusDAO userDAO;
    @Autowired
    UserStatusCache userCache;

    public UserStatusCO login(String loginName,String pwd) throws UserNamePwdWrongException {
        UserStatusCO userStatusCO = userDAO.login(loginName, pwd);
        if (userStatusCO == null) {
            throw new UserNamePwdWrongException("loginName=" + loginName + " pwd= " + pwd);
        }
        return userStatusCO;
    }
}
