package com.h13.slg.equip.service;

import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.role.RoleRequestKeyConstants;
import com.h13.slg.role.RoleResponseKeyConstants;
import com.h13.slg.equip.vo.EquipMakeVO;
import com.h13.slg.equip.vo.EquipStrengthenVO;
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
        int lucky = new Integer(request.getArgs().get(RoleRequestKeyConstants.LUCKY).toString());
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

        return SlgData.getData().add("userEquip", userEquipCO);
    }
}
