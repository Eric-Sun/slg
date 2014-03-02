package com.h13.slg.event.cache;

import com.h13.slg.event.co.UserEventCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserEventCache {

    private static final String KEY = "slg:user:events:";

    @Resource(name = "userEventTemplate")
    private RedisTemplate<String, UserEventCO> userEventTemplate;


    public UserEventCO get(long uid) {
        return userEventTemplate.opsForList().rightPop(KEY + uid);
    }

    public void set(UserEventCO userEventCO) {
        userEventTemplate.opsForList().leftPush(KEY + userEventCO.getUid(), userEventCO);
    }

}
