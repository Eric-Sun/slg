package com.h13.slg.skill.cache;

import com.h13.slg.skill.co.UserRoleSkillCO;
import com.h13.slg.skill.co.UserZuLingCO;
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
public class UserZuLingCache {

    private static final String KEY = "slg:user:zuling:";

    @Resource(name = "userZuLingTemplate")
    private RedisTemplate<String, UserZuLingCO> userZuLingTemplate;


    public UserZuLingCO get(long id) {
        return userZuLingTemplate.opsForValue().get(KEY + id);
    }

    public void set(UserZuLingCO userZuLingCO) {
        userZuLingTemplate.opsForValue().set(KEY + userZuLingCO.getId(), userZuLingCO);
    }

}
