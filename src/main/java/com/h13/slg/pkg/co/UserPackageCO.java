package com.h13.slg.pkg.co;

import com.alibaba.fastjson.JSON;

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

    public Map<String, Integer> getGem() {
        return (Map<String, Integer>) JSON.parseObject(gem, Map.class);
    }

    public void setGem(String gem) {
        this.gem = gem;
    }

    public Map<String, Integer> getMaterial() {
        return (Map<String, Integer>) JSON.parseObject(material, Map.class);
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

    public Map<String, Integer> getRoleCard() {
        return (Map<String, Integer>) JSON.parseObject(roleCard, Map.class);
    }

    public void setRoleCard(String roleCard) {
        this.roleCard = roleCard;
    }

    public Map<String, Integer> getEquip() {
        return (Map<String, Integer>) JSON.parseObject(equip, Map.class);
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

}
