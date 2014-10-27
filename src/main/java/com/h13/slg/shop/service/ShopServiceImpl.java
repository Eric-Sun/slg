package com.h13.slg.shop.service;

import com.h13.slg.config.cache.ShopCache;
import com.h13.slg.config.co.ShopCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.ShopConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.shop.vo.ShopBuyMaterialVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import com.h13.slg.user.vo.UserEquipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-18
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
@Service("ShopService")
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopConfigFetcher shopConfigFetcher;
    @Autowired
    MaterialConfigFetcher materialConfigFetcher;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;
    @Autowired
    ShopCache shopCache;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    UserEquipHelper userEquipHelper;

    @Override
    public SlgData buy(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {
        // "num":10,"id":41
        int uid = request.getUid();
        int num = new Integer(request.getArgs().get("num").toString());
        int shopId = new Integer(request.getArgs().get("id").toString());


        Map<String, Object> awards = new HashMap<String, Object>();
        ShopCO shopCO = shopCache.get(shopId + "");
        String currency = shopCO.getCurrency();
        int price = shopCO.getPrice();

        String type = shopCO.getCategory2();
        int id = new Integer(shopCO.getCategory3());
        int allPrice = num * price;

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        // 检查是否金钱等足够
        if (currency.equals(SlgConstants.Shop.CURRENCY.CASH)) {
            int currentCash = userStatusCO.getCash();
            if (currentCash < allPrice) {
                throw new RequestUnexpectedException(CodeConstants.Role.RESOURCE_IS_NOT_ENOUGH, "");
            }
            userStatusCO.setCash(currentCash - allPrice);
        } else {
            throw new RequestUnexpectedException(CodeConstants.SYSTEM.COMMON_ERROR, "buy use " + currency);
        }

        userStatusHelper.updateUserStatus(userStatusCO);

        // 添加到背包中
        if (type.equals(SlgConstants.PackageConstants.PACKAGE.MATERIAL_SPACE)) {
            userPackageHelper.addMaterialItem(uid, id, num);
            ShopBuyMaterialVO itemVO = new ShopBuyMaterialVO();
            itemVO.setId(id);
            itemVO.setCount(num);
            awards.put(SlgConstants.Shop.Type.MATERIAL, itemVO);
        } else {
            List<UserEquipVO> voList = new LinkedList<UserEquipVO>();
            for (int i = 0; i < num; i++) {
                UserEquipCO co = userEquipHelper.addUserEquip(uid, type);
                UserEquipVO ueVO = new UserEquipVO();
                SlgBeanUtils.copyProperties(ueVO, co);
                voList.add(ueVO);
            }
            awards.put(SlgConstants.Shop.Type.EQUIP, voList);
        }

        int cost = allPrice * -1;
        //{"cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]}
        return SlgData.getData().add("cost_type", currency)
                .add("cost_num", cost)
                .add("award", awards);
    }
}

