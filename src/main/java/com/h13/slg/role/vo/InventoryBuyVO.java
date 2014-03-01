package com.h13.slg.role.vo;

import java.util.ArrayList;

/**
 * "cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class InventoryBuyVO {

    private String costType;
    private int costNum;
    private ArrayList<ArrayList<Object>> awards;

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public int getCostNum() {
        return costNum;
    }

    public void setCostNum(int costNum) {
        this.costNum = costNum;
    }

    public ArrayList<ArrayList<Object>> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<ArrayList<Object>> awards) {
        this.awards = awards;
    }
}
