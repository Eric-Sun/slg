package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.GlobalCache;
import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.GlobalCO;
import com.h13.slg.config.co.LevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class GlobalConfigFetcher implements BasicFetcher<GlobalCO> {

    @Autowired
    GlobalCache cache;

    @Override
    public GlobalCO get(String key) {
        return cache.get(key);
    }

    public int getIntValue(String key) {
        return Integer.valueOf(cache.get(key).getValue());
    }

    public String getStringValue(String key) {
        return cache.get(key).getValue();
    }

}
