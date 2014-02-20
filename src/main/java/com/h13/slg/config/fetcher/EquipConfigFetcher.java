package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.EquipCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.EquipCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EquipConfigFetcher implements BasicFetcher<EquipCO> {

    @Autowired
    EquipCache cache;

    @Override
    public EquipCO get(String key) {
        return cache.get(key);
    }

}
