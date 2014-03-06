package com.h13.slg.battle.co;

import com.alibaba.fastjson.JSON;

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
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public Map<String, Integer> getDataMap() {
        return (Map<String, Integer>)JSON.parseObject(data, Map.class);
    }

    public void setDataMap(Map<Integer, Long> data) {
        this.data = JSON.toJSONString(data);
    }


    public void setData(String data) {
        this.data = data;
    }
}
