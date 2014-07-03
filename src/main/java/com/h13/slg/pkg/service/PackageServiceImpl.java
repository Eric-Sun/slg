package com.h13.slg.pkg.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.GemCO;
import com.h13.slg.config.co.MaterialCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.GemConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.equip.vo.EquipInfoVO;
import com.h13.slg.equip.vo.GemInfoVO;
import com.h13.slg.equip.vo.MaterialInfoVO;
import com.h13.slg.equip.vo.UserEquipVO;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.helper.UserPackageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-19
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Service("PackageService")
public class PackageServiceImpl implements PackageService {


    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    GemConfigFetcher gemConfigFetcher;
    @Autowired
    MaterialConfigFetcher materialConfigFetcher;

    @Override
    public SlgData get(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();

        UserPackageCO userPackageCO = userPackageHelper.get(uid);
        // equip
        List<Integer> ueIdSet = userPackageCO.getEquip();
        List<UserEquipVO> userEquipList = Lists.newArrayList();
        for (Integer ueId : ueIdSet) {
            UserEquipCO userEquipCO = userEquipHelper.getUserEquip(uid, ueId);
            UserEquipVO userEquipVO = new UserEquipVO();
            try {
                BeanUtils.copyProperties(userEquipVO, userEquipCO);
            } catch (Exception e) {
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "", e);
            }

            EquipInfoVO equipInfoVO = userEquipHelper.getEquipInfo(userEquipCO);

            userEquipVO.setEquipInfo(equipInfoVO);
            userEquipList.add(userEquipVO);
        }

        //gem
        Map<String, Integer> gemMap = userPackageCO.getGem();
        List<GemInfoVO> gemList = Lists.newLinkedList();
        // 获取gem相关信息
        Set<String> gemIdSet = gemMap.keySet();
        for (String gemId : gemIdSet) {
            GemCO gemCO = gemConfigFetcher.get(gemId);
            GemInfoVO gemInfoVO = new GemInfoVO();
            gemInfoVO.setName(gemCO.getName());
            gemInfoVO.setId(gemCO.getId());
            gemList.add(gemInfoVO);
        }

        // material
        Map<String, Integer> materialMap = userPackageCO.getMaterial();
        List<MaterialInfoVO> materialList = Lists.newLinkedList();

        Set<String> materialIdSet = materialMap.keySet();
        for (String materialId : materialIdSet) {
            MaterialCO materialCO = materialConfigFetcher.get(materialId);
            MaterialInfoVO materialInfoVO = new MaterialInfoVO();
            materialInfoVO.setName(materialCO.getName());
            materialInfoVO.setId(materialCO.getId());
            materialList.add(materialInfoVO) ;
        }


        return SlgData.getData().add("equip", userEquipList)
                .add("gemMap", gemMap)
                .add("gemList", gemList)
                .add("materialMap", materialMap)
                .add("materialList", materialList);
    }
}
