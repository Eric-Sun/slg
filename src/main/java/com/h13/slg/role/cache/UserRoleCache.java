package com.h13.slg.role.cache;

import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.role.co.UserRoleCO;
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
public class UserRoleCache {

    private static final String KEY = "slg:user:role:";

    @Resource(name = "userRoleTemplate")
    private RedisTemplate<String, UserRoleCO> userRoleTemplate;


    public UserRoleCO get(long uid) {
        return userRoleTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserRoleCO userRoleCO) {
        userRoleTemplate.opsForValue().set(KEY + userRoleCO.getId(), userRoleCO);
    }

}
