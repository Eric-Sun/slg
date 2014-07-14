package com.h13.slg.user.hepler;

import com.h13.slg.user.cache.AuthCache;
import com.h13.slg.user.co.AuthCO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-14
 * Time: 下午6:25
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AuthHelper {

    @Autowired
    AuthCache authCache;


    /**
     * 登陆的时候调用
     *
     * @param uid
     * @return
     */
    public AuthCO createAuth(long uid) {
        AuthCO authCO = new AuthCO();
        String authKey = MD5Util.getMD5String(uid + "");
        long authTime = System.currentTimeMillis();
        authCO.setAuthKey(authKey);
        authCO.setAuthTime(authTime);
        authCache.set(uid, authCO);
        return authCO;
    }


    /**
     * 每次操作的时候需要验证，如果失败的话，需要重新登陆
     *
     * @param uid
     * @param authKey
     * @param authTime
     * @return
     */
    public boolean check(long uid, String authKey, long authTime) {
        AuthCO authCO = authCache.get(uid);
        if (authCO == null) {
            SlgLogger.info(SlgLoggerEntity.p("auth", "check", uid, "check fail")
                    .addParam("authKey", authKey)
                    .addParam("authTime", authTime)
            );
            return false;
        }

        if (authCO.getAuthKey().equals(authKey)
                && authCO.getAuthTime() == authTime) {

            return true;
        }
        SlgLogger.info(SlgLoggerEntity.p("auth", "check", uid, "check fail")
                .addParam("authKey", authKey)
                .addParam("authTime", authTime)
        );
        return false;
    }
}
