package com.h13.slg.task.cache;

import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.co.UserTaskCO;
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
public class UserTaskCache {

    private static final String KEY = "slg:user:task:";

    @Resource(name = "userTaskTemplate")
    private RedisTemplate<String, UserTaskCO> userTaskTemplate;


    public UserTaskCO get(long uid) {
        return userTaskTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserTaskCO userTaskCO) {
        userTaskTemplate.opsForValue().set(KEY + userTaskCO.getId(), userTaskCO);
    }

}
