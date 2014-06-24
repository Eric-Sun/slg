package com.h13.slg.tavern.vo;

/**
 * 招贤馆中的武将的id和他的状态
 * DEFAULT=0 GOT=1
 */
public class TavernRoleVO {
    private long id;
    private int status;
    private String roleName;
    private int gold;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
