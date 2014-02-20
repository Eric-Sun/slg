package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class LevelConfigFetcher implements BasicFetcher<LevelCO> {

    @Autowired
    LevelCache cache;

    @Override
    public LevelCO get(String key) {
        return cache.get(key);
    }

}
