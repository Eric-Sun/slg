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
@ObjectCreate(pattern = "weaponList")
public class WeaponXMLCO {


    private Map<String, WeaponCO> map = new HashMap<String, WeaponCO>();

    @SetNext
    public void addWeapon(WeaponCO obj) {
        map.put(obj.getStrength() + "", obj);
    }

    public Map<String, WeaponCO> getMap() {
        return map;
    }

    public void setMap(Map<String, WeaponCO> map) {
        this.map = map;
    }
}
