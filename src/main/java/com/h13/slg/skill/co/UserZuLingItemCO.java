package com.h13.slg.skill.co;

import com.h13.slg.skill.RoleSkillConstants;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
public class UserZuLingItemCO {
    private int rsId;
    private int status = RoleSkillConstants.NOT_GOT;

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
