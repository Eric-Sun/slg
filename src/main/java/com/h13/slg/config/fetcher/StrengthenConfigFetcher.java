package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.AccessoryCache;
import com.h13.slg.config.cache.StrengthenCache;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.StrengthenCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StrengthenConfigFetcher implements BasicFetcher<StrengthenCO> {

    @Autowired
    StrengthenCache cache;

    @Override
    public StrengthenCO get(String key) {
        return cache.get(key);
    }

}
