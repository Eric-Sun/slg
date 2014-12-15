package com.h13.slg.fight;

import com.google.common.collect.Lists;
import com.h13.slg.fight.buff.Buff;

import java.util.List;

/**
 * 参战的user对象
 */
public class FUser {
    private int uid;


    private FUserRole leader;

    private List<FUserRole> userRoleList;

    private List<Buff> globalBuffList = Lists.newLinkedList();

    public FUserRole getLeader() {
        return leader;
    }

    public void setLeader(FUserRole leader) {
        this.leader = leader;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<FUserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<FUserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<Buff> getGlobalBuffList() {
        return globalBuffList;
    }

    public void setGlobalBuffList(List<Buff> globalBuffList) {
        this.globalBuffList = globalBuffList;
    }
}
