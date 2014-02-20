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
@ObjectCreate(pattern = "equipList")
public class EquipXMLCO {


    private Map<String, EquipCO> map = new HashMap<String, EquipCO>();

    @SetNext
    public void addEquip(EquipCO obj) {
        map.put(obj.getLevel() + "", obj);
    }

    public Map<String, EquipCO> getMap() {
        return map;
    }

    public void setMap(Map<String, EquipCO> map) {
        this.map = map;
    }
}
