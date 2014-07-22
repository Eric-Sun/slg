package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.AccessoryCache;
import com.h13.slg.config.cache.SkillCache;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.SkillConfigCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SkillConfigFetcher {

    @Autowired
    SkillCache cache;

    public  get(int soldier, int skill) {
        return cache.get(soldier + "_" + skill);
    }

}
