package com.h13.slg.user.cache;

import com.h13.slg.user.co.UserStatusCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-24
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserStatusCache {

    @Resource(name = "userStatusTemplate")
    private RedisTemplate<String, UserStatusCO> userStatusTemplate;

    private static String KEY = "slg:user:";


    public UserStatusCO get(long userId) {
        UserStatusCO userStatusCO = userStatusTemplate.opsForValue().get(userId);
        return userStatusCO;
    }


    public void put(long userId, UserStatusCO userStatusCO) {
        userStatusTemplate.opsForValue().set(KEY + ":" + userId, userStatusCO);
    }


}
