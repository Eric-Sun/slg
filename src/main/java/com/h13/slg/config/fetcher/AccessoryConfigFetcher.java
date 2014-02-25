package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.AccessoryCache;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.ArmorCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AccessoryConfigFetcher implements BasicFetcher<AccessoryCO> {

    @Autowired
    AccessoryCache cache;

    @Override
    public AccessoryCO get(String key) {
        return cache.get(key);
    }

}
