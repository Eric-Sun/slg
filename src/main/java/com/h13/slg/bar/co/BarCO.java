package com.h13.slg.bar.co;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public class BarCO {

    private long id;
    private List<BarRoleCO> roleList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<BarRoleCO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<BarRoleCO> roleList) {
        this.roleList = roleList;
    }
}
