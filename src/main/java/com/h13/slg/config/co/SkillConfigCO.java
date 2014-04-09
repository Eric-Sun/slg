package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-25
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "skillList/skill")
public class SkillConfigCO {
    @BeanPropertySetter(pattern = "skillList/skill/soldier")
    private int soldier;
    @BeanPropertySetter(pattern = "skillList/skill/skill")
    private int skill;
    @BeanPropertySetter(pattern = "skillList/skill/fightForceFactor")
    private int fightForceFactor;
    @BeanPropertySetter(pattern = "skillList/skill/roleLevel")
    private int roleLevel;
    @BeanPropertySetter(pattern = "skillList/skill/material")
    private int material;
    @BeanPropertySetter(pattern = "skillList/skill/skillUrl")
    private String skillUrl;
    @BeanPropertySetter(pattern = "skillList/skill/skillName")
    private String skillName;
    @BeanPropertySetter(pattern = "skillList/skill/replaceSkill")
    private int replaceSkill;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum2")
    private int materialNum2;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum3")
    private int materialNum3;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum4")
    private int materialNum4;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum5")
    private int materialNum5;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum6")
    private int materialNum6;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum7")
    private int materialNum7;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum8")
    private int materialNum8;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum9")
    private int materialNum9;
    @BeanPropertySetter(pattern = "skillList/skill/materialNum10")
    private int materialNum10;
    @BeanPropertySetter(pattern = "skillList/skill/skillAction")
    private String skillAction;

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getFightForceFactor() {
        return fightForceFactor;
    }

    public void setFightForceFactor(int fightForceFactor) {
        this.fightForceFactor = fightForceFactor;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public String getSkillUrl() {
        return skillUrl;
    }

    public void setSkillUrl(String skillUrl) {
        this.skillUrl = skillUrl;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getReplaceSkill() {
        return replaceSkill;
    }

    public void setReplaceSkill(int replaceSkill) {
        this.replaceSkill = replaceSkill;
    }

    public int getMaterialNum2() {
        return materialNum2;
    }

    public void setMaterialNum2(int materialNum2) {
        this.materialNum2 = materialNum2;
    }

    public int getMaterialNum3() {
        return materialNum3;
    }

    public void setMaterialNum3(int materialNum3) {
        this.materialNum3 = materialNum3;
    }

    public int getMaterialNum4() {
        return materialNum4;
    }

    public void setMaterialNum4(int materialNum4) {
        this.materialNum4 = materialNum4;
    }

    public int getMaterialNum5() {
        return materialNum5;
    }

    public void setMaterialNum5(int materialNum5) {
        this.materialNum5 = materialNum5;
    }

    public int getMaterialNum6() {
        return materialNum6;
    }

    public void setMaterialNum6(int materialNum6) {
        this.materialNum6 = materialNum6;
    }

    public int getMaterialNum7() {
        return materialNum7;
    }

    public void setMaterialNum7(int materialNum7) {
        this.materialNum7 = materialNum7;
    }

    public int getMaterialNum8() {
        return materialNum8;
    }

    public void setMaterialNum8(int materialNum8) {
        this.materialNum8 = materialNum8;
    }

    public int getMaterialNum9() {
        return materialNum9;
    }

    public void setMaterialNum9(int materialNum9) {
        this.materialNum9 = materialNum9;
    }

    public int getMaterialNum10() {
        return materialNum10;
    }

    public void setMaterialNum10(int materialNum10) {
        this.materialNum10 = materialNum10;
    }

    public String getSkillAction() {
        return skillAction;
    }

    public void setSkillAction(String skillAction) {
        this.skillAction = skillAction;
    }

    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }
}
