package com.h13.slg.fight;

import com.google.common.collect.Lists;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.config.co.*;
import com.h13.slg.config.fetcher.BattleConfigFetcher;
import com.h13.slg.config.fetcher.GemConfigFetcher;
import com.h13.slg.config.fetcher.MonsterConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fight Object's create. (Include FUser and FUserRole).
 * In the class . we should load attributes and buffer.
 * Set Attributes and Buffers Handler into the FUser Object.
 */
@Service
public class FCreator {

    @Autowired
    TeamHelper teamHelper;

    @Autowired
    UserRoleHelper userRoleHelper;

    @Autowired
    UserEquipHelper userEquipHelper;

    @Autowired
    GemConfigFetcher gemConfigFetcher;

    @Autowired
    BattleConfigFetcher battleConfigFetcher;

    @Autowired
    MonsterConfigFetcher monsterConfigFetcher;

    @Autowired
    RoleConfigFetcher roleConfigFetcher;


    /**
     * Create FUser Object by uid.
     *
     * @return
     */
    public FUser createByUser(int uid) throws RequestUnexpectedException, RequestFatalException {
        FUser fUser = new FUser();

        // Todo:load mastery.

        // Load team Object
        UserTeamCO userTeamCO = teamHelper.get(uid);
        int leaderId = userTeamCO.getLeader();
        fUser.setLeader(createLeader(uid, leaderId));

        List<Integer> userRoleIdList = userTeamCO.getData();
        List<FUserRole> fUserRoleList = Lists.newLinkedList();
        int pos = 0;
        for (int userRoleId : userRoleIdList) {
            if (userRoleId != SlgConstants.Role.NO_ROLE)
                fUserRoleList.add(createFUserRole(uid, userRoleId, pos));
            else {
                fUserRoleList.add(null);
            }
            pos++;
        }
        fUser.setUserRoleList(fUserRoleList);

        return fUser;
    }


    /**
     * Create battle FUser object by battleId
     *
     * @return
     */
    public FUser createByBattle(int battleId) throws RequestFatalException {
        FUser fUser = new FUser();

        BattleCO battleCO = battleConfigFetcher.get(battleId + "");
        for (int i = 0; i < 9; i++) {
            String monsterIdStr = SlgBeanUtils.getProperty(battleCO, "pos" + i);
            if (monsterIdStr.trim().equals("")) {
                fUser.getUserRoleList().add(null);
            } else {
                // add monster
                int monsterId = new Integer(monsterIdStr);
                MonsterCO monsterCO = monsterConfigFetcher.get(monsterIdStr);
                FUserRole fUserRole = new FUserRole();
                fUserRole.setUrId(monsterId);
                fUserRole.setBaseAttack(new Integer(monsterCO.getAttack()));
                fUserRole.setBaseDefence(new Integer(monsterCO.getDefence()));
                fUserRole.setBaseHealth(new Integer(monsterCO.getHealth()));
                fUserRole.setPos(i);
                fUser.getUserRoleList().add(fUserRole);
            }
        }

        return fUser;
    }


    /**
     * create FUserRole Object .
     * init object.
     * set Attributes.
     * set base Attack Defence Health
     *
     * @param uid
     * @param urId
     * @return
     * @throws RequestUnexpectedException
     */
    private FUserRole createFUserRole(int uid, int urId, int pos) throws RequestUnexpectedException, RequestFatalException {
        FUserRole fUserRole = new FUserRole();
        fUserRole.setPos(pos);
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urId);

        // get UserRole's attributes from gem
        int weaponId = userRoleCO.getWeapon();
        int armorId = userRoleCO.getArmor();
        int accessoryId = userRoleCO.getAccessory();

        int roleId = userRoleCO.getRoleId();
        RoleCO roleCO = roleConfigFetcher.get(roleId + "");

        fUserRole.setAttack(roleCO.getAttack());
        fUserRole.setDefence(roleCO.getDefence());
        fUserRole.setHealth(roleCO.getHealth());

        int equipAttack = 0;
        int equipDefence = 0;
        int equipHealth = 0;
        if (weaponId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO weaponCO = userEquipHelper.getUserEquip(uid, weaponId);
            if (weaponCO.getGemId() != SlgConstants.Equip.DEFAULT_GEM_ID) {
                int gemId = weaponCO.getGemId();
                fUserRole.getBuffList().add(BuffGenerator.generator(FightConstants.Buff.ADD_ATTACK_RATE,
                        gemConfigFetcher.get(gemId + "").getValue() + ""));
            }
            equipAttack = userEquipHelper.getWeaponAttack(uid, userRoleCO.getId(), weaponCO.getId());

        }
        if (armorId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO armorCO = userEquipHelper.getUserEquip(uid, armorId);
            if (armorCO.getGemId() != SlgConstants.Equip.DEFAULT_GEM_ID) {
                int gemId = armorCO.getGemId();
                fUserRole.getBuffList().add(BuffGenerator.generator(FightConstants.Buff.ADD_DEFENCE_RATE,
                        gemConfigFetcher.get(gemId + "").getValue() + ""));
            }
            equipDefence = userEquipHelper.getArmorDefence(uid, userRoleCO.getId(), armorCO.getId());

        }

        if (accessoryId != SlgConstants.Equip.NO_EQUIP_ID) {
            UserEquipCO accessoryCO = userEquipHelper.getUserEquip(uid, accessoryId);
            if (accessoryCO.getGemId() != SlgConstants.Equip.DEFAULT_GEM_ID) {
                int gemId = accessoryCO.getGemId();
                fUserRole.getBuffList().add(BuffGenerator.generator(FightConstants.Buff.ADD_HEALTH_RATE,
                        gemConfigFetcher.get(gemId + "").getValue() + ""));
            }
            equipHealth = userEquipHelper.getAccessoryHealth(uid, userRoleCO.getId(), accessoryCO.getId());
        }

        fUserRole.setUserRoleCO(userRoleCO);
        fUserRole.setUrId(urId);


        int userAttack = userRoleHelper.getAttack(uid, urId);
        int userDefence = userRoleHelper.getDefence(uid, urId);
        int userHealth = userRoleHelper.getHealth(uid, urId);


        int finalAttack = userAttack + equipAttack;
        int finalDefence = userDefence + equipDefence;
        int finalHealth = userHealth + equipHealth;

        fUserRole.setBaseAttack(finalAttack);
        fUserRole.setBaseDefence(finalDefence);
        fUserRole.setBaseHealth(finalHealth);


        return fUserRole;
    }


    /**
     * create FUserRole Object .
     * init object.
     * set Attributes.
     * set base Attack Defence Health
     *
     * @param uid
     * @param urId
     * @return
     * @throws RequestUnexpectedException
     */
    private FUserRole createLeader(int uid, int urId) throws RequestUnexpectedException, RequestFatalException {
        FUserRole fUserRole = new FUserRole();
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urId);

        return fUserRole;
    }

}
