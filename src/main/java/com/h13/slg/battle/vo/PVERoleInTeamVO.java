package com.h13.slg.battle.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-23
 * Time: 下午5:56
 * To change this template use File | Settings | File Templates.
 */
public class PVERoleInTeamVO {
    private int rid;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public static PVERoleInTeamVO EmptyObject() {
        PVERoleInTeamVO vo = new PVERoleInTeamVO();
        vo.setName("");
        vo.setRid(0);
        return vo;
    }
}
