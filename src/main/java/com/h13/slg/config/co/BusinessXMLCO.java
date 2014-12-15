package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "businessList")
public class BusinessXMLCO {


    private Map<String, BusinessCO> map = new HashMap<String, BusinessCO>();

    @SetNext
    public void addBusiness(BusinessCO obj) {
        map.put(obj.getId() + "", obj);
    }

    public Map<String, BusinessCO> getMap() {
        return map;
    }

    public void setMap(Map<String, BusinessCO> map) {
        this.map = map;
    }
}
