package com.h13.slg.shop.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * "cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
public class ShopBuyVO {

    private String costType;
    private int costNum;
    private Map<String,Object> awards = new HashMap<String, Object>();

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

    public Map<String, Object> getAwards() {
        return awards;
    }

    public void setAwards(Map<String, Object> awards) {
        this.awards = awards;
    }
}
