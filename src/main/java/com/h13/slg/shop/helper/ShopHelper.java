package com.h13.slg.shop.helper;

import com.h13.slg.config.cache.ShopCache;
import com.h13.slg.config.co.ShopCO;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.shop.vo.ShopBuyMaterialVO;
import com.h13.slg.shop.vo.ShopBuyVO;
import com.h13.slg.pkg.helper.UserPackageHelper;
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
 * 商店相关的
 */
@Service
public class ShopHelper {

    @Autowired
    ShopCache shopCache;

    @Autowired
    UserStatusHelper userStatusHelper;

    @Autowired
    UserPackageHelper userPackageHelper;

    @Autowired
    UserEquipHelper userEquipHelper;

    /**
     * 在商店购买商品
     *
     * @param uid
     * @param shopId
     * @param num
     * @return
     * @throws RequestErrorException
     */
    public ShopBuyVO buy(int uid, long shopId, int num) throws RequestErrorException {

        ShopBuyVO vo = new ShopBuyVO();
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
                throw new RequestErrorException(CodeConstants.Role.RESOURCE_IS_NOT_ENOUGH, "");
            }
            userStatusCO.setCash(currentCash - allPrice);
        } else {
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "buy use " + currency);
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
                UserEquipCO co = userEquipHelper.addNewUserEquip(uid, id, type);
                UserEquipVO ueVO = new UserEquipVO();
                SlgBeanUtils.copyProperties(ueVO, co);
                voList.add(ueVO);
            }
            awards.put(SlgConstants.Shop.Type.EQUIP, voList);
        }

        vo.setCostNum(allPrice * -1);
        vo.setCostType(currency);
        vo.setAwards(awards);
        return vo;
    }

}
