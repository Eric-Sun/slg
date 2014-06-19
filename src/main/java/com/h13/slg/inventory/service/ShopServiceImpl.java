package com.h13.slg.inventory.service;

import com.google.common.collect.Maps;
import com.h13.slg.config.co.ShopCO;
import com.h13.slg.config.fetcher.ShopConfigFetcher;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public SlgData shopList(SlgRequestDTO request) {
        List<ShopCO> list = shopConfigFetcher.getAll();

        // inventory : material, gem    equip : armor accessory,weapon
        return SlgData.getData().add("shopList", list);
    }
}

