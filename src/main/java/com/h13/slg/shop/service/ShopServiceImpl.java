package com.h13.slg.shop.service;

import com.h13.slg.config.co.ShopCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.config.fetcher.MaterialConfigFetcher;
import com.h13.slg.config.fetcher.ShopConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.shop.helper.ShopHelper;
import com.h13.slg.shop.vo.ShopBuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    ShopHelper shopHelper;

    @Override
    public SlgData shopList(SlgRequestDTO request) {
        List<ShopCO> list = shopConfigFetcher.getAll();
        for (ShopCO shop : list) {
            String id = shop.getCategory3();
            if (shop.getCategory2().equals("material")) {
                shop.setName(materialConfigFetcher.get(id).getName());
            }  else if (shop.getCategory2().equals("armor")) {
                shop.setName(equipConfigFetcher.get(id).getArmorName());
            } else if (shop.getCategory2().equals("accessory")) {
                shop.setName(equipConfigFetcher.get(id).getAccessoryName());
            } else {
                shop.setName(equipConfigFetcher.get(id).getWeaponName());
            }
        }

        // shop : material, gem    equip : armor accessory,weapon
        return SlgData.getData().add("shopList", list);
    }

    @Override
    public SlgData buy(SlgRequestDTO request) throws RequestErrorException {
        // "num":10,"id":41
        int uid = request.getUid();
        int num = new Integer(request.getArgs().get("num").toString());
        int id = new Integer(request.getArgs().get("id").toString());

        ShopBuyVO shopBuyVO = shopHelper.buy(uid, id,num );
        //{"cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]}
        return SlgData.getData().add("cost_type", shopBuyVO.getCostType())
                .add("cost_num", shopBuyVO.getCostNum())
                .add("award", shopBuyVO.getAwards());
    }
}

