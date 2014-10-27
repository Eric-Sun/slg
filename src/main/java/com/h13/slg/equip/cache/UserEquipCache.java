package com.h13.slg.equip.cache;

import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.task.co.UserTaskCO;
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
public class UserEquipCache {

    private static final String KEY = "slg:user:equip:";

    @Resource(name = "userEquipTemplate")
    private RedisTemplate<String, UserEquipCO> userEquipTemplate;


    public UserEquipCO get(long uid) {
        return userEquipTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserEquipCO userEquipCO) {
        userEquipTemplate.opsForValue().set(KEY + userEquipCO.getId(), userEquipCO);
    }

}
