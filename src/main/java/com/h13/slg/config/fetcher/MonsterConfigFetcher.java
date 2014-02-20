package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.MonsterCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.MonsterCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class MonsterConfigFetcher implements BasicFetcher<MonsterCO> {

    @Autowired
    MonsterCache cache;

    @Override
    public MonsterCO get(String key) {
        return cache.get(key);
    }

}
