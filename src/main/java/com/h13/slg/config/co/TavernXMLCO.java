package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-13
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "tavernList")
public class TavernXMLCO {
    private Map<String, TavernConfigCO> map = new HashMap<String, TavernConfigCO>();

    @SetNext
    public void addTavern(TavernConfigCO co) {
        map.put(co.getLevel() + "", co);
    }

    public Map<String, TavernConfigCO> getMap() {
        return map;
    }

    public void setMap(Map<String, TavernConfigCO> map) {
        this.map = map;
    }
}
