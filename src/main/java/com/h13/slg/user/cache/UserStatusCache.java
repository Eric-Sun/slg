package com.h13.slg.user.cache;

import com.h13.slg.user.co.UserStatusCO;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserStatusCache {

    private static final String KEY = "slg:user:status:";

    @Resource(name = "userStatusTemplate")
    private RedisTemplate<String, UserStatusCO> userStatusTemplate;


    public UserStatusCO get(long uid) {
        return userStatusTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserStatusCO userStatusCO) {
        userStatusTemplate.opsForValue().set(KEY + userStatusCO.getId(), userStatusCO);
    }

}
