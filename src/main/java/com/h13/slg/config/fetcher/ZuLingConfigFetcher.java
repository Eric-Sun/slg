package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.AccessoryCache;
import com.h13.slg.config.cache.ZuLingCache;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.ZuLingCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ZuLingConfigFetcher implements BasicFetcher<ZuLingCO> {

    @Autowired
    ZuLingCache cache;

    @Override
    public ZuLingCO get(String key) {
        return cache.get(key);
    }


}
