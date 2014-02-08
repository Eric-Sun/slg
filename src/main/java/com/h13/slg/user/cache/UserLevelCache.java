package com.h13.slg.user.cache;

import com.h13.slg.user.co.UserLevelCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserLevelCache {

    @Resource(name = "userLevelTemplate")
    private RedisTemplate<String, UserLevelCO> userLevelTemplate;
    private static final String KEY = "slg:userLevel:";

    public void put(UserLevelCO userLevelCO) {
        userLevelTemplate.opsForValue().set(KEY + ":" + userLevelCO.getId(), userLevelCO);
    }

    public UserLevelCO get(int userLevelId) {
        return userLevelTemplate.opsForValue().get(KEY + ":" + userLevelId);
    }

}
