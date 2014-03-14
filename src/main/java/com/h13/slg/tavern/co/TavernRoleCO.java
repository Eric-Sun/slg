package com.h13.slg.tavern.co;

/**
 * 招贤馆中的武将的id和他的状态
 * DEFAULT=0 GOT=1
 */
public class TavernRoleCO {
    private long id;
    private int status;

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
