package com.h13.slg.equip.help;

import com.h13.slg.equip.EquipConstants;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.equip.dao.UserEquipDAO;
import com.h13.slg.role.vo.EquipMakeVO;
import com.h13.slg.role.vo.EquipStrengthenVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-25
 * Time: 下午7:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EquipHelper {

    @Autowired
    UserEquipDAO userEquipDAO;
    @Autowired
    StrengthenConfigFetcher strengthenConfigFetcher;

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;

    @Autowired
    UserPackageHelper userPackageHelper;


    /**
     * 获取userEquip
     *
     * @param ueId
     * @return
     */
    public UserEquipCO getUserEquip(long ueId) {
        return userEquipDAO.get(ueId);
    }

    public void updateUserEquip(UserEquipCO ue) {
        userEquipDAO.update(ue.getId(), ue.getLevel(), ue.getGems(), ue.getStrength(),
                ue.getFail(), ue.getRefine(), ue.getStar());
    }


    /**
     * 强化装备
     *
     * @param ueId
     * @return EquipStrengthenVO
     */
    public EquipStrengthenVO strengthen(long uid, long ueId) throws RequestErrorException {
        EquipStrengthenVO vo = new EquipStrengthenVO();
        // 获得当前的强化等级
        UserEquipCO ue = getUserEquip(ueId);
        int curStrength = ue.getStrength();
        int type = ue.getType();

        // 查看下一级强化需要的金币数目
        int nextStrength = curStrength++;
        StrengthenCO strengthenCO = strengthenConfigFetcher.get(nextStrength + "");
        if (strengthenCO == null)
            throw new RequestErrorException(ErrorCodeConstants.Role.EQUIP_STRENGTH_LEVEL_TO_TOP, "");
        int cost = 0;
        if (type == EquipConstants.EquipType.WEAPON) {
            cost = strengthenCO.getWeaponCost();
        } else if (type == EquipConstants.EquipType.ARMOR) {
            cost = strengthenCO.getArmorCost();
        } else {
            cost = strengthenCO.getAccessoryCost();
        }

        // 扣除对应的金币
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        if (userStatusCO.getGold() >= cost) {
            userStatusCO.setGold(userStatusCO.getGold() - cost);
        }
        userStatusHelper.updateUserStatus(userStatusCO);

        // 增加强化等级
        ue.setLevel(nextStrength);
        updateUserEquip(ue);

        vo.setGold(cost);
        vo.setStrength(nextStrength);
        return vo;
    }


    /**
     * 合成装备
     */
    public EquipMakeVO make(long uid, int lucky, long ueId) throws RequestErrorException {
        EquipMakeVO vo = new EquipMakeVO();
        // 获得当前的装备等级
        UserEquipCO ue = getUserEquip(ueId);
        int curLevel = ue.getLevel();
        int nextLevel = curLevel++;
        // 获得升级需要的资源数量
        EquipCO equipCO = equipConfigFetcher.get(curLevel + "");
        int materialId1 = 0;
        int materialId2 = 0;
        int materialCount1 = 0;
        int materialCount2 = 0;
        int type = ue.getType();
        if (type == EquipConstants.EquipType.WEAPON) {
            materialId1 = equipCO.getWeaponMaterialType1();
            materialId2 = equipCO.getWeaponMaterialType2();
            materialCount1 = equipCO.getWeaponMaterial1();
            materialCount2 = equipCO.getWeaponMaterial2();
        } else if (type == EquipConstants.EquipType.ARMOR) {
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
        int packageMCount1 = userPackageCO.getMaterial().get(materialId1 + "");
        int packageMCount2 = userPackageCO.getMaterial().get(materialId2 + "");

        if (packageMCount1 < materialCount1 || packageMCount2 < materialCount2) {
            // 资源不够
            throw new RequestErrorException(ErrorCodeConstants.Role.RESOURCE_IS_NOT_ENOUGH, "");
        }

        userPackageCO.getMaterial().put(materialId1 + "", packageMCount1 - materialCount1);
        userPackageCO.getMaterial().put(materialId2 + "", packageMCount2 - materialCount2);
        userPackageHelper.updateMaterial(userPackageCO);

        vo.setLevel(nextLevel);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(materialId1 + "", materialCount1 * -1);
        map.put(materialId2 + "", materialCount2 * -1);
        vo.setMap(map);
        return vo;
    }


}
