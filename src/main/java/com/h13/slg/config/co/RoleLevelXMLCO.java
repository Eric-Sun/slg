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
@ObjectCreate(pattern = "rolelevelList")
public class RoleLevelXMLCO {

    private Map<String, RoleLevelCO> map = new HashMap<String, RoleLevelCO>();

    @SetNext
    public void addRoleLevel(RoleLevelCO co) {
        map.put(co.getLevel() + "", co);
    }

    public Map<String, RoleLevelCO> getMap() {
        return map;
    }

    public void setMap(Map<String, RoleLevelCO> map) {
        this.map = map;
    }
}
