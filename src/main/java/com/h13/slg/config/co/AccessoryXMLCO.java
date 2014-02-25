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
@ObjectCreate(pattern = "accessoryList")
public class AccessoryXMLCO {


    private Map<String, AccessoryCO> map = new HashMap<String, AccessoryCO>();

    @SetNext
    public void addAccessory(AccessoryCO obj) {
        map.put(obj.getStrength() + "", obj);
    }

    public Map<String, AccessoryCO> getMap() {
        return map;
    }

    public void setMap(Map<String, AccessoryCO> map) {
        this.map = map;
    }
}
