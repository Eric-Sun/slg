package com.h13.slg.battle.vo;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-23
 * Time: 下午5:57
 * To change this template use File | Settings | File Templates.
 */
public class UserTeamVO {
    private long id;
    private List<UserRoleInTeamVO> data = Lists.newArrayList();

    public List<UserRoleInTeamVO> getData() {
        return data;
    }

    public void setData(List<UserRoleInTeamVO> data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
