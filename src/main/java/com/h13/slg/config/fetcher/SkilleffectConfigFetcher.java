package com.h13.slg.config.fetcher;

import com.h13.slg.config.cache.SkilleffectCache;
import com.h13.slg.config.co.SkilleffectConfigCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SkilleffectConfigFetcher {

    @Autowired
    SkilleffectCache cache;

    public SkilleffectConfigCO get(int soldier, int skill, int level) {
        return cache.get(soldier + "_" + skill + "_" + level);
    }

}
