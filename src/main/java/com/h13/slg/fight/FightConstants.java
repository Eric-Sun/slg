package com.h13.slg.fight;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-11
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class FightConstants {

    /**
     * 战斗类型
     */
    public class FightType {
        public static final String PVE = "pve";
        public static final String PVP = "pvp";
    }

    public class Buff {
        public static final String ADD_ATTACK_RATE = "add_attack_rate";
        public static final String ADD_DEFENCE_RATE = "add_defence_rate";
        public static final String ADD_HEALTH_RATE = "add_health_rate";
    }

    public class Action {
        public static final String ATTACK = "attack";
        public static final String DEFENCE = "defence";

        public static final String NO = "no";
    }

    public class Status {
        public static final String PREPARE = "prepare";

        public static final String RUNNING = "running";

        public static final String END = "end";
    }


    public class Win {
        public static final String ATTACK = "attack";
        public static final String DEFENCE = "defence";
    }


    public class Parameter {
        /**
         * 特殊攻击第一次的增加伤害的百分比
         */
        public static final float SPECIAL_ATTACK_FIRST_RATE = 0.5F;
        /**
         * 当武力大于90的时候发动第二次特殊攻击的攻击力增加百分比
         */
        public static final float SPECIAL_ATTACK_SECOEND_RATE = 0.15F;

        /**
         * 攻击力大于多少的时候触发第二次特殊攻击
         */
        public static final float ENHANCE_SPECIAL_ATTACK_ATTACK=90;


    }

}
