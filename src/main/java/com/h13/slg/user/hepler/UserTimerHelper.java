package com.h13.slg.user.hepler;

import com.h13.slg.user.co.UserTimerCO;
import com.h13.slg.user.dao.UserTimerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-18
 * Time: 上午2:43
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserTimerHelper {

    @Autowired
    UserTimerDAO userTimerDAO;

    public void createTimer(long uid) {
        UserTimerCO userTimerCO = new UserTimerCO();
        userTimerCO.setId(uid);
        userTimerCO.setFood(System.currentTimeMillis());
        userTimerCO.setGold(System.currentTimeMillis());
        userTimerDAO.add(userTimerCO);
    }


    public void flushTimer(long uid){
        UserTimerCO userTimerCO = new UserTimerCO();
        userTimerCO.setId(uid);
        userTimerCO.setFood(System.currentTimeMillis());
        userTimerCO.setGold(System.currentTimeMillis());
        userTimerDAO.update(userTimerCO);

    }

}
