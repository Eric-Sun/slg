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
@ObjectCreate(pattern = "roleList")
public class RoleXMLCO {

    private Map<String, RoleCO> map = new HashMap<String, RoleCO>();

    @SetNext
    public void addRole(RoleCO roleCO) {
        map.put(roleCO.getId() + "", roleCO);
    }

    public Map<String, RoleCO> getMap() {
        return map;
    }

    public void setMap(Map<String, RoleCO> map) {
        this.map = map;
    }
}
