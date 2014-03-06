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
@ObjectCreate(pattern = "strengthenList")
public class StrengthenXMLCO {


    private Map<String, StrengthenCO> map = new HashMap<String, StrengthenCO>();

    @SetNext
    public void addStrengthen(StrengthenCO obj) {
        map.put(obj.getStrength() + "", obj);
    }

    public Map<String, StrengthenCO> getMap() {
        return map;
    }

    public void setMap(Map<String, StrengthenCO> map) {
        this.map = map;
    }
}
