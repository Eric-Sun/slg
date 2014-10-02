package com.h13.slg.equip.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.StrengthenConfigFetcher;
import com.h13.slg.core.*;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.equip.vo.EquipMakeInfoVO;
import com.h13.slg.equip.vo.EquipStrengthenInfoVO;
import com.h13.slg.user.vo.UserEquipVO;
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
        int nextLevel = userEquipHelper.make(uid, ueId);

        return SlgData.getData().add("level", nextLevel);
    }


}
