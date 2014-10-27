package com.h13.slg.equip.helper;

import com.google.common.collect.Lists;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.equip.cache.UserEquipCache;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.equip.dao.UserEquipDAO;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.FightForceHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-25
 * Time: 下午7:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserEquipHelper {

    @Autowired
    UserEquipDAO userEquipDAO;
    @Autowired
    StrengthenConfigFetcher strengthenConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;
    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    FightForceHelper fightForceHelper;

    @Autowired
    UserEquipCache userEquipCache;

    /**
     * 获取用户装备
     * <p>
     * 从数据库中读取
     * </p>
     *
     * @param ueId 用户装备id
     * @return
     * @throws RequestUnexpectedException
     */
    public UserEquipCO getUserEquip(int uid, int ueId) throws RequestUnexpectedException {
        UserEquipCO userEquipCO = userEquipCache.get(ueId);
        if (userEquipCO == null) {
            userEquipCO = userEquipDAO.get(uid, ueId);
            userEquipCache.set(userEquipCO);
            return userEquipCO;
        } else {
            if (userEquipCO.getUid() == uid) {
                return userEquipCO;
            } else {
                throw new RequestUnexpectedException(CodeConstants.User.UID_EQUIPID_NOT_MATCHED,
                        String.format("uid=%s,ueid=%s not match", uid, ueId));
            }
        }
    }

    /**
     * 更新用户装备，并且更新cache
     *
     * @param ue
     */
    public void updateUserEquip(UserEquipCO ue) {
        userEquipDAO.update(ue.getId(), ue.getLevel(), ue.getStrength(),
                ue.getUrid(), ue.getName());
        userEquipCache.set(ue);
    }


    /**
     * 获得一个新的装备，从商店通过军功购买，或者通过程序获得
     *
     * @param uid
     * @param type EquipConstants.EquipType
     */
    public UserEquipCO addUserEquip(int uid, String type) {

        String name = getEquipNameFromConfig(type, SlgConstants.Equip.USER_EQUIP_DEFAULT_LEVEL);
        UserEquipCO userEquipCO = new UserEquipCO();
        userEquipCO.setLevel(SlgConstants.Equip.DEFAULT_LEVEL);
        userEquipCO.setType(type);
        userEquipCO.setUid(uid);
        userEquipCO.setName(name);
        userEquipCO.setUrid(SlgConstants.Role.NO_ROLE);
        userEquipCO.setStrength(SlgConstants.Equip.DEFAULT_STRENGTHEN);
        int ueid = userEquipDAO.insert(
                userEquipCO.getUid(),
                userEquipCO.getType(),
                userEquipCO.getLevel(),
                userEquipCO.getStrength(),
                userEquipCO.getUrid(),
                userEquipCO.getName());
        userEquipCO.setId(ueid);
        userEquipCache.set(userEquipCO);

        userPackageHelper.addEquipItem(uid, ueid);

        return userEquipCO;
    }

    /**
     * 通过type和level，读取配置文件，获得对应的name
     *
     * @param type
     * @param level
     * @return
     */
    public String getEquipNameFromConfig(String type, int level) {
        EquipCO equipCO = equipConfigFetcher.get(level + "");

        if (type.equals(SlgConstants.Equip.EquipType.WEAPON)) {
            return equipCO.getWeaponName();
        } else if (type.equals(SlgConstants.Equip.EquipType.ACCESSORY)) {
            return equipCO.getAccessoryName();
        } else {
            return equipCO.getArmorName();
        }
    }

    /**
     * 获得用户的所有装备，包括已经穿上的或者未穿上的
     *
     * @param uid
     * @return
     */
    public List<UserEquipCO> getUserEquips(int uid) {
        List<UserEquipCO> list = userEquipDAO.getUserEquips(uid);
        return list;
    }


    /**
     * 强化装备
     *
     * @param ueId
     * @return int 下一个等级是多少
     */
    public int strengthen(int uid, int ueId) throws RequestUnexpectedException, RequestFatalException {
        // 获得当前的强化等级
        UserEquipCO ue = getUserEquip(uid, ueId);
        int curStrength = ue.getStrength();
        String type = ue.getType();

        // 查看下一级强化需要的金币数目
        int nextStrength = curStrength + 1;
        StrengthenCO strengthenCO = strengthenConfigFetcher.get(nextStrength + "");
        if (strengthenCO == null)
            throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_STRENGTH_LEVEL_TO_TOP,
                    String.format("uid=%s,ueid=%s,nextStrength=%s", uid, ueId, nextStrength));
        int cost = 0;
        if (type.equals(SlgConstants.Equip.EquipType.WEAPON)) {
            cost = strengthenCO.getWeaponCost();
        } else if (type.equals(SlgConstants.Equip.EquipType.ARMOR)) {
            cost = strengthenCO.getArmorCost();
        } else {
            cost = strengthenCO.getAccessoryCost();
        }

        // 扣除对应的金币
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        if (userStatusCO.getGold() >= cost) {
            userStatusCO.setGold(userStatusCO.getGold() - cost);
        } else {
            throw new RequestUnexpectedException(CodeConstants.Role.RESOURCE_IS_NOT_ENOUGH,
                    String.format("gold is not enough. cost=%s, currentGold=%s", cost, userStatusCO.getGold()));
        }
        userStatusHelper.updateUserStatus(userStatusCO);

        // 增加强化等级
        ue.setStrength(nextStrength);
        updateUserEquip(ue);

        // 更新fightforce
        int urid = ue.getUrid();
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
        fightForceHelper.updateUserRoleFightForce(userRoleCO);
        return nextStrength;
    }


    /**
     * 合成装备 （升级）
     */
    public int make(int uid, int ueId) throws RequestFatalException, RequestUnexpectedException {
        // 获得当前的装备等级
        UserEquipCO ue = getUserEquip(uid, ueId);
        int curLevel = ue.getLevel();
        int nextLevel = curLevel + 1;
        // 获得升级需要的资源数量
        EquipCO equipCO = equipConfigFetcher.get(curLevel + "");
        int materialId1 = 0;
        int materialId2 = 0;
        int materialCount1 = 0;
        int materialCount2 = 0;
        String type = ue.getType();
        if (type.equals(SlgConstants.Equip.EquipType.WEAPON)) {
            materialId1 = equipCO.getWeaponMaterialType1();
            materialId2 = equipCO.getWeaponMaterialType2();
            materialCount1 = equipCO.getWeaponMaterial1();
            materialCount2 = equipCO.getWeaponMaterial2();
        } else if (type.equals(SlgConstants.Equip.EquipType.ARMOR)) {
            materialId1 = equipCO.getArmorMaterialType1();
            materialId2 = equipCO.getArmorMaterialType2();
            materialCount1 = equipCO.getArmorMaterial1();
            materialCount2 = equipCO.getArmorMaterial2();
        } else {
            materialId1 = equipCO.getAccessoryMaterialType1();
            materialId2 = equipCO.getAccessoryMaterialType2();
            materialCount1 = equipCO.getAccessoryMaterial1();
            materialCount2 = equipCO.getAccessoryMaterial2();
        }

        // 查看背包中是否有这些资源
        UserPackageCO userPackageCO = userPackageHelper.get(uid);
        int packageMCount1 = userPackageHelper.getMaterialCount(uid, materialId1);
        int packageMCount2 = userPackageHelper.getMaterialCount(uid, materialId2);

        if (packageMCount1 < materialCount1 || packageMCount2 < materialCount2) {
            // 资源不够
            throw new RequestUnexpectedException(CodeConstants.Role.RESOURCE_IS_NOT_ENOUGH,
                    String.format("packageMCount1=%s,materialCount1=%s  packageMCount2=%s,materialCount2=%s",
                            packageMCount1, materialCount1, packageMCount2, materialCount2));
        }
        Map d1 = userPackageCO.getMaterial();
        d1.put(materialId1 + "", packageMCount1 - materialCount1);
        d1.put(materialId2 + "", packageMCount2 - materialCount2);
        userPackageHelper.updateMaterial(userPackageCO);
        ue.setLevel(nextLevel);
        ue.setName(getEquipNameFromConfig(type, nextLevel));
        updateUserEquip(ue);

        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, ue.getUrid());
        fightForceHelper.updateUserRoleFightForce(userRoleCO);
        return nextLevel;
    }


    /**
     * 注册的时候用于给新用户添加一些基础装备
     * 3 weapon
     * 3 accessory
     * 3 armor
     *
     * @param uid
     */
    public List<UserEquipCO> addUserEquipForRegister(int uid) throws RequestFatalException {
        List<UserEquipCO> ueList = Lists.newLinkedList();
        for (int i = 0; i < 3; i++) {
            UserEquipCO ue1 = addUserEquip(uid, SlgConstants.Equip.EquipType.ACCESSORY);
            UserEquipCO ue2 = addUserEquip(uid, SlgConstants.Equip.EquipType.ARMOR);
            UserEquipCO ue3 = addUserEquip(uid, SlgConstants.Equip.EquipType.WEAPON);
            ueList.add(ue1);
            ueList.add(ue2);
            ueList.add(ue3);
        }
        return ueList;
    }

}
