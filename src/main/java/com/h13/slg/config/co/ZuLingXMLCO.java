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
@ObjectCreate(pattern = "zuLingList")
public class ZuLingXMLCO {


    private Map<String, ZuLingCO> map = new HashMap<String, ZuLingCO>();

    @SetNext
    public void addZuLing(ZuLingCO obj) {
        map.put(obj.getLevel() + "", obj);
    }

    public Map<String, ZuLingCO> getMap() {
        return map;
    }

    public void setMap(Map<String, ZuLingCO> map) {
        this.map = map;
    }
}
