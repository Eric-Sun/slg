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
@ObjectCreate(pattern = "businessList/business")
public class BusinessCO {
    @BeanPropertySetter(pattern = "businessList/business/id")
    private int id;
    @BeanPropertySetter(pattern = "businessList/business/name")
    private String name;
    @BeanPropertySetter(pattern = "businessList/business/desc")
    private String desc;
    @BeanPropertySetter(pattern = "businessList/business/gem1")
    private String gem1;
    @BeanPropertySetter(pattern = "businessList/business/gem2")
    private String gem2;
    @BeanPropertySetter(pattern = "businessList/business/gem3")
    private String gem3;
    @BeanPropertySetter(pattern = "businessList/business/gem4")
    private String gem4;
    @BeanPropertySetter(pattern = "businessList/business/gem5")
    private String gem5;
    @BeanPropertySetter(pattern = "businessList/business/gem6")
    private String gem6;
    @BeanPropertySetter(pattern = "businessList/business/gem7")
    private String gem7;
    @BeanPropertySetter(pattern = "businessList/business/gem8")
    private String gem8;
    @BeanPropertySetter(pattern = "businessList/business/cardPass")
    private int cardPass;
    @BeanPropertySetter(pattern = "businessList/business/goldOpen")
    private int goldOpen;
    @BeanPropertySetter(pattern = "businessList/business/cash")
    private int cash;
    @BeanPropertySetter(pattern = "businessList/business/gold")
    private int gold;


    public int getCardPass() {
        return cardPass;
    }

    public void setCardPass(int cardPass) {
        this.cardPass = cardPass;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGem1() {
        return gem1;
    }

    public void setGem1(String gem1) {
        this.gem1 = gem1;
    }

    public String getGem2() {
        return gem2;
    }

    public void setGem2(String gem2) {
        this.gem2 = gem2;
    }

    public String getGem3() {
        return gem3;
    }

    public void setGem3(String gem3) {
        this.gem3 = gem3;
    }

    public String getGem4() {
        return gem4;
    }

    public void setGem4(String gem4) {
        this.gem4 = gem4;
    }

    public String getGem5() {
        return gem5;
    }

    public void setGem5(String gem5) {
        this.gem5 = gem5;
    }

    public String getGem6() {
        return gem6;
    }

    public void setGem6(String gem6) {
        this.gem6 = gem6;
    }

    public String getGem7() {
        return gem7;
    }

    public void setGem7(String gem7) {
        this.gem7 = gem7;
    }

    public String getGem8() {
        return gem8;
    }

    public void setGem8(String gem8) {
        this.gem8 = gem8;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGoldOpen() {
        return goldOpen;
    }

    public void setGoldOpen(int goldOpen) {
        this.goldOpen = goldOpen;
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
}
