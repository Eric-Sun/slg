package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-13
 * Time: 下午6:04
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "tavernList/tavern")
public class TavernConfigCO {
    @BeanPropertySetter(pattern = "tavernList/tavern/level")
    private int level;
    @BeanPropertySetter(pattern = "tavernList/tavern/upRate")
    private int upRate;
    @BeanPropertySetter(pattern = "tavernList/tavern/white")
    private int white;
    @BeanPropertySetter(pattern = "tavernList/tavern/blue")
    private int blue;
    @BeanPropertySetter(pattern = "tavernList/tavern/purple")
    private int purple;
    @BeanPropertySetter(pattern = "tavernList/tavern/orange")
    private int orange;
    @BeanPropertySetter(pattern = "tavernList/tavern/gold")
    private int gold;

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUpRate() {
        return upRate;
    }

    public void setUpRate(int upRate) {
        this.upRate = upRate;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getPurple() {
        return purple;
    }

    public void setPurple(int purple) {
        this.purple = purple;
    }

    public int getOrange() {
        return orange;
    }

    public void setOrange(int orange) {
        this.orange = orange;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
