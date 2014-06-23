package com.h13.slg.core;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 上午12:45
 * To change this template use File | Settings | File Templates.
 */
public class ErrorCodeConstants {

    public static int COMMON_ERROR = 0001;

    public static class User {
        public static int NAME_OR_PASSWORD_ERROR = 1001;
        public static int NAME_EXISTS = 1002;
        public static int DONT_HAVE_ENOUGH_FOOD = 1003;
        public static int DONT_HAVE_ENOUGH_GOLD = 1004;
    }

    public static class Role {
        public static int EQUIP_STRENGTH_LEVEL_TO_TOP = 2001;
        public static int RESOURCE_IS_NOT_ENOUGH = 2002;
        public static int DONT_HAVE_THIS_USER_ROLE = 2003;
    }

    public static class Tavern {
        public static int TAVERN_IS_FULL = 3001;
        public static int TAVERN_IS_EMPTY = 3002;
    }

    public static class Team {
        public static int POS_HAVE_USER_ROLE = 4001;
        public static int USER_ROLE_HAVE_IN_TEAM = 4002;
        public static int POST_IS_NO_USER_ROLE = 4003;
    }
}
