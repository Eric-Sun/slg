package com.h13.slg.equip.service;

import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.equip.vo.EquipInfoVO;
import com.h13.slg.equip.vo.UserEquipVO;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.RoleRequestKeyConstants;
import com.h13.slg.role.RoleResponseKeyConstants;
import com.h13.slg.equip.vo.EquipMakeVO;
import com.h13.slg.equip.vo.EquipStrengthenVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Service("EquipService")
public class EquipServiceImpl implements EquipService {

    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    StrengthenConfigFetcher strengthenConfigFetcher;
    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    MaterialConfigFetcher materialConfigFetcher;

    @Override
    public SlgData strengthen(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        long ueId = new Long(request.getArgs().get(RoleRequestKeyConstants.UEID).toString());

        EquipStrengthenVO vo = userEquipHelper.strengthen(uid, ueId);
        return SlgData.getData().add(RoleResponseKeyConstants.STRENGTH, vo.getStrength())
                .add(RoleResponseKeyConstants.GOLD, vo.getGold());
    }


    @Override
    public SlgData make(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        long ueId = new Long(request.getArgs().get(RoleRequestKeyConstants.UEID).toString());
//        int lucky = new Integer(request.getArgs().get(RoleRequestKeyConstants.LUCKY).toString());
        int lucky = 0;
        EquipMakeVO vo = userEquipHelper.make(uid, lucky, ueId);

        return SlgData.getData().add(RoleResponseKeyConstants.LEVEL, vo.getLevel())
                .add(RoleResponseKeyConstants.MATERIAL, vo.getMap());
    }

    @Override
    public SlgData equipList(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        String type = request.getArgs().get("type").toString();
        List<UserEquipCO> userEquipList = userEquipHelper.equipList(uid, type);
        for (UserEquipCO userEquipCO : userEquipList) {
            EquipCO equipCO = equipConfigFetcher.get(userEquipCO.getLevel() + "");
            if (type.equals(EquipConstants.EquipType.ARMOR)) {
                userEquipCO.setEquipName(equipCO.getArmorName());
            } else if (type.equals(EquipConstants.EquipType.ACCESSORY)) {
                userEquipCO.setEquipName(equipCO.getAccessoryName());
            } else {
                userEquipCO.setEquipName(equipCO.getWeaponName());
            }
        }
        return SlgData.getData().add("equipList", userEquipList);
    }

    @Override
    public SlgData equip(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        long ueid = new Long(request.getArgs().get("ueid").toString());

        UserEquipCO userEquipCO = userEquipHelper.getUserEquip(uid, ueid);
        EquipCO equipCO = equipConfigFetcher.get(userEquipCO.getLevel() + "");
        if (userEquipCO.getType().equals(EquipConstants.EquipType.ARMOR)) {
            userEquipCO.setEquipName(equipCO.getArmorName());
        } else if (userEquipCO.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            userEquipCO.setEquipName(equipCO.getAccessoryName());
        } else {
            userEquipCO.setEquipName(equipCO.getWeaponName());
        }

        // 获取金币相关，金币用于升级
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        StrengthenCO strengthenCO = strengthenConfigFetcher.get(userEquipCO.getStrength() + "");

        // 需要材料的类型和数量

        UserEquipVO userEquipVO = new UserEquipVO();
        try {
            BeanUtils.copyProperties(userEquipVO, userEquipCO);
        } catch (Exception e) {
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "", e);
        }
        EquipInfoVO equipInfoVO = userEquipHelper.getEquipInfo(userEquipCO);
        userEquipVO.setEquipInfo(equipInfoVO);
        userEquipVO.setCurGold(userStatusCO.getGold());
        userEquipVO.setNeedGold(strengthenCO.getWeaponCost());

        if (userEquipCO.getType().equals(EquipConstants.EquipType.WEAPON)) {
            userEquipVO.setMaterialType1Id(equipCO.getWeaponMaterialType1());
            userEquipVO.setMaterialType2Id(equipCO.getWeaponMaterialType2());
            userEquipVO.setMaterialType1NeedCount(equipCO.getWeaponMaterial1());
            userEquipVO.setMaterialType2NeedCount(equipCO.getWeaponMaterial2());
        } else if (userEquipCO.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            userEquipVO.setMaterialType1Id(equipCO.getAccessoryMaterialType1());
            userEquipVO.setMaterialType2Id(equipCO.getAccessoryMaterialType2());
            userEquipVO.setMaterialType1NeedCount(equipCO.getAccessoryMaterial1());
            userEquipVO.setMaterialType2NeedCount(equipCO.getAccessoryMaterial2());
        } else if (userEquipCO.getType().equals(EquipConstants.EquipType.ARMOR)) {
            userEquipVO.setMaterialType1Id(equipCO.getArmorMaterialType1());
            userEquipVO.setMaterialType2Id(equipCO.getArmorMaterialType2());
            userEquipVO.setMaterialType1NeedCount(equipCO.getArmorMaterial1());
            userEquipVO.setMaterialType2NeedCount(equipCO.getArmorMaterial2());
        }

        userEquipVO.setMaterialType1Name(materialConfigFetcher.get(userEquipVO.getMaterialType1Id() + "").getName());
        userEquipVO.setMaterialType2Name(materialConfigFetcher.get(userEquipVO.getMaterialType2Id() + "").getName());


        userEquipVO.setMaterialType1CurrentCount(
                userPackageHelper.getMaterialCount(uid, userEquipVO.getMaterialType1Id()));
        userEquipVO.setMaterialType2CurrentCount(
                userPackageHelper.getMaterialCount(uid, userEquipVO.getMaterialType2Id()));

        return SlgData.getData().add("userEquip", userEquipVO);
    }
}
