package com.h13.slg.role.helper;

import com.h13.slg.config.cache.RoleCache;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.core.ErrorCodeConstants;
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

    /**
     * 在创建一个账号的时候，有一个默认的人物
     *
     * @param uid
     */
    public long addRoleForRegister(long uid) {
        long rid = 48;
        RoleCO roleCO = roleCache.get(rid + "");
        int fightForce = roleCO.getFightForce();
        int attack = roleCO.getAttack();
        int defence = roleCO.getDefence();
        int health = roleCO.getHealth();
        long urid = userRoleDAO.insert(rid, uid,
                RoleConstants.NO_EQUIP_ID, RoleConstants.NO_EQUIP_ID,
                RoleConstants.NO_EQUIP_ID, 1, fightForce,
                attack, defence, health);
        SlgLogger.info(
                SlgLoggerEntity.p("userRole", "addRoleForRegister", uid, "ok")
        );
        return urid;
    }


    /**
     * 更新数据库中战斗力
     *
     * @param urId
     * @param fightForce
     */
    public void updateFightForce(long urId, int fightForce) {
        UserRoleCO userRoleCO = getUserRole(urId);
        userRoleCO.setFightForce(fightForce);
        updateUserRole(userRoleCO);
        SlgLogger.info(SlgLoggerEntity.p("userRole", "updateFightForce", userRoleCO.getUid(), "ok")
                .addParam("urId", urId)
                .addParam("fightForce", fightForce)
        );
    }

    public UserRoleCO getUserRole(long urid) {
        return userRoleDAO.get(urid);
    }

    public void updateUserRole(UserRoleCO userRoleCO) {
        userRoleDAO.update(userRoleCO.getId(), userRoleCO.getWeapon(), userRoleCO.getArmor(),
                userRoleCO.getAccessory(), userRoleCO.getFightForce(), userRoleCO.getLevel());
    }

    public long add(long uid, long rId) {
        RoleCO roleCO = roleCache.get(rId + "");
        int fightForce = roleCO.getFightForce();
        int attack = roleCO.getAttack();
        int defence = roleCO.getDefence();
        int health = roleCO.getHealth();
        // 检查是否在招贤馆中
        long urid = userRoleDAO.insert(rId, uid, RoleConstants.NO_EQUIP_ID,
                RoleConstants.NO_EQUIP_ID, RoleConstants.NO_EQUIP_ID, 1, fightForce,
                attack, defence, health);
        SlgLogger.info(SlgLoggerEntity.p("userRole", "add new Role", uid, "ok")
                .addParam("rId", rId)
                .addParam("urId", urid)
        );
        return urid;
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
        UserRoleCO ur = getUserRole(urid);
        UserEquipCO ue = userEquipHelper.getUserEquip(ueid);
        if (ue.getUid() != uid) {
            SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "ue's uid is not target uid.")
                    .addParam("ue-uid", ue.getUid())
                    .addParam("uid", uid));
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
        }
        if (ue.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            if (ur.getAccessory() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "accessory is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentAccessory", ur.getAccessory()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }

            ur.setAccessory(ue.getId());
        } else if (ue.getType().equals(EquipConstants.EquipType.ARMOR)) {
            if (ur.getArmor() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "armor is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentArmor", ur.getArmor()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
            ur.setArmor(ue.getId());
        } else {
            if (ur.getWeapon() != RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "weapon is have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentWeapon", ur.getWeapon()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
            ur.setWeapon(ue.getId());
        }
        updateUserRole(ur);
        SlgLogger.info(SlgLoggerEntity.p("role", "wear", uid, "ok")
                .addParam("urid", urid)
                .addParam("ueid", ueid));

        fightForceHelper.updateUserRoleFightForce(urid);
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
        UserRoleCO ur = getUserRole(urid);
        UserEquipCO ue = userEquipHelper.getUserEquip(ueid);
        if (ue.getUid() != uid) {
            SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "ue's uid is not target uid.")
                    .addParam("ue-uid", ue.getUid())
                    .addParam("uid", uid));
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
        }
        if (ue.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            if (ur.getAccessory() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "accessory don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentAccessory", ur.getAccessory()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
            ur.setAccessory(RoleConstants.NO_EQUIP_ID);
        } else if (ue.getType().equals(EquipConstants.EquipType.ARMOR)) {
            if (ur.getArmor() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "armor don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentArmor", ur.getArmor()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
            ur.setArmor(RoleConstants.NO_EQUIP_ID);
        } else {
            if (ur.getWeapon() == RoleConstants.NO_EQUIP_ID) {
                SlgLogger.error(SlgLoggerEntity.p("role", "wear", uid, "weapon don't have one")
                        .addParam("ue-uid", ue.getUid())
                        .addParam("uid", uid)
                        .addParam("currentWeapon", ur.getWeapon()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
            ur.setWeapon(RoleConstants.NO_EQUIP_ID);
        }
        updateUserRole(ur);

        fightForceHelper.updateUserRoleFightForce(ue.getUrid());
        SlgLogger.info(SlgLoggerEntity.p("role", "takeOff", uid, "ok")
                .addParam("urid", urid)
                .addParam("ueid", ueid));
        fightForceHelper.updateUserRoleFightForce(urid);
    }


    public void updateAttackDefenceHealth(long urid, int finalAttack, int finalDefence, int finalHealth) {
        UserRoleCO userRoleCO = getUserRole(urid);
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
}
