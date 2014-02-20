package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.RolelevelCache;
import com.h13.slg.config.co.RoleLevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RoleLevelConfigFetcher implements BasicFetcher<RoleLevelCO> {

    @Autowired
    RolelevelCache cache;

    @Override
    public RoleLevelCO get(String key) {
        return cache.get(key);
    }

}
