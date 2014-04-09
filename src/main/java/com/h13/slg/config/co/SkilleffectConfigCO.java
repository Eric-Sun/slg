package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-20
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "skilleffectList/skilleffect")
public class SkilleffectConfigCO {
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/soldier")
    private int soldier;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/skill")
    private int skill;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/type")
    private int type;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/level")
    private int level;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/effect")
    private String effect;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/param")
    private int param;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/isSelf")
    private int isSelf;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/desc")
    private String desc;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/round")
    private int round;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/damage")
    private int damage;
    @BeanPropertySetter(pattern = "skilleffectList/skilleffect/tips")
    private String tips;


    public int getSoldier() {
        return soldier;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }

    public int getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(int self) {
        isSelf = self;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
