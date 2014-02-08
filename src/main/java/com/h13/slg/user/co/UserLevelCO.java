package com.h13.slg.user.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class UserLevelCO {

    private int id;
    private int fromExp;
    private int toExp;
    private int foodCapacity;
    private int goldCoinCapacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromExp() {
        return fromExp;
    }

    public void setFromExp(int fromExp) {
        this.fromExp = fromExp;
    }

    public int getToExp() {
        return toExp;
    }

    public void setToExp(int toExp) {
        this.toExp = toExp;
    }

    public int getFoodCapacity() {
        return foodCapacity;
    }

    public void setFoodCapacity(int foodCapacity) {
        this.foodCapacity = foodCapacity;
    }

    public int getGoldCoinCapacity() {
        return goldCoinCapacity;
    }

    public void setGoldCoinCapacity(int goldCoinCapacity) {
        this.goldCoinCapacity = goldCoinCapacity;
    }
}
