package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-25
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "strengthenList/strengthen")
public class StrengthenCO {

    @BeanPropertySetter(pattern = "strengthenList/strengthen/strength")
    private int strength;
    @BeanPropertySetter(pattern = "strengthenList/strengthen/level")
    private int level;
    @BeanPropertySetter(pattern = "strengthenList/strengthen/weaponCost")
    private int weaponCost;
    @BeanPropertySetter(pattern = "strengthenList/strengthen/armorCost")
    private int armorCost;
    @BeanPropertySetter(pattern = "strengthenList`/strengthen/accessoryCost")
    private int AccessoryCost;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getWeaponCost() {
        return weaponCost;
    }

    public void setWeaponCost(int weaponCost) {
        this.weaponCost = weaponCost;
    }

    public int getArmorCost() {
        return armorCost;
    }

    public void setArmorCost(int armorCost) {
        this.armorCost = armorCost;
    }

    public int getAccessoryCost() {
        return AccessoryCost;
    }

    public void setAccessoryCost(int accessoryCost) {
        AccessoryCost = accessoryCost;
    }
}
