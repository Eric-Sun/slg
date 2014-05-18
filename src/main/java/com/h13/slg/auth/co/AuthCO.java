package com.h13.slg.auth.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-14
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class AuthCO {
    private long authTime;
    private String authKey;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public long getAuthTime() {
        return authTime;
    }

    public void setAuthTime(long authTime) {
        this.authTime = authTime;
    }
}
