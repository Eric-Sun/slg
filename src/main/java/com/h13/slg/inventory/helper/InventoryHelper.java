package com.h13.slg.inventory.helper;

import com.h13.slg.config.cache.ShopCache;
import com.h13.slg.config.co.ShopCO;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.inventory.InventoryConstants;
import com.h13.slg.pkg.PackageConstants;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.inventory.vo.InventoryBuyVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 商店相关的
 */
@Service
public class InventoryHelper {

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
    public InventoryBuyVO buy(long uid, long shopId, int num) throws RequestErrorException {
        ShopCO shopCO = shopCache.get(shopId + "");
        String currency = shopCO.getCurrency();
        int price = shopCO.getPrice();

        String type = shopCO.getCategory2();
        int id = new Integer(shopCO.getCategory3());
        int allPrice = num * price;

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        // 检查是否金钱等足够
        if (currency.equals(InventoryConstants.CURRENCY.CASH)) {
            int currentCash = userStatusCO.getCash();
            if (currentCash < allPrice) {
                throw new RequestErrorException(ErrorCodeConstants.Role.RESOURCE_IS_NOT_ENOUGH, "");
            }
            userStatusCO.setCash(currentCash - allPrice);
        } else if (currency.equals(InventoryConstants.CURRENCY.HONOR)) {
            int currentHonor = userStatusCO.getHonor();
            if (currentHonor < allPrice) {
                throw new RequestErrorException(ErrorCodeConstants.Role.RESOURCE_IS_NOT_ENOUGH, "");
            }
            userStatusCO.setCash(currentHonor - allPrice);
        } else {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "buy use " + currency);
        }

        userStatusHelper.updateUserStatus(userStatusCO);

        // 添加到背包中
        if (!type.equals(PackageConstants.PACKAGE.GEM_SPACE) ||
                !type.equals(PackageConstants.PACKAGE.MATERIAL_SPACE)) {
            type = PackageConstants.PACKAGE.EQUIP_SPACE;
        }

        if (type.equals(PackageConstants.PACKAGE.MATERIAL_SPACE)) {
            userPackageHelper.addMaterialItem(uid, id, num);
        } else if (type.equals(PackageConstants.PACKAGE.GEM_SPACE)) {
            userPackageHelper.addGemItem(uid, id, num);
        } else if (type.equals(PackageConstants.PACKAGE.ROLE_CODE_SPACE)) {
            userPackageHelper.addRoleCodeItem(uid, id, num);
        } else {
            for (int i = 0; i < num; i++) {
                userEquipHelper.getANewUserEquip(uid,id, type);
            }
        }


        InventoryBuyVO vo = new InventoryBuyVO();
        vo.setCostNum(allPrice * -1);
        vo.setCostType(currency);
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> l1 = new ArrayList<Object>();
        l1.add(type);
        l1.add(id);
        l1.add(num);
        list.add(l1);
        vo.setAwards(list);
        return vo;
    }

}
