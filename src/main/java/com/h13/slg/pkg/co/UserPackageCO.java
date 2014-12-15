package com.h13.slg.pkg.co;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public class UserPackageCO {
    private int id;
    // 用户装备的id
    private List<Integer> equip;
    // 材料的id--》材料的数量
    private Map<String, Integer> material;

    private Map<String,Integer> skill;

    private Map<String,Integer> gem;

    public Map<String, Integer> getGem() {
        return gem;
    }

    public void setGem(Map<String, Integer> gem) {
        this.gem = gem;
    }

    public Map<String, Integer> getSkill() {
        return skill;
    }

    public void setSkill(Map<String, Integer> skill) {
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getEquip() {
        return equip;
    }

    public void setEquip(List<Integer> equip) {
        this.equip = equip;
    }

    public Map<String, Integer> getMaterial() {
        return material;
    }

    public void setMaterial(Map<String, Integer> material) {
        this.material = material;
    }
}
