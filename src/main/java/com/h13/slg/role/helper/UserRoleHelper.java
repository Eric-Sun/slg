package com.h13.slg.role.helper;

import com.h13.slg.config.cache.RoleCache;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.RoleLevelCO;
import com.h13.slg.config.fetcher.RoleLevelConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.dao.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午8:16
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserRoleHelper {

    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    FightForceHelper fightForceHelper;
    @Autowired
    RoleCache roleCache;
    @Autowired
    RoleLevelConfigFetcher roleLevelConfigFetcher;

    /**
     * 在创建一个账号的时候，有一个默认的人物
     *
     * @param uid
     */
    public long addRoleForRegister(long uid) {
        long rid = 48;
        UserRoleCO userRoleCO = add(uid, rid);
        return userRoleCO.getId();
    }

    /**
     * 更新数据库中战斗力
     *
     * @param urId
     * @param fightForce
     */
    public void updateFightForce(long uid, long urId, int fightForce) {
        UserRoleCO userRoleCO = getUserRole(uid, urId);
        userRoleCO.setFightForce(fightForce);
        updateUserRole(userRoleCO);
        SlgLogger.info(SlgLoggerEntity.p("userRole", "updateFightForce", userRoleCO.getUid(), "ok")
                .addParam("urId", urId)
                .addParam("fightForce", fightForce)
        );
    }

    /**
     * 更新用户将领对象
     *
     * @param userRoleCO
     */
    public void updateUserRole(UserRoleCO userRoleCO) {
        userRoleDAO.update(userRoleCO.getId(), userRoleCO.getWeapon(), userRoleCO.getArmor(),
                userRoleCO.getAccessory(),
                userRoleCO.getFightForce(),
                userRoleCO.getLevel(),
                userRoleCO.getCurSkill(),
                userRoleCO.getSkillLevels(), userRoleCO.getRoleName(), userRoleCO.getXp());
    }

    /**
     * 添加一个新的用户将领
     *
     * @param uid
     * @param rId
     * @return
     */
    public UserRoleCO add(long uid, long rId) {
        UserRoleCO userRoleCO = new UserRoleCO();
        RoleCO roleCO = roleCache.get(rId + "");
        String name = roleCO.getName();
        int fightForce = roleCO.getFightForce();
        int attack = roleCO.getAttack();
        int defence = roleCO.getDefence();
        int health = roleCO.getHealth();
        int soldier = roleCO.getSoldier();
        int curSkill = RoleConstants.NO_SKILL_SELECTED;
        Map<String, Integer> skillLevels = new HashMap<String, Integer>() {{
            // 5个技能
            put("1", 1);
            put("2", 1);
            put("3", 1);
            put("4", 1);
            put("5", 1);
        }};
        int defaultXp = 0;

        // 检查是否在招贤馆中
        long urid = userRoleDAO.insert(rId, uid, RoleConstants.NO_EQUIP_ID,
                RoleConstants.NO_EQUIP_ID, RoleConstants.NO_EQUIP_ID, 1, fightForce,
                attack, defence, health, soldier, curSkill,
                skillLevels, name, defaultXp);
        SlgLogger.info(SlgLoggerEntity.p("userRole", "add new Role", uid, "ok")
                .addParam("rId", rId)
                .addParam("urId", urid)
        );
        userRoleCO.setSkillLevels(skillLevels);
        userRoleCO.setCurSkill(curSkill);
        userRoleCO.setSoldier(soldier);
        userRoleCO.setDefence(defence);
        userRoleCO.setAccessory(RoleConstants.NO_EQUIP_ID);
        userRoleCO.setAttack(attack);
        userRoleCO.setArmor(RoleConstants.NO_EQUIP_ID);
        userRoleCO.setWeapon(RoleConstants.NO_EQUIP_ID);
        userRoleCO.setHealth(health);
        userRoleCO.setId(urid);
        userRoleCO.setUid(uid);
        userRoleCO.setLevel(1);
        userRoleCO.setRoleName(name);
        userRoleCO.setRoleId(rId);
        return userRoleCO;
    }


    /**
     * 穿戴装备
     *
     * @param uid
     * @param urid
     * @param ueid
     * @throws RequestErrorException
     */
    public void wear(long uid, long urid, long ueid) throws RequestErrorException {
        UserRoleCO ur = getUserRole(uid, urid);
        UserEquipCO ue = userEquipHelper.getUserEquip(uid, ueid);
        if (ue.getUid() != uid) {
            SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "ue's uid is not target uid.")
                    .addParam("ue-uid", ue.getUid())
                    .addParam("uid", uid));
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
        }
        if (ue.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            if (ur.getAccessory() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "accessory is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentAccessory", ur.getAccessory()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }

            ur.setAccessory(ue.getId());
        } else if (ue.getType().equals(EquipConstants.EquipType.ARMOR)) {
            if (ur.getArmor() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "armor is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentArmor", ur.getArmor()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
            ur.setArmor(ue.getId());
        } else {
            if (ur.getWeapon() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "weapon is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentWeapon", ur.getWeapon()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
            ur.setWeapon(ue.getId());
        }
        ue.setUrid(ur.getId());
        updateUserRole(ur);
        userEquipHelper.updateUserEquip(ue);
        SlgLogger.info(SlgLoggerEntity.p("role", "wear", uid, "ok")
                .addParam("urid", urid)
                .addParam("ueid", ueid));

        fightForceHelper.updateUserRoleFightForce(uid, urid);
    }

    /**
     * 脱下装备
     *
     * @param uid
     * @param urid
     * @param ueid
     * @throws RequestErrorException
     */
    public void takeOff(long uid, long urid, long ueid) throws RequestErrorException {
        UserRoleCO ur = getUserRole(uid, urid);
        UserEquipCO ue = userEquipHelper.getUserEquip(uid, ueid);
        if (ue.getUid() != uid) {
            SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "ue's uid is not target uid.")
                    .addParam("ue-uid", ue.getUid())
                    .addParam("uid", uid));
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
        }
        if (ue.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            if (ur.getAccessory() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "accessory don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentAccessory", ur.getAccessory()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
            ur.setAccessory(RoleConstants.NO_EQUIP_ID);
        } else if (ue.getType().equals(EquipConstants.EquipType.ARMOR)) {
            if (ur.getArmor() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "armor don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentArmor", ur.getArmor()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
            ur.setArmor(RoleConstants.NO_EQUIP_ID);
        } else {
            if (ur.getWeapon() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "weapon don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentWeapon", ur.getWeapon()));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
            ur.setWeapon(RoleConstants.NO_EQUIP_ID);
        }
        updateUserRole(ur);
        ue.setUrid(0);
        userEquipHelper.updateUserEquip(ue);
        fightForceHelper.updateUserRoleFightForce(uid, ue.getUrid());
        SlgLogger.info(SlgLoggerEntity.p("role", "takeOff", uid, "ok")
                .addParam("urid", urid)
                .addParam("ueid", ueid));
        fightForceHelper.updateUserRoleFightForce(uid, urid);
    }


    public void updateAttackDefenceHealth(long uid, long urid, int finalAttack, int finalDefence, int finalHealth) {
        UserRoleCO userRoleCO = getUserRole(uid, urid);
        userRoleCO.setAttack(finalAttack);
        userRoleCO.setDefence(finalDefence);
        userRoleCO.setHealth(finalHealth);
        updateUserRole(userRoleCO);
        SlgLogger.info(SlgLoggerEntity.p("userRole", "updateFightForce", userRoleCO.getUid(), "ok")
                .addParam("urid", urid)
                .addParam("finalAttack", finalAttack)
                .addParam("finalDefence", finalDefence)
                .addParam("finalHealth", finalHealth)
        );
    }


    /**
     * 获得用户将领的列表
     *
     * @param uid
     * @return
     */
    public List<UserRoleCO> getUserRoleList(long uid) {
        List<UserRoleCO> userRoleList = userRoleDAO.getRoleList(uid);
        return userRoleList;

    }


    /**
     * 获得用户将领的详细信息
     *
     * @param uid
     * @param urid
     * @return
     */
    public UserRoleCO getUserRole(long uid, long urid) {
        UserRoleCO userRoleCO = userRoleDAO.get(uid, urid);
        return userRoleCO;
    }

    /**
     * 判断这个UserRole是不是这个用户的
     * 如果是返回true
     *
     * @param uid
     * @param urid
     * @return
     */
    public boolean checkUserRole(long uid, int urid) {
        return userRoleDAO.check(uid, urid);
    }


    /**
     * 增加用户将领的经验，可能会升级武将
     *
     * @param uid
     * @param urid
     * @param xp
     */
    public void addXp(int uid, int urid, int xp) throws RequestErrorException {
        UserRoleCO userRoleCO = getUserRole(uid, urid);
        RoleLevelCO userLevelCO = roleLevelConfigFetcher.get(userRoleCO.getLevel() + "");
        int curXp = userRoleCO.getXp();
        int finalXp = curXp + xp;
        boolean levelUp = false;
        if (userLevelCO.getXp() > finalXp) {
            userRoleCO.setXp(finalXp);
        } else {
            int curLevel = userRoleCO.getLevel();
            int nextLevel = curLevel + 1;
            userRoleCO.setLevel(nextLevel);
            userRoleCO.setXp(finalXp);
            levelUp = true;
            fightForceHelper.updateUserRoleFightForce(uid, urid);

        }

        updateUserRole(userRoleCO);

        SlgLogger.info(SlgLoggerEntity.p("userRole", "addXp", userRoleCO.getUid(), "ok")
                .addParam("urid", urid)
                .addParam("finalXp", finalXp)
                .addParam("levelUp", levelUp)
        );
    }

}
