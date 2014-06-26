package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.BattleCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.BattleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class BattleConfigFetcher implements BasicFetcher<BattleCO> {

    @Autowired
    BattleCache cache;

    @Override
    public BattleCO get(String key) {
        return cache.get(key);
    }

    public Map<String, Map<String, List<Integer>>> getMap() {
        return cache.getMap();
    }
}
