package com.h13.slg.business.cache;

import com.h13.slg.business.co.UserBusinessCO;
import com.h13.slg.chat.co.UserChatCO;
import com.h13.slg.equip.co.UserEquipCO;
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
public class UserBusinessCache {

    private static final String KEY = "slg:user:business:";

    @Resource(name = "userBusinessTemplate")
    private RedisTemplate<String, UserBusinessCO> userBusinessTemplate;



    public UserBusinessCO get(int uid) {
        return userBusinessTemplate.opsForValue().get(KEY + uid);
    }

    public void set(UserBusinessCO userBusinessCO) {
        userBusinessTemplate.opsForValue().set(KEY + userBusinessCO.getId(), userBusinessCO);
    }

}
