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
@ObjectCreate(pattern = "battleList")
public class BattleXMLCO {


    private Map<String, BattleCO> map = new HashMap<String, BattleCO>();

    @SetNext
    public void addBattle(BattleCO obj) {
        map.put(obj.getId() + "", obj);
    }

    public Map<String, BattleCO> getMap() {
        return map;
    }

    public void setMap(Map<String, BattleCO> map) {
        this.map = map;
    }
}
