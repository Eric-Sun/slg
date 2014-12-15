package com.h13.slg.role.helper;

import com.h13.slg.config.cache.RoleCache;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.RoleLevelCO;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.config.fetcher.RoleLevelConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.role.cache.UserRoleCache;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.dao.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    RoleConfigFetcher roleConfigFetcher;
    @Autowired
    RoleLevelConfigFetcher roleLevelConfigFetcher;
    @Autowired
    UserRoleCache userRoleCache;

    /**
     * 在创建一个账号的时候，有一个默认的人物
     *
     * @param uid
     */
    public long addDefaultRole(int uid) {
        int rid = 48;
        UserRoleCO userRoleCO = addUserRole(uid, rid);
        return userRoleCO.getId();
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
                userRoleCO.getRoleName(), userRoleCO.getXp(),
                userRoleCO.getPutongSkillId(), userRoleCO.getTianfuSkillId());
    }


    /**
     * 获得用户将领的列表
     *
     * @param uid
     * @return
     */
    public List<UserRoleCO> getUserRoleList(int uid) {
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
    public UserRoleCO getUserRole(int uid, int urid) throws RequestUnexpectedException {

        UserRoleCO userRoleCO = userRoleCache.get(urid);
        if (userRoleCO == null) {
            userRoleCO = userRoleDAO.get(uid, urid);
            userRoleCache.set(userRoleCO);
        } else {
            if (userRoleCO.getUid() != uid) {
                throw new RequestUnexpectedException(CodeConstants.User.UID_USERROLE_NOT_MATCHED,
                        "");
            }
            return userRoleCO;
        }
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
    public boolean checkUserRole(int uid, int urid) {
        try {
            getUserRole(uid, urid);
            return true;
        } catch (RequestUnexpectedException e) {
            return false;
        }
    }


    /**
     * 增加用户将领的经验，可能会升级武将
     */
    public void addXp(UserRoleCO userRoleCO, int xp) throws RequestFatalException, RequestUnexpectedException {
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
            fightForceHelper.updateUserRoleFightForce(userRoleCO);

        }
        updateUserRole(userRoleCO);
    }


    /**
     * 添加一个新的用户将领
     *
     * @param uid
     * @param rId
     * @return
     */
    public UserRoleCO addUserRole(int uid, int rId) {
        UserRoleCO userRoleCO = new UserRoleCO();
        RoleCO roleCO = roleConfigFetcher.get(rId + "");

        userRoleCO.setSoldier(roleCO.getSoldier());
        userRoleCO.setAccessory(SlgConstants.Role.NO_EQUIP_ID);
        userRoleCO.setArmor(SlgConstants.Role.NO_EQUIP_ID);
        userRoleCO.setWeapon(SlgConstants.Role.NO_EQUIP_ID);
        userRoleCO.setFightForce(roleCO.getFightForce());
        userRoleCO.setUid(uid);
        userRoleCO.setLevel(SlgConstants.Role.DEFAULT_LEVEL);
        userRoleCO.setRoleName(roleCO.getName());
        userRoleCO.setRoleId(rId);
        userRoleCO.setXp(SlgConstants.Role.DEFAULT_XP);
        userRoleCO.setPutongSkillId(SlgConstants.Role.NO_SKILL_SELECTED);
        userRoleCO.setTianfuSkillId(SlgConstants.Role.NO_SKILL_SELECTED);

        int urid = userRoleDAO.insert(userRoleCO.getRoleId(), userRoleCO.getUid(), SlgConstants.Role.NO_EQUIP_ID,
                SlgConstants.Role.NO_EQUIP_ID, SlgConstants.Role.NO_EQUIP_ID, SlgConstants.Role.DEFAULT_LEVEL,
                userRoleCO.getFightForce(),
                userRoleCO.getSoldier(), userRoleCO.getRoleName(), userRoleCO.getXp(), userRoleCO.getPutongSkillId(),
                userRoleCO.getTianfuSkillId());

        userRoleCO.setId(urid);
        userRoleCache.set(userRoleCO);
        return userRoleCO;
    }


    /**
     * 穿戴装备
     *
     * @throws com.h13.slg.core.exception.RequestFatalException
     *
     */
    public void wear(UserRoleCO userRoleCO, UserEquipCO userEquipCO) throws RequestFatalException, RequestUnexpectedException {
        if (userEquipCO.getUid() != userRoleCO.getUid()) {
            throw new RequestUnexpectedException(CodeConstants.User.UID_EQUIPID_NOT_MATCHED,
                    String.format("ue's uid=%s,ur's uid=%", userEquipCO.getUid(), userRoleCO.getUid()));
        }
        if (userEquipCO.getType().equals(SlgConstants.Equip.EquipType.ACCESSORY)) {
            if (userRoleCO.getAccessory() != SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("accessory should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId())
                );
            }
            userRoleCO.setAccessory(userEquipCO.getId());
        } else if (userEquipCO.getType().equals(SlgConstants.Equip.EquipType.ARMOR)) {
            if (userRoleCO.getArmor() != SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("armor should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId())
                );
            }
            userRoleCO.setArmor(userEquipCO.getId());
        } else {
            if (userRoleCO.getWeapon() != SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("weapon should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId())
                );
            }
            userRoleCO.setWeapon(userEquipCO.getId());
        }
        userEquipCO.setUrid(userRoleCO.getId());
        updateUserRole(userRoleCO);
        userEquipHelper.updateUserEquip(userEquipCO);
        fightForceHelper.updateUserRoleFightForce(userRoleCO);
    }

    /**
     * 脱下装备
     *
     * @throws com.h13.slg.core.exception.RequestFatalException
     *
     */
    public void takeOff(UserRoleCO userRoleCO, UserEquipCO userEquipCO) throws RequestFatalException, RequestUnexpectedException {
        if (userEquipCO.getUid() != userRoleCO.getUid()) {
            throw new RequestUnexpectedException(CodeConstants.User.UID_EQUIPID_NOT_MATCHED,
                    String.format("ue's uid=%s,ur's uid=%", userEquipCO.getUid(), userRoleCO.getUid()));
        }
        if (userEquipCO.getType().equals(SlgConstants.Equip.EquipType.ACCESSORY)) {
            if (userRoleCO.getAccessory() == SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("accessory should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId()
                        ));
            }
            userRoleCO.setAccessory(SlgConstants.Role.NO_EQUIP_ID);
        } else if (userEquipCO.getType().equals(SlgConstants.Equip.EquipType.ARMOR)) {
            if (userRoleCO.getArmor() == SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("armor should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId())
                );
            }
            userRoleCO.setArmor(SlgConstants.Role.NO_EQUIP_ID);
        } else {
            if (userRoleCO.getWeapon() == SlgConstants.Role.NO_EQUIP_ID) {
                throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_ID_SHOULD_BE_NO_EQUIP_ID,
                        String.format("weapon should be no_equip_id. urid=%s,ueid=%s", userRoleCO.getId(), userEquipCO.getId()));
            }
            userRoleCO.setWeapon(SlgConstants.Role.NO_EQUIP_ID);
        }
        userEquipCO.setUrid(0);
        updateUserRole(userRoleCO);
        userEquipHelper.updateUserEquip(userEquipCO);
        fightForceHelper.updateUserRoleFightForce(userRoleCO);
    }


    /**
     * get UserRole's final attack.
     * <p/>
     * Final Attack = Base Attack + AttackGrouth * current Level
     * The same as Defence and Health
     *
     * @param uid
     * @param urId
     * @return
     */
    public int getAttack(int uid, int urId) throws RequestUnexpectedException {
        UserRoleCO userRoleCO = getUserRole(uid, urId);
        int currentLevel = userRoleCO.getLevel();
        RoleCO roleCO = roleConfigFetcher.get(userRoleCO.getRoleId() + "");
        return roleCO.getAttack() + currentLevel * roleCO.getAttackGrouth();
    }


    public int getDefence(int uid, int urId) throws RequestUnexpectedException {
        UserRoleCO userRoleCO = getUserRole(uid, urId);
        int currentLevel = userRoleCO.getLevel();
        RoleCO roleCO = roleConfigFetcher.get(userRoleCO.getRoleId() + "");
        return roleCO.getDefence() + currentLevel * roleCO.getDefenceGrouth();
    }

    public int getHealth(int uid, int urId) throws RequestUnexpectedException {
        UserRoleCO userRoleCO = getUserRole(uid, urId);
        int currentLevel = userRoleCO.getLevel();
        RoleCO roleCO = roleConfigFetcher.get(userRoleCO.getRoleId() + "");
        return roleCO.getHealth() + currentLevel * roleCO.getHealth();
    }


}
