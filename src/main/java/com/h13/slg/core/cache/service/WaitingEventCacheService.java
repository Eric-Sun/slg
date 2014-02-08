package com.h13.slg.core.cache.service;

import com.h13.slg.core.cache.co.WaitingEventCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-23
 * Time: 下午6:46
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WaitingEventCacheService {

    @Resource(name = "waitingEventTemplate")
    private RedisTemplate<String, WaitingEventCO> waitingEventTemplate;

    private static String KEY = "slg:waitingevent";


    public List<WaitingEventCO> get(long userId) {
        List<WaitingEventCO> list = waitingEventTemplate.opsForList().range(KEY + "_" + userId
                , 0, waitingEventTemplate.opsForList().size(KEY));
        return list;
    }


    public void put(long userId, List<WaitingEventCO> list) {
        for (WaitingEventCO co : list) {
            waitingEventTemplate.opsForList().leftPush(KEY + "_" + userId, co);
        }
    }


}
