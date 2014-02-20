package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.MaterialCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.MaterialCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MaterialConfigFetcher implements BasicFetcher<MaterialCO> {

    @Autowired
    MaterialCache cache;

    @Override
    public MaterialCO get(String key) {
        return cache.get(key);
    }

}
