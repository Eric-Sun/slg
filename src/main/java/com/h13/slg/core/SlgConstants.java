package com.h13.slg.core;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-9-11
 * Time: 上午1:15
 * To change this template use File | Settings | File Templates.
 */
public class SlgConstants {

    public static class Team {
    }

    public static class Role {
        public final static int NO_SKILL_SELECTED = 0;
        public final static int NO_ROLE = 0;
        public final static int NO_EQUIP_ID = 0;
        public final static int DEFAULT_LEVEL = 1;
        public final static int DEFAULT_XP = 0;

        public static class soldierType {
            public static String QIANG = "0";
            public static String QI = "1";
            public static String GONG = "2";
        }

    }

    public static class Equip {
        public static class EquipType {
            public static String WEAPON = "weapon";
            public static String ARMOR = "armor";
            public static String ACCESSORY = "accessory";
        }

        /**
         * 一个装备初始化之后默认的level
         */
        public static int USER_EQUIP_DEFAULT_LEVEL = 1;
        public final static int DEFAULT_STRENGTHEN = 1;
        public final static int DEFAULT_LEVEL = 1;
    }

    public static class Fight {
        public static final int ATTACK_ATTACK = 0;
        public static final int DEFENCE_ATTACK = 1;

        public class BuffType {
            public static final String DANTI = "danti";
            public static final String QIANG = "qiang";
            public static final String QI = "qi";
            public static final String GONG = "gong";
            public static final String SHIZI = "shizi";
            public static final String ALL = "all";

        }

        public class BuffStartTime {
            public static final String NOW = "now";
            public static final String WAIT = "wait";
        }

        public class Round {
            public static final String GLOBAL = "0";
        }

        public class Target {
            public static final String ZIJI = "ziji";
            public static final String DUIFANG = "duifang";
        }

        public class Owner {
            public static final String ATTACK = "attack";
            public static final String DEFENCE = "defence";
        }

    }

    public static class Shop {
        public static class CURRENCY {
            public static String CASH = "cash";
            public static String HONOR = "honor";
        }

        public static class Type {
            public static String MATERIAL = "material";
            public static String EQUIP = "equip";
        }

    }

    public static class RoleSkillConstants {

        public static final String TIAN = "tian";
        public static final String DI = "di";
        public static final String XUAN = "xuan";
        public static final String HUANG = "huang";


        public static final int NOT_GOT = 0;
        public static final int GOT = 1;


        public static class SkillType {
            public static final String JIANGLING = "jiangling";
            public static final String TIANFU = "tianfu";
        }

        public static class RunTime {
            public static final String NOW = "0";
            public static final String NEXT_ROUND = "1";
        }

        public static class RunTarget {
            public static final String MATE = "0";
            public static final String ENEMY = "1";
        }

        public static class RunType {
            public static final String DANTI = "danti";
            public static final String QIANG = "qiang";
            public static final String GONG = "gong";
            public static final String QI = "qi";
            public static final String SHIZI = "shizi";
            public static final String ALL = "all";
        }

    }

    public static class ZuLingConstants {
        public static final int SIZE = 4;
    }

    public static class TavernConstants {
        public static int DEFAULT = 0;
        public static int GOT = 1;
    }


    public static class PackageConstants {
        public static class PACKAGE {
            public static String MATERIAL_SPACE = "material";
            public static String GEM_SPACE = "gem";
            public static String EQUIP_SPACE = "equip";
            public static String ROLE_CODE_SPACE = "role_card";
        }

    }


}
