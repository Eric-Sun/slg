package com.h13.slg.tavern.vo;

import com.h13.slg.tavern.co.TavernRoleCO;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-12
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public class InviteTavernVO {
    private List<TavernRoleVO> data;

    public List<TavernRoleVO> getData() {
        return data;
    }

    public void setData(List<TavernRoleVO> data) {
        this.data = data;
    }
}
