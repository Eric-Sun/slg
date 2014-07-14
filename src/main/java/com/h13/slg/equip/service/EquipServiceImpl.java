package com.h13.slg.equip.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.equip.vo.EquipMakeInfoVO;
import com.h13.slg.equip.vo.EquipStrengthenInfoVO;
import com.h13.slg.equip.vo.UserEquipVO;
import com.h13.slg.pkg.helper.UserPackageHelper;
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
        long ueId = new Long(request.getArgs().get("ueid") + "");

        int nextStrengthen = userEquipHelper.strengthen(uid, ueId);
        return SlgData.getData().add("strengthen", nextStrengthen);
    }


    @Override
    public SlgData make(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        long ueId = new Long(request.getArgs().get("ueid").toString());
        int lucky = 0;
        int nextLevel = userEquipHelper.make(uid, lucky, ueId);

        return SlgData.getData().add("level", nextLevel);
    }

    @Override
    public SlgData noUsedEquipList(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        String type = request.getArgs().get("type").toString();
        List<UserEquipCO> userEquipList = userEquipHelper.noUsedEquipList(uid, type);
        List<UserEquipVO> voList = Lists.newLinkedList();

        for (UserEquipCO userEquipCO : userEquipList) {
            UserEquipVO vo = new UserEquipVO();
            SlgBeanUtils.copyProperties(vo, userEquipCO);
            voList.add(vo);
        }
        return SlgData.getData().add("equipList", voList);
    }

    @Override
    public SlgData equip(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        long ueid = new Long(request.getArgs().get("ueid").toString());


        UserEquipVO userEquipVO = new UserEquipVO();
        UserEquipCO userEquipCO = userEquipHelper.getUserEquip(uid, ueid);
        EquipCO equipCO = equipConfigFetcher.get(userEquipCO.getLevel() + "");

        // 获取金币相关，金币用于升级
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        StrengthenCO strengthenCO = strengthenConfigFetcher.get(userEquipCO.getStrength() + "");
        try {
            BeanUtils.copyProperties(userEquipVO, userEquipCO);
        } catch (Exception e) {
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "", e);
        }
        EquipStrengthenInfoVO strengthenInfoVO = new EquipStrengthenInfoVO();
        strengthenInfoVO.setCurGold(userStatusCO.getGold());
        strengthenInfoVO.setNeedGold(strengthenCO.getWeaponCost());
        userEquipVO.setStrengthenInfo(strengthenInfoVO);

        // 获得升级相关的相关数据
        EquipMakeInfoVO makeInfo = new EquipMakeInfoVO();
        if (userEquipCO.getType().equals(EquipConstants.EquipType.WEAPON)) {
            makeInfo.setMaterialType1Id(equipCO.getWeaponMaterialType1());
            makeInfo.setMaterialType2Id(equipCO.getWeaponMaterialType2());
            makeInfo.setMaterialType1NeedCount(equipCO.getWeaponMaterial1());
            makeInfo.setMaterialType2NeedCount(equipCO.getWeaponMaterial2());
        } else if (userEquipCO.getType().equals(EquipConstants.EquipType.ACCESSORY)) {
            makeInfo.setMaterialType1Id(equipCO.getAccessoryMaterialType1());
            makeInfo.setMaterialType2Id(equipCO.getAccessoryMaterialType2());
            makeInfo.setMaterialType1NeedCount(equipCO.getAccessoryMaterial1());
            makeInfo.setMaterialType2NeedCount(equipCO.getAccessoryMaterial2());
        } else if (userEquipCO.getType().equals(EquipConstants.EquipType.ARMOR)) {
            makeInfo.setMaterialType1Id(equipCO.getArmorMaterialType1());
            makeInfo.setMaterialType2Id(equipCO.getArmorMaterialType2());
            makeInfo.setMaterialType1NeedCount(equipCO.getArmorMaterial1());
            makeInfo.setMaterialType2NeedCount(equipCO.getArmorMaterial2());
        }

        makeInfo.setMaterialType1Name(materialConfigFetcher.get(makeInfo.getMaterialType1Id() + "").getName());
        makeInfo.setMaterialType2Name(materialConfigFetcher.get(makeInfo.getMaterialType2Id() + "").getName());


        makeInfo.setMaterialType1CurrentCount(
                userPackageHelper.getMaterialCount(uid, makeInfo.getMaterialType1Id()));
        makeInfo.setMaterialType2CurrentCount(
                userPackageHelper.getMaterialCount(uid, makeInfo.getMaterialType2Id()));
        userEquipVO.setMakeInfo(makeInfo);
        return SlgData.getData().add("userEquip", userEquipVO);
    }
}
