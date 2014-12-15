package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.AccessoryCache;
import com.h13.slg.config.cache.GemCache;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.GemCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class GemConfigFetcher implements BasicFetcher<GemCO> {

    @Autowired
    GemCache cache;

    @Override
    public GemCO get(String key) {
        return cache.get(key);
    }

}
