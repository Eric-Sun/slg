package com.h13.slg.core;

/**
 * 跟前端交互的code，对应前端的不同的文字等
 */
public class CodeConstants {

    public static class SYSTEM {
        public static int SUCCESS = 0;
        public static int COMMON_ERROR = 1001;
        public static int TOKEN_INVALID = 1002;
    }


    public static class User {
        public static int NAME_OR_PASSWORD_ERROR = 2001;
        public static int NAME_EXISTS = 2002;
        public static int DONT_HAVE_ENOUGH_FOOD = 2003;
        public static int DONT_HAVE_ENOUGH_GOLD = 2004;
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

    public static class ZuLing {
        public static int ZULING_IS_FULL = 5001;
        public static int SKILL_HAVE_GOT = 5002;
        public static int ZULING_IS_EMPTY = 5003;

    }

}
