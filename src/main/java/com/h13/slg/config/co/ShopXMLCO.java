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
@ObjectCreate(pattern = "shopList")
public class ShopXMLCO {


    private Map<String, ShopCO> map = new HashMap<String, ShopCO>();

    @SetNext
    public void addShop(ShopCO obj) {
        map.put(obj.getId() + "", obj);
    }

    public Map<String, ShopCO> getMap() {
        return map;
    }

    public void setMap(Map<String, ShopCO> map) {
        this.map = map;
    }
}
