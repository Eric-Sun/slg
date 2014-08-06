package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.co.ArmorCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ArmorConfigFetcher implements BasicFetcher<ArmorCO> {

    @Autowired
    ArmorCache cache;

    @Override
    public ArmorCO get(String key) {
        return cache.get(key);
    }

}
