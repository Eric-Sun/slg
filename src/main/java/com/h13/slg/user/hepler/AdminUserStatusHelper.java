package com.h13.slg.user.hepler;

import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by sunbo on 14-11-13.
 */
@Service
public class AdminUserStatusHelper {

    @Autowired
    UserStatusDAO userStatusDAO;

    public UserStatusCO getByName(String name) {
        try {
            return userStatusDAO.getByName(name);
        } catch (DataAccessException e) {
            throw null;
        }
    }

}
