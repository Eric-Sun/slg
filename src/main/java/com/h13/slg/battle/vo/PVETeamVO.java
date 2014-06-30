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
public class PVETeamVO {
    private long id;
    private List<PVERoleInTeamVO> data = Lists.newArrayList();

    public List<PVERoleInTeamVO> getData() {
        return data;
    }

    public void setData(List<PVERoleInTeamVO> data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
