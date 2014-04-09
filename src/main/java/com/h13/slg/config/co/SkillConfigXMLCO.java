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
@ObjectCreate(pattern = "skillList")
public class SkillConfigXMLCO {


    private Map<String, SkillConfigCO> map = new HashMap<String, SkillConfigCO>();

    @SetNext
    public void addSkill(SkillConfigCO obj) {
        map.put(obj.getSoldier() + "_" + obj.getSkill() + "", obj);
    }

    public Map<String, SkillConfigCO> getMap() {
        return map;
    }

    public void setMap(Map<String, SkillConfigCO> map) {
        this.map = map;
    }
}
