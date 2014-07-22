package com.h13.slg.skill.co;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public class UserZuLingCO {
    private int id;
    private List<UserZuLingItemCO> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserZuLingItemCO> getList() {
        return list;
    }

    public void setList(List<UserZuLingItemCO> list) {
        this.list = list;
    }
}
