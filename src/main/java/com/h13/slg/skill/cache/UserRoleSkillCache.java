package com.h13.slg.skill.cache;

import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.skill.co.UserRoleSkillCO;
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
public class UserRoleSkillCache {

    private static final String KEY = "slg:user:roleSkill:";

    @Resource(name = "userRoleSkillTemplate")
    private RedisTemplate<String, UserRoleSkillCO> userRoleSkillTemplate;


    public UserRoleSkillCO get(long id) {
        return userRoleSkillTemplate.opsForValue().get(KEY + id);
    }

    public void set(UserRoleSkillCO userRoleSkillCO) {
        userRoleSkillTemplate.opsForValue().set(KEY + userRoleSkillCO.getId(), userRoleSkillCO);
    }

}
