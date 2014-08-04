package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "roleSkillList/roleSkill")
public class RoleSkillCO {

    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/id")
    private int id;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/name")
    private String name;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/type")
    private String type;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/quality")
    private String quality;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/materialNum2")
    private int materialNum2;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/tips")
    private int tips;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/material")
    private int material;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runStart")
    private String runStart;        // 0为当场生效，1为下回合生效
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runTarget")
    private String runTarget; // 0为自己，1为给对方
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runType")
    private String runType;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runParamsAttack")
    private String runParamsAttack;        // 全都是百分比，如果是减少的话，设置为负数
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runParamsDefence")
    private String runParamsDefence;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runParamsHealth")
    private String runParamsHealth;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runParamsHuanghu")
    private String runParamsHuanghu;  // 0没有，1有
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runParamsDamage")
    private String runParamsDamage; // 伤害
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/runTime")
    private String runRound;  // 0全局，>0的为回合数


    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getMaterialNum2() {
        return materialNum2;
    }

    public void setMaterialNum2(int materialNum2) {
        this.materialNum2 = materialNum2;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public String getRunStart() {
        return runStart;
    }

    public void setRunStart(String runStart) {
        this.runStart = runStart;
    }

    public String getRunTarget() {
        return runTarget;
    }

    public void setRunTarget(String runTarget) {
        this.runTarget = runTarget;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getRunParamsAttack() {
        return runParamsAttack;
    }

    public void setRunParamsAttack(String runParamsAttack) {
        this.runParamsAttack = runParamsAttack;
    }

    public String getRunParamsDefence() {
        return runParamsDefence;
    }

    public void setRunParamsDefence(String runParamsDefence) {
        this.runParamsDefence = runParamsDefence;
    }

    public String getRunParamsHealth() {
        return runParamsHealth;
    }

    public void setRunParamsHealth(String runParamsHealth) {
        this.runParamsHealth = runParamsHealth;
    }

    public String getRunParamsHuanghu() {
        return runParamsHuanghu;
    }

    public void setRunParamsHuanghu(String runParamsHuanghu) {
        this.runParamsHuanghu = runParamsHuanghu;
    }

    public String getRunParamsDamage() {
        return runParamsDamage;
    }

    public void setRunParamsDamage(String runParamsDamage) {
        this.runParamsDamage = runParamsDamage;
    }

    public String getRunRound() {
        return runRound;
    }

    public void setRunRound(String runRound) {
        this.runRound = runRound;
    }
}
