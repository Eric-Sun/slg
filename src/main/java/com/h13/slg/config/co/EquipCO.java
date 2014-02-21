package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-20
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "equipList/role")
public class EquipCO {
    @BeanPropertySetter(pattern = "equipList/role/level")
    private int level;
    @BeanPropertySetter(pattern = "equipList/role/weaponMaterial1")
    private int weaponMaterial1;
    @BeanPropertySetter(pattern = "equipList/role/weaponMaterialType1")
    private int weaponMaterialType1;
    @BeanPropertySetter(pattern = "equipList/role/weaponMaterial2")
    private int weaponMaterial2;
    @BeanPropertySetter(pattern = "equipList/role/weaponMaterialType2")
    private int weaponMaterialType2;
    @BeanPropertySetter(pattern = "equipList/role/weaponGold")
    private int weaponGold;
    @BeanPropertySetter(pattern = "equipList/role/weaponSuccess")
    private int weaponSuccess;
    @BeanPropertySetter(pattern = "equipList/role/armorMaterial1")
    private int armorMaterial1;
    @BeanPropertySetter(pattern = "equipList/role/armorMaterialType1")
    private int armorMaterialType1;
    @BeanPropertySetter(pattern = "equipList/role/armorMaterial2")
    private int armorMaterial2;
    @BeanPropertySetter(pattern = "equipList/role/armorMaterialType2")
    private int armorMaterialType2;
    @BeanPropertySetter(pattern = "equipList/role/armorGold")
    private int armorGold;
    @BeanPropertySetter(pattern = "equipList/role/armorSuccess")
    private int armorSuccess;
    @BeanPropertySetter(pattern = "equipList/role/accessoryMaterial1")
    private int accessoryMaterial1;
    @BeanPropertySetter(pattern = "equipList/role/accessoryMaterialType1")
    private int accessoryMaterialType1;
    @BeanPropertySetter(pattern = "equipList/role/accessoryMaterial2")
    private int accessoryMaterial2;
    @BeanPropertySetter(pattern = "equipList/role/accessoryMaterialType2")
    private int accessoryMaterialType2;
    @BeanPropertySetter(pattern = "equipList/role/accessoryGold")
    private int accessoryGold;
    @BeanPropertySetter(pattern = "equipList/role/accessorySuccess")
    private int accessorySuccess;
    @BeanPropertySetter(pattern = "equipList/role/weaponUrl")
    private String weaponUrl;
    @BeanPropertySetter(pattern = "equipList/role/weaponName")
    private String weaponName;
    @BeanPropertySetter(pattern = "equipList/role/weaponDesc")
    private String weaponDesc;
    @BeanPropertySetter(pattern = "equipList/role/magicUrl")
    private String magicUrl;
    @BeanPropertySetter(pattern = "equipList/role/magicName")
    private String magicName;
    @BeanPropertySetter(pattern = "equipList/role/magicDesc")
    private String magicDesc;
    @BeanPropertySetter(pattern = "equipList/role/armorUrl")
    private String armorUrl;
    @BeanPropertySetter(pattern = "equipList/role/armorName")
    private String armorName;
    @BeanPropertySetter(pattern = "equipList/role/armorDesc")
    private String armorDesc;
    @BeanPropertySetter(pattern = "equipList/role/accessoryUrl")
    private String accessoryUrl;
    @BeanPropertySetter(pattern = "equipList/role/accessoryName")
    private String accessoryName;
    @BeanPropertySetter(pattern = "equipList/role/accessoryDesc")
    private String accessoryDesc;
    @BeanPropertySetter(pattern = "equipList/role/sell")
    private int sell;
    @BeanPropertySetter(pattern = "equipList/role/buyable")
    private int buyable;
    @BeanPropertySetter(pattern = "equipList/role/equipAddSuccByFail")
    private int equipAddSuccByFail;
    @BeanPropertySetter(pattern = "equipList/role/attributeLimit")
    private int attributeLimit;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getWeaponMaterial1() {
        return weaponMaterial1;
    }

    public void setWeaponMaterial1(int weaponMaterial1) {
        this.weaponMaterial1 = weaponMaterial1;
    }

    public int getWeaponMaterialType1() {
        return weaponMaterialType1;
    }

    public void setWeaponMaterialType1(int weaponMaterialType1) {
        this.weaponMaterialType1 = weaponMaterialType1;
    }

    public int getWeaponMaterial2() {
        return weaponMaterial2;
    }

    public void setWeaponMaterial2(int weaponMaterial2) {
        this.weaponMaterial2 = weaponMaterial2;
    }

    public int getWeaponMaterialType2() {
        return weaponMaterialType2;
    }

