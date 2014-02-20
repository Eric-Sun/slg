package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-13
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "rolelevelList/rolelevel")
public class RoleLevelCO {
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/level")
    private int level;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/xp")
    private int xp;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/soldier1")
    private int soldier1;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/soldier2")
    private int soldier2;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/soldier3")
    private int soldier3;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/soldier4")
    private int soldier4;
    @BeanPropertySetter(pattern = "rolelevelList/rolelevel/soldier5")
    private int soldier5;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getSoldier1() {
        return soldier1;
    }

    public void setSoldier1(int soldier1) {
        this.soldier1 = soldier1;
    }

    public int getSoldier2() {
        return soldier2;
    }

    public void setSoldier2(int soldier2) {
        this.soldier2 = soldier2;
    }

    public int getSoldier3() {
        return soldier3;
    }

    public void setSoldier3(int soldier3) {
        this.soldier3 = soldier3;
    }

    public int getSoldier4() {
        return soldier4;
    }

    public void setSoldier4(int soldier4) {
        this.soldier4 = soldier4;
    }

    public int getSoldier5() {
        return soldier5;
    }

    public void setSoldier5(int soldier5) {
        this.soldier5 = soldier5;
    }
}
