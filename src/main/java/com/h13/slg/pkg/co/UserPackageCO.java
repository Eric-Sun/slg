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
    private long id;
    private String roleCard;
    private String equip;
    private String gem;
    private String material;

    public String getRoleCard() {
        return roleCard;
    }

    public String getEquip() {
        return equip;
    }

    public String getGem() {
        return gem;
    }

    public String getMaterial() {
        return material;
    }

    public Map<String, Integer> getGemMap() {
        return (Map<String, Integer>) JSON.parseObject(gem, Map.class);
    }

    public void setGemMap(Map<String, Integer> map) {
        this.gem = JSON.toJSONString(map);
    }

    public void setGem(String gem) {
        this.gem = gem;
    }

    public Map<String, Integer> getMaterialMap() {
        return (Map<String, Integer>) JSON.parseObject(material, Map.class);
    }

    public void setMaterialMap(Map<String, Integer> map) {
        this.material = JSON.toJSONString(map);
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, Integer> getRoleCardMap() {
        return (Map<String, Integer>) JSON.parseObject(roleCard, Map.class);
    }

    public void setRoleCardMap(Map<String, Integer> map) {
        this.roleCard = JSON.toJSONString(map);
    }

    public void setRoleCard(String roleCard) {
        this.roleCard = roleCard;
    }

    public Map<String, List<Long>> getEquipMap() {
        return (Map<String, List<Long>>) JSON.parseObject(equip, Map.class);
    }

    public void setEquipMap(Map<String, List<Long>> map) {
        this.equip = JSON.toJSONString(map);
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

}
