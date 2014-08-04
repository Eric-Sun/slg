package com.h13.slg.core.util;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-8-4
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
public class SlgStrings {

    public static boolean isZeroOrEmptyOrNull(String p) {
        if (p == null)
            return true;
        if (p.equals("0"))
            return true;
        if (p.equals(""))
            return true;

        return false;
    }
}
