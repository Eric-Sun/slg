package com.h13.slg.chat.cache;

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
public class UserChatCache {

    private static final String KEY = "slg:user:chat:";

    @Resource(name = "userChatTemplate")
    private RedisTemplate<String, UserChatCO> userChatTemplate;


    public List<UserChatCO> get(long uid) {
        long size = userChatTemplate.opsForList().size(KEY + uid);
        List<UserChatCO> list = userChatTemplate.opsForList().range(KEY + uid, 0, size);
        userChatTemplate.delete(KEY + uid);
        return list;
    }

    public void set(UserChatCO userChatCO) {
        userChatTemplate.opsForList().rightPush(KEY + userChatCO.getToId(), userChatCO);
    }

}
