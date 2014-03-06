package com.h13.slg.core.util;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-21
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class ResourceCalUtil {


    public static int calFoodOrGold(long last, long current, int perHour) {
        return new Double((current - last) / (1000 * 1.0 * 60 * 60) * perHour).intValue();
    }


    /**
     * 通过两次收获的时间间隔计算相应的应收获的总量
     *
     * @param curResource 当前资源的数量
     * @param lastTimer   上次收获的时间
     * @param curTimer    本次收获的时间
     * @param perHour     该资源每小时的产量
     * @param maxResource 本次收获的时候能收获的资源的最大量
     * @return
     */
    public static int calResource4Harvest(int curResource, long lastTimer, long curTimer, int perHour, int maxResource) {
        int finalResource = 0;
        int tmp = calFoodOrGold(lastTimer, curTimer, perHour);
        if (tmp >= maxResource) {
            finalResource = curResource + maxResource;
        } else {
            finalResource = curResource + tmp;
        }
        return finalResource;
    }


    /**
     * 普通的计算资源的添加
     *
     * @param curResource
     * @param food
     * @param maxResource
     * @return
     */
    public static int calResource4SimpleAdd(int curResource, int food, int maxResource) {
        int finalResource = 0;
        int tmp = food;
        if (curResource + tmp >= maxResource) {
            finalResource = maxResource;
        } else {
            finalResource = curResource + tmp;
        }
        return finalResource;
    }
}
