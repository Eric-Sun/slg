package com.h13.slg.equip.service;

import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.equip.helper.UserEquipHelper;
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
    public SlgData strengthen(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {
        int uid = request.getUid();
        int ueId = new Integer(request.getArgs().get("ueid") + "");

        int nextStrengthen = userEquipHelper.strengthen(uid, ueId);
        return SlgData.getData().add("strengthen", nextStrengthen);
    }


    @Override
    public SlgData make(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {

        int uid = request.getUid();
        int ueId = new Integer(request.getArgs().get("ueid").toString());
        int nextLevel = userEquipHelper.make(uid, ueId);

        return SlgData.getData().add("level", nextLevel);
    }


}
