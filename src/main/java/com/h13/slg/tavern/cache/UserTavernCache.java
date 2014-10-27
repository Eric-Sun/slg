package com.h13.slg.tavern.cache;

import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.tavern.co.TavernCO;
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
public class UserTavernCache {

    private static final String KEY = "slg:user:tavern:";

    @Resource(name = "userTavernTemplate")
    private RedisTemplate<String, TavernCO> userTavernTemplate;


    public TavernCO get(long uid) {
        return userTavernTemplate.opsForValue().get(KEY + uid);
    }

    public void set(TavernCO tavernCO) {
        userTavernTemplate.opsForValue().set(KEY + tavernCO.getId(), tavernCO);
    }

}
