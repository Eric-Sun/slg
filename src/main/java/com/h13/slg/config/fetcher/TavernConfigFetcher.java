package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.TavernCache;
import com.h13.slg.config.co.TavernConfigCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TavernConfigFetcher implements BasicFetcher<TavernConfigCO> {

    @Autowired
    TavernCache cache;

    @Override
    public TavernConfigCO get(String key) {
        return cache.get(key);
    }


}
