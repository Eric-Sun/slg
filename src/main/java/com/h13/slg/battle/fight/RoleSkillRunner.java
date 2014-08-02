package com.h13.slg.battle.fight;

import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.skill.helper.RoleSkillHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 在以下两种情况的时候，这个类发挥作用
 * 1. 战斗开始前，触发双方的天赋技能
 * 2. 战斗进行时，当将领技能被出发的时候
 * <p/>
 * 主要功能为解析roleSkill，然后解析相关参数，给对方或者己方增加buff
 */
public class RoleSkillRunner {

    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;


    /**
     * 发送天赋技能的时候触发
     *
     * @param tianfuRoleSkillId 天赋技能id
     * @param attack            发动技能方
     * @param defence           被动方
     */
    public void tianfu(int tianfuRoleSkillId, FightUnit attack, FightUnit defence) {
        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(tianfuRoleSkillId + "");


    }
}