    public void setWeaponMaterialType2(int weaponMaterialType2) {
        this.weaponMaterialType2 = weaponMaterialType2;
    }

    public int getWeaponGold() {
        return weaponGold;
    }

    public void setWeaponGold(int weaponGold) {
        this.weaponGold = weaponGold;
    }

    public int getWeaponSuccess() {
        return weaponSuccess;
    }

    public void setWeaponSuccess(int weaponSuccess) {
        this.weaponSuccess = weaponSuccess;
    }

    public int getArmorMaterial1() {
        return armorMaterial1;
    }

    public void setArmorMaterial1(int armorMaterial1) {
        this.armorMaterial1 = armorMaterial1;
    }

    public int getArmorMaterialType1() {
        return armorMaterialType1;
    }

    public void setArmorMaterialType1(int armorMaterialType1) {
        this.armorMaterialType1 = armorMaterialType1;
    }

    public int getArmorMaterial2() {
        return armorMaterial2;
    }

    public void setArmorMaterial2(int armorMaterial2) {
        this.armorMaterial2 = armorMaterial2;
    }

    public int getArmorMaterialType2() {
        return armorMaterialType2;
    }

    public void setArmorMaterialType2(int armorMaterialType2) {
        this.armorMaterialType2 = armorMaterialType2;
    }

    public int getArmorGold() {
        return armorGold;
    }

    public void setArmorGold(int armorGold) {
        this.armorGold = armorGold;
    }

    public int getArmorSuccess() {
        return armorSuccess;
    }

    public void setArmorSuccess(int armorSuccess) {
        this.armorSuccess = armorSuccess;
    }

    public int getAccessoryMaterial1() {
        return accessoryMaterial1;
    }

    public void setAccessoryMaterial1(int accessoryMaterial1) {
        this.accessoryMaterial1 = accessoryMaterial1;
    }

    public int getAccessoryMaterialType1() {
        return accessoryMaterialType1;
    }

    public void setAccessoryMaterialType1(int accessoryMaterialType1) {
        this.accessoryMaterialType1 = accessoryMaterialType1;
    }

    public int getAccessoryMaterial2() {
        return accessoryMaterial2;
    }

    public void setAccessoryMaterial2(int accessoryMaterial2) {
        this.accessoryMaterial2 = accessoryMaterial2;
    }

    public int getAccessoryMaterialType2() {
        return accessoryMaterialType2;
    }

    public void setAccessoryMaterialType2(int accessoryMaterialType2) {
        this.accessoryMaterialType2 = accessoryMaterialType2;
    }

    public int getAccessoryGold() {
        return accessoryGold;
    }

    public void setAccessoryGold(int accessoryGold) {
        this.accessoryGold = accessoryGold;
    }

    public int getAccessorySuccess() {
        return accessorySuccess;
    }

    public void setAccessorySuccess(int accessorySuccess) {
        this.accessorySuccess = accessorySuccess;
    }

    public String getWeaponUrl() {
        return weaponUrl;
    }

    public void setWeaponUrl(String weaponUrl) {
        this.weaponUrl = weaponUrl;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponDesc() {
        return weaponDesc;
    }

    public void setWeaponDesc(String weaponDesc) {
        this.weaponDesc = weaponDesc;
    }

    public String getMagicUrl() {
        return magicUrl;
    }

    public void setMagicUrl(String magicUrl) {
        this.magicUrl = magicUrl;
    }

    public String getMagicName() {
        return magicName;
    }

    public void setMagicName(String magicName) {
        this.magicName = magicName;
    }

    public String getMagicDesc() {
        return magicDesc;
    }

    public void setMagicDesc(String magicDesc) {
        this.magicDesc = magicDesc;
    }

    public String getArmorUrl() {
        return armorUrl;
    }

    public void setArmorUrl(String armorUrl) {
        this.armorUrl = armorUrl;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public String getArmorDesc() {
        return armorDesc;
    }

    public void setArmorDesc(String armorDesc) {
        this.armorDesc = armorDesc;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryDesc() {
        return accessoryDesc;
    }

    public void setAccessoryDesc(String accessoryDesc) {
        this.accessoryDesc = accessoryDesc;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public int getBuyable() {
        return buyable;
    }

    public void setBuyable(int buyable) {
        this.buyable = buyable;
    }

    public int getEquipAddSuccByFail() {
        return equipAddSuccByFail;
    }

    public void setEquipAddSuccByFail(int equipAddSuccByFail) {
        this.equipAddSuccByFail = equipAddSuccByFail;
    }

    public int getAttributeLimit() {
        return attributeLimit;
    }

    public void setAttributeLimit(int attributeLimit) {
        this.attributeLimit = attributeLimit;
    }
}
