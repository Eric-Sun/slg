package com.h13.slg.role.helper;

import com.h13.slg.config.cache.*;
import com.h13.slg.config.co.*;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 战斗力相关数据
 */
@Service
public class FightForceHelper {

    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    RoleCache roleCache;
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    EquipCache equipCache;
    @Autowired
    ArmorCache armorCache;
    @Autowired
    AccessoryCache accessoryCache;
    @Autowired
    WeaponCache weaponCache;
    @Autowired
    UserStatusHelper userStatusHelper;


    public int updateUserRoleFightForce(UserRoleCO userRoleCO) throws RequestFatalException, RequestUnexpectedException {

        int oldFightForce = userRoleCO.getFightForce();
        int uid = userRoleCO.getUid();
        int urid = userRoleCO.getId();
        // 获得人物本身的fightforce
        int roleId = userRoleCO.getRoleId();
        RoleCO roleCO = roleCache.get(roleId + "");
        // 需要计算成长，然后算一个新的
        int roleFightForce = roleCO.getFightForce();
        int attackGrouth = roleCO.getAttackGrouth();
        int defenceGrouth = roleCO.getDefenceGrouth();
        int healthGrouth = roleCO.getHealthGrouth();
        int baseAttack = roleCO.getAttack();
        int baseDefence = roleCO.getDefence();
        int baseHealth = roleCO.getHealth();
        int level = userRoleCO.getLevel();
        int finalUserRoleAttack = baseAttack + (level - 1) * attackGrouth;
        int finalUserRoleDefence = baseDefence + (level - 1) * defenceGrouth;
        int finalUserRoleHealth = baseHealth + (level - 1) * healthGrouth;
        roleFightForce = roleFightForce +
                calFightForceByAttack(finalUserRoleAttack)
                +
                calFightForceByDefence(finalUserRoleDefence)
                +
                calFightForceByHealth(finalUserRoleHealth);

        // 获得装备的fightforce
        int ueWeaponId = userRoleCO.getWeapon();
        int ueArmorId = userRoleCO.getArmor();
        int ueAccessoryId = userRoleCO.getAccessory();
        int weaponAttack = 0;
        int weaponFightForce = 0;
        int armorDefence = 0;
        int armorFightForce = 0;
        int accessoryHealth = 0;
        int accessoryFightForce = 0;
        if (ueWeaponId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO weapon = userEquipHelper.getUserEquip(uid, ueWeaponId);
            weaponAttack = getWeaponAttack(weapon);
            weaponFightForce = calFightForceByAttack(weaponAttack);
        }
        if (ueArmorId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO armor = userEquipHelper.getUserEquip(uid, ueArmorId);
            armorDefence = getArmorDefence(armor);
            armorFightForce = calFightForceByDefence(armorDefence);
        }
        if (ueAccessoryId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO accessory = userEquipHelper.getUserEquip(uid, ueAccessoryId);
            accessoryHealth = getAccessoryHealth(accessory);
            accessoryFightForce = calFightForceByHealth(accessoryHealth);
        }

        int finalFightForce =
                roleFightForce
                        +
                        weaponFightForce
                        +
                        armorFightForce
                        +
                        accessoryFightForce;
        SlgLogger.info(SlgLoggerEntity.p("fightForce", "calUserRole", uid, "ok")
                .addParam("oldFightForce", oldFightForce)
                .addParam("roleFightForce", roleFightForce)
                .addParam("weaponFightForce", weaponFightForce)
                .addParam("armorFightForce", armorFightForce)
                .addParam("accessoryFightForce", accessoryFightForce)
                .addParam("finalFightForce", finalFightForce));

        int finalAttack = finalUserRoleAttack + weaponAttack;
        int finalDefence = finalUserRoleDefence + armorDefence;
        int finalHealth = finalUserRoleHealth + accessoryHealth;

//        userRoleCO.setAttack(finalAttack);
//        userRoleCO.setDefence(finalDefence);
//        userRoleCO.setHealth(finalHealth);

        userRoleHelper.updateUserRole(userRoleCO);

        // 更新全局的战斗力
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        userStatusHelper.updateUserStatus(userStatusCO);

        return finalFightForce;

    }


    /**
     * 计算武器带来的攻击力
     *
     * @param ue
     * @return
     */
    private int getWeaponAttack(UserEquipCO ue) throws RequestFatalException {
        try {
            int strength = ue.getStrength();
            WeaponCO weaponCO = weaponCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(weaponCO, "purple" + ue.getLevel());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestFatalException(CodeConstants.SYSTEM.COMMON_ERROR, "system error.", e);
        }
    }


    private int getArmorDefence(UserEquipCO ue) throws RequestFatalException {
        try {
            int strength = ue.getStrength();
            ArmorCO armorCO = armorCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(armorCO, "purple" + ue.getLevel());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestFatalException(CodeConstants.SYSTEM.COMMON_ERROR, "system error.", e);
        }
    }

    private int getAccessoryHealth(UserEquipCO ue) throws RequestFatalException {
        try {
            int strength = ue.getStrength();
            AccessoryCO accessoryCO = accessoryCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(accessoryCO, "purple" + ue.getLevel());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestFatalException(CodeConstants.SYSTEM.COMMON_ERROR, "system error.", e);
        }
    }


    private int calFightForceByAttack(int attack) {
        return attack;
    }

    private int calFightForceByDefence(int defence) {
        return defence / 2;
    }

    private int calFightForceByHealth(int health) {
        return health / 4;
    }

}
