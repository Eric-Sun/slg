package com.h13.slg.role.helper;

import com.h13.slg.config.cache.*;
import com.h13.slg.config.co.*;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.EventType;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.task.vo.FinishedPerTaskVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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


    public int updateUserRoleFightForce(long urid) throws RequestErrorException {

        if (urid == EquipConstants.NO_USER_ROLE)
            return 0;
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(urid);
        int oldFightForce = userRoleCO.getFightForce();
        long uid = userRoleCO.getUid();

        // 重新计算新的

        // 获得人物本身的fightforce
        long roleId = userRoleCO.getRoleId();
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
        long ueWeaponId = userRoleCO.getWeapon();
        long ueArmorId = userRoleCO.getArmor();
        long ueAccessoryId = userRoleCO.getAccessory();
        int weaponAttack = 0;
        int weaponFightForce = 0;
        int armorDefence = 0;
        int armorFightForce = 0;
        int accessoryHealth = 0;
        int accessoryFightForce = 0;
        if (ueWeaponId != RoleConstants.NO_EQUIP_ID) {
            UserEquipCO weapon = userEquipHelper.getUserEquip(ueWeaponId);
            weaponAttack = getWeaponAttack(weapon);
            weaponFightForce = calFightForceByAttack(weaponAttack);
        }
        if (ueArmorId != RoleConstants.NO_EQUIP_ID) {
            UserEquipCO armor = userEquipHelper.getUserEquip(ueArmorId);
            armorDefence = getArmorDefence(armor);
            armorFightForce = calFightForceByDefence(armorDefence);
        }
        if( ueAccessoryId!=RoleConstants.NO_EQUIP_ID){
            UserEquipCO accessory = userEquipHelper.getUserEquip(ueAccessoryId);
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

        userRoleHelper.updateAttackDefenceHealth(urid, finalAttack, finalDefence, finalHealth);
        userRoleHelper.updateFightForce(urid, finalFightForce);

        userStatusHelper.updateFightForce(uid, oldFightForce, finalFightForce);

        return finalFightForce;

    }


    /**
     * 计算武器带来的攻击力
     *
     * @param ue
     * @return
     */
    private int getWeaponAttack(UserEquipCO ue) throws RequestErrorException {
        try {
            int strength = ue.getStrength();
            WeaponCO weaponCO = weaponCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(weaponCO, "purple" + ue.getId());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "system error.", e);
        }
    }


    private int getArmorDefence(UserEquipCO ue) throws RequestErrorException {
        try {
            int strength = ue.getStrength();
            ArmorCO armorCO = armorCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(armorCO, "purple" + ue.getId());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "system error.", e);
        }
    }

    private int getAccessoryHealth(UserEquipCO ue) throws RequestErrorException {
        try {
            int strength = ue.getStrength();
            AccessoryCO accessoryCO = accessoryCache.get(strength + "");
            String tmpValue = BeanUtils.getSimpleProperty(accessoryCO, "purple" + ue.getId());
            int value = Integer.parseInt(tmpValue);
            return value;
        } catch (Exception e) {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "system error.", e);
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
