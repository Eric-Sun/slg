package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午6:03
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "shopList/shop")
public class ShopCO {
    @BeanPropertySetter(pattern = "shopList/shop/id")
    private int id;
    @BeanPropertySetter(pattern = "shopList/shop/label1")
    private int label1;
    @BeanPropertySetter(pattern = "shopList/shop/label2")
    private int label2;
    @BeanPropertySetter(pattern = "shopList/shop/category1")
    private String category1;
    @BeanPropertySetter(pattern = "shopList/shop/category2")
    private String category2;
    @BeanPropertySetter(pattern = "shopList/shop/category3")
    private String category3;
    @BeanPropertySetter(pattern = "shopList/shop/currency")
    private String currency;
    @BeanPropertySetter(pattern = "shopList/shop/price")
    private int price;
    @BeanPropertySetter(pattern = "shopList/shop/discount")
    private int discount;
    @BeanPropertySetter(pattern = "shopList/shop/restrictions")
    private int restrictions;
    @BeanPropertySetter(pattern = "shopList/shop/openDays")
    private int openDays;
    @BeanPropertySetter(pattern = "shopList/shop/openLevel")
    private int openLevel;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabel1() {
        return label1;
    }

    public void setLabel1(int label1) {
        this.label1 = label1;
    }

    public int getLabel2() {
        return label2;
    }

    public void setLabel2(int label2) {
        this.label2 = label2;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(int restrictions) {
        this.restrictions = restrictions;
    }

    public int getOpenDays() {
        return openDays;
    }

    public void setOpenDays(int openDays) {
        this.openDays = openDays;
    }

    public int getOpenLevel() {
        return openLevel;
    }

    public void setOpenLevel(int openLevel) {
        this.openLevel = openLevel;
    }
}
