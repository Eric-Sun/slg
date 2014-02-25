package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.WeaponCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.WeaponCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class WeaponConfigFetcher implements BasicFetcher<WeaponCO> {

    @Autowired
    WeaponCache cache;

    @Override
    public WeaponCO get(String key) {
        return cache.get(key);
    }

}
