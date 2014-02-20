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
@ObjectCreate(pattern = "armorList/armor")
public class ArmorCO {
    @BeanPropertySetter(pattern = "armorList/armor/strength")
    private int strength;
    @BeanPropertySetter(pattern = "armorList/armor/purple1")
    private int purple1;
    @BeanPropertySetter(pattern = "armorList/armor/purple2")
    private int purple2;
    @BeanPropertySetter(pattern = "armorList/armor/purple3")
    private int purple3;
    @BeanPropertySetter(pattern = "armorList/armor/purple4")
    private int purple4;
    @BeanPropertySetter(pattern = "armorList/armor/purple5")
    private int purple5;
    @BeanPropertySetter(pattern = "armorList/armor/purple6")
    private int purple6;
    @BeanPropertySetter(pattern = "armorList/armor/purple7")
    private int purple7;
    @BeanPropertySetter(pattern = "armorList/armor/purple8")
    private int purple8;
    @BeanPropertySetter(pattern = "armorList/armor/purple9")
    private int purple9;
    @BeanPropertySetter(pattern = "armorList/armor/purple10")
    private int purple10;
    @BeanPropertySetter(pattern = "armorList/armor/purple11")
    private int purple11;
    @BeanPropertySetter(pattern = "armorList/armor/purple12")
    private int purple12;
    @BeanPropertySetter(pattern = "armorList/armor/purple13")
    private int purple13;
    @BeanPropertySetter(pattern = "armorList/armor/purple14")
    private int purple14;
    @BeanPropertySetter(pattern = "armorList/armor/purple15")
    private int purple15;
    @BeanPropertySetter(pattern = "armorList/armor/purple16")
    private int purple16;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getPurple1() {
        return purple1;
    }

    public void setPurple1(int purple1) {
        this.purple1 = purple1;
    }

    public int getPurple2() {
        return purple2;
    }

    public void setPurple2(int purple2) {
        this.purple2 = purple2;
    }

    public int getPurple3() {
        return purple3;
    }

    public void setPurple3(int purple3) {
        this.purple3 = purple3;
    }

    public int getPurple4() {
        return purple4;
    }

    public void setPurple4(int purple4) {
        this.purple4 = purple4;
    }

    public int getPurple5() {
        return purple5;
    }

    public void setPurple5(int purple5) {
        this.purple5 = purple5;
    }

    public int getPurple6() {
        return purple6;
    }

    public void setPurple6(int purple6) {
        this.purple6 = purple6;
    }

    public int getPurple7() {
        return purple7;
    }

    public void setPurple7(int purple7) {
        this.purple7 = purple7;
    }

    public int getPurple8() {
        return purple8;
    }

    public void setPurple8(int purple8) {
        this.purple8 = purple8;
    }

    public int getPurple9() {
        return purple9;
    }

    public void setPurple9(int purple9) {
        this.purple9 = purple9;
    }

    public int getPurple10() {
        return purple10;
    }

    public void setPurple10(int purple10) {
        this.purple10 = purple10;
    }

    public int getPurple11() {
        return purple11;
    }

    public void setPurple11(int purple11) {
        this.purple11 = purple11;
    }

    public int getPurple12() {
        return purple12;
    }

    public void setPurple12(int purple12) {
        this.purple12 = purple12;
    }

    public int getPurple13() {
        return purple13;
    }

    public void setPurple13(int purple13) {
        this.purple13 = purple13;
    }

    public int getPurple14() {
        return purple14;
    }

    public void setPurple14(int purple14) {
        this.purple14 = purple14;
    }

    public int getPurple15() {
        return purple15;
    }

    public void setPurple15(int purple15) {
        this.purple15 = purple15;
    }

    public int getPurple16() {
        return purple16;
    }

    public void setPurple16(int purple16) {
        this.purple16 = purple16;
    }
}
