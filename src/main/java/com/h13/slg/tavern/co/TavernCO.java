package com.h13.slg.tavern.co;

import com.h13.slg.tavern.vo.TavernRoleVO;

import java.util.List;

/**
 * 招贤馆
 */
public class TavernCO {

    private long id; //招贤馆对应的uid
    private List<TavernRoleCO> roleList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TavernRoleCO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<TavernRoleCO> roleList) {
        this.roleList = roleList;
    }
}
