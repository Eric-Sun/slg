package com.h13.slg.pkg.cache;

import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.pkg.co.UserPackageCO;
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
public class UserPackageCache {

    private static final String KEY = "slg:user:package:";

    @Resource(name = "userPackageTemplate")
    private RedisTemplate<String, UserPackageCO> userPackageTemplate;


    public UserPackageCO get(long uid) {
        return userPackageTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserPackageCO userPackageCO) {
        userPackageTemplate.opsForValue().set(KEY + userPackageCO.getId(), userPackageCO);
    }

}
