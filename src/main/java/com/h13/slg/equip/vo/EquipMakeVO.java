package com.h13.slg.equip.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午2:12
 * To change this template use File | Settings | File Templates.
 */
public class EquipMakeVO {
    private int level;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
