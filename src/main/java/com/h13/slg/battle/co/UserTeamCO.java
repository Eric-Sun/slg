package com.h13.slg.battle.co;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:30
 * To change this template use File | Settings | File Templates.
 */
public class UserTeamCO {
    private long id;
    private List<Long> data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getData() {
        return data;
    }

    public void setData(List<Long> data) {
        this.data = data;
    }
}
