package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-2
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "gemList/gem")
public class GemCO {

    @BeanPropertySetter(pattern = "gemList/gem/id")
    private int id;
    @BeanPropertySetter(pattern = "gemList/gem/name")
    private String name;
    @BeanPropertySetter(pattern = "gemList/gem/color")
    private String color;
    @BeanPropertySetter(pattern = "gemList/gem/quality")
    private int quality;
    @BeanPropertySetter(pattern = "gemList/gem/key")
    private String key;
    @BeanPropertySetter(pattern = "gemList/gem/value")
    private int value;
    @BeanPropertySetter(pattern = "gemList/gem/buyable")
    private int buyable;
    @BeanPropertySetter(pattern = "gemList/gem/url")
    private String url;
    @BeanPropertySetter(pattern = "gemList/gem/desc")
    private String desc;
    @BeanPropertySetter(pattern = "gemList/gem/sell")
    private int sell;
    @BeanPropertySetter(pattern = "gemList/gem/fightForceFactor")
    private int fightForceFactor;


    public int getBuyable() {
        return buyable;
    }

    public void setBuyable(int buyable) {
        this.buyable = buyable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFightForceFactor() {
        return fightForceFactor;
    }

    public void setFightForceFactor(int fightForceFactor) {
        this.fightForceFactor = fightForceFactor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
