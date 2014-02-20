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
@ObjectCreate(pattern = "globalList")
public class GlobalXMLCO {


    private Map<String, GlobalCO> map = new HashMap<String, GlobalCO>();

    @SetNext
    public void addGlobal(GlobalCO obj) {
        map.put(obj.getKey() + "", obj);
    }

    public Map<String, GlobalCO> getMap() {
        return map;
    }

    public void setMap(Map<String, GlobalCO> map) {
        this.map = map;
    }
}
