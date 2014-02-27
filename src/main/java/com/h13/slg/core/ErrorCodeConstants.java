package com.h13.slg.core;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:45
 * To change this template use File | Settings | File Templates.
 */
public class ErrorCodeConstants {

    public static int COMMON_ERROR = 1001;

    public static class User {
        public static int NAME_OR_PASSWORD_ERROR = 1002;
        public static int NAME_EXISTS = 1003;
        public static int DONT_HAVE_ENOUGH_FOOD = 1004;
        public static int DONT_HAVE_ENOUGH_GOLD = 1005;
    }

    public static class Role {
        public static int EQUIP_STRENGTH_LEVEL_TO_TOP = 2001;
        public static int RESOURCE_IS_NOT_ENOUGH = 2002;
    }

}
