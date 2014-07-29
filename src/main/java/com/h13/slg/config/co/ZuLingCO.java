package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "zuLingList/zuLing")
public class ZuLingCO {
    @BeanPropertySetter(pattern = "zuLingList/zuLing/level")
    private int level;
    @BeanPropertySetter(pattern = "zuLingList/zuLing/tian")
    private int tian;
    @BeanPropertySetter(pattern = "zuLingList/zuLing/di")
    private int di;
    @BeanPropertySetter(pattern = "zuLingList/zuLing/xuan")
    private int xuan;
    @BeanPropertySetter(pattern = "zuLingList/zuLing/huang")
    private int huang;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTian() {
        return tian;
    }

    public void setTian(int tian) {
        this.tian = tian;
    }

    public int getDi() {
        return di;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public int getXuan() {
        return xuan;
    }

    public void setXuan(int xuan) {
        this.xuan = xuan;
    }

    public int getHuang() {
        return huang;
    }

    public void setHuang(int huang) {
        this.huang = huang;
    }
}
