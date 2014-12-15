package com.h13.slg.equip.service;

import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Service("EquipService")
public class EquipService {

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

    public SlgData strengthen(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {
        int uid = request.getUid();
        int ueId = new Integer(request.getArgs().get("ueid") + "");

        int nextStrengthen = userEquipHelper.strengthen(uid, ueId);
        return SlgData.getData().add("strengthen", nextStrengthen);
    }


    public SlgData make(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {

        int uid = request.getUid();
        int ueId = new Integer(request.getArgs().get("ueid").toString());
        int nextLevel = userEquipHelper.make(uid, ueId);

        return SlgData.getData().add("level", nextLevel);
    }


    public SlgData setGem(SlgRequestDTO requestDTO) throws RequestUnexpectedException {

        int uid = requestDTO.getUid();
        int gemId = new Integer(requestDTO.getArgs().get("gemId") + "");
        int ueId = new Integer(requestDTO.getArgs().get("ueId") + "");


        boolean have = userPackageHelper.checkGemEnough(uid, gemId, 1);
        if (!have) {
            throw new RequestUnexpectedException(CodeConstants.Gem.DONT_HAVE_ENOUTH_GEM,
                    String.format("uid=%s,gemId=%s", uid, gemId));
        }

        userPackageHelper.subtractGem(uid, gemId, 1);

        UserEquipCO userEquipCO = userEquipHelper.getUserEquip(uid, ueId);

        if (userEquipCO.getGemId() != SlgConstants.Equip.DEFAULT_GEM_ID) {
            // 已经有宝石了
            throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_GEM_IS_SET,
                    String.format("uid=%s,ueid=%s,gemId=%s", uid, ueId, gemId));
        }

        userEquipCO.setGemId(gemId);
        userEquipHelper.updateUserEquip(userEquipCO);

        return SlgData.getData();
    }


    public SlgData unsetGem(SlgRequestDTO requestDTO) throws RequestUnexpectedException {
        int uid = requestDTO.getUid();
        int ueId = new Integer(requestDTO.getArgs().get("ueId") + "");

        UserEquipCO userEquipCO = userEquipHelper.getUserEquip(uid, ueId);

        if (userEquipCO.getGemId() == SlgConstants.Equip.DEFAULT_GEM_ID) {
            // 没有宝石
            throw new RequestUnexpectedException(CodeConstants.Role.EQUIP_GEM_IS_NOT_SET,
                    String.format("uid=%s,ueid=%s", uid, ueId));
        }

        int gemId = userEquipCO.getGemId();

        userPackageHelper.addGem(uid, gemId, 1);
        userEquipCO.setGemId(SlgConstants.Equip.DEFAULT_GEM_ID);

        userEquipHelper.updateUserEquip(userEquipCO);

        return SlgData.getData();

    }

}
