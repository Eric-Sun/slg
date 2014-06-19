package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.ShopCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ShopCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ShopConfigFetcher implements BasicFetcher<ShopCO> {

    @Autowired
    ShopCache cache;

    @Override
    public ShopCO get(String key) {
        return cache.get(key);
    }

    public List<ShopCO> getAll() {
        return cache.getAll();
    }
}
