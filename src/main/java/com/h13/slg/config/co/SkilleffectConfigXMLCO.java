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
@ObjectCreate(pattern = "skilleffectList")
public class SkilleffectConfigXMLCO {


    private Map<String, SkilleffectConfigCO> map = new HashMap<String, SkilleffectConfigCO>();

    @SetNext
    public void addSkilleffect(SkilleffectConfigCO obj) {
        map.put(obj.getSoldier() + "_" + obj.getSkill() + "_" + obj.getLevel(), obj);
    }

    public Map<String, SkilleffectConfigCO> getMap() {
        return map;
    }

    public void setMap(Map<String, SkilleffectConfigCO> map) {
        this.map = map;
    }
}
