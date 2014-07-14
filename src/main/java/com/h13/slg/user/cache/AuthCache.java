package com.h13.slg.user.cache;

import com.h13.slg.user.co.AuthCO;
import com.h13.slg.web.SysConfig;
import com.h13.slg.web.SysConfigConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-14
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AuthCache {


    private static final String KEY = "slg:auth:";

    @Resource(name = "authTemplate")
    private RedisTemplate<String, AuthCO> authTemplate;


    public AuthCO get(long uid) {
        return authTemplate.opsForValue().get(KEY + uid);
    }

    public void set(long uid, AuthCO authCO) {
        long ms = new Long(SysConfig.get(SysConfigConstants.AUTH_CACHE_MS));
        authTemplate.opsForValue().set(KEY + uid, authCO, ms, TimeUnit.MILLISECONDS);
    }

}
