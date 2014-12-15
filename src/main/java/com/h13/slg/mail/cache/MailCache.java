package com.h13.slg.mail.cache;

import com.h13.slg.mail.co.MailCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 未读 mail cache
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MailCache {

    private static final String KEY = "slg:user:mailIdList:";

    @Resource(name = "mailTemplate")
    private RedisTemplate<String, MailCO> mailTemplate;


    public void push(int uid, MailCO mailCO, int maxSize) {
        long currentSize = mailTemplate.opsForList().size(KEY + uid);
        if (currentSize >= maxSize) {
            mailTemplate.opsForList().rightPop(KEY + uid);
            mailTemplate.opsForList().leftPush(KEY + uid, mailCO);
        } else {
            mailTemplate.opsForList().leftPush(KEY + uid, mailCO);
        }
    }

    public List<MailCO> getAll(int uid) {
        long currentSize = mailTemplate.opsForList().size(KEY + uid);
        return mailTemplate.opsForList().range(KEY + uid, 0, currentSize);
    }

    public MailCO get(int uid, int index) {
        return mailTemplate.opsForList().index(KEY + uid, index);
    }


    public boolean hasCache(int uid) {
        return mailTemplate.hasKey(KEY + uid);
    }

    public void update(int uid, int index, MailCO mailCO) {
        mailTemplate.opsForList().set(KEY + uid, index, mailCO);
    }

}
