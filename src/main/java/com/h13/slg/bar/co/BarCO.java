package com.h13.slg.bar.co;

import java.util.List;

/**
 * 招贤馆
 */
public class BarCO {

    private long id; //招贤馆对应的uid
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
