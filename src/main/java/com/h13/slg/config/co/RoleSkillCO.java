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
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/arg1")
    private String arg1;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/arg2")
    private String arg2;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/arg3")
    private String arg3;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/action")
    private String action;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/materialNum2")
    private int materialNum2;
    @BeanPropertySetter(pattern = "roleSkillList/roleSkill/material")
    private int material;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
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
}
