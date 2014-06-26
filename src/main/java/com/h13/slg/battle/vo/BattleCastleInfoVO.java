package com.h13.slg.battle.vo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-26
 * Time: 下午7:34
 * To change this template use File | Settings | File Templates.
 */
public class BattleCastleInfoVO {
    private String name;
    private List<BattleRoleInfoVO> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BattleRoleInfoVO> getList() {
        return list;
    }

    public void setList(List<BattleRoleInfoVO> list) {
        this.list = list;
    }
}
