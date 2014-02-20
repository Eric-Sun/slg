package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-20
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "materialList/material")
public class MaterialCO {
    @BeanPropertySetter(pattern = "materialList/material/id")
    private int id;
    @BeanPropertySetter(pattern = "materialList/material/name")
    private String name;
    @BeanPropertySetter(pattern = "materialList/material/category")
    private String category;
    @BeanPropertySetter(pattern = "materialList/material/color")
    private String color;
    @BeanPropertySetter(pattern = "materialList/material/sell")
    private int sell;
    @BeanPropertySetter(pattern = "materialList/material/buyable")
    private int buyable;
    @BeanPropertySetter(pattern = "materialList/material/canUse")
    private int canUse;
    @BeanPropertySetter(pattern = "materialList/material/useType")
    private int useType;
    @BeanPropertySetter(pattern = "materialList/material/useEffect")
    private int useEffect;
    @BeanPropertySetter(pattern = "materialList/material/useNeed")
    private int useNeed;
    @BeanPropertySetter(pattern = "materialList/material/endTime")
    private int endTime;
    @BeanPropertySetter(pattern = "materialList/material/userInterval")
    private int userInterval;
    @BeanPropertySetter(pattern = "materialList/material/url")
    private String url;
    @BeanPropertySetter(pattern = "materialList/material/desc")
    private String desc;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getCanUse() {
        return canUse;
    }

    public void setCanUse(int canUse) {
        this.canUse = canUse;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public int getUseEffect() {
        return useEffect;
    }

    public void setUseEffect(int useEffect) {
        this.useEffect = useEffect;
    }

    public int getUseNeed() {
        return useNeed;
    }

    public void setUseNeed(int useNeed) {
        this.useNeed = useNeed;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getUserInterval() {
        return userInterval;
    }

    public void setUserInterval(int userInterval) {
        this.userInterval = userInterval;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
