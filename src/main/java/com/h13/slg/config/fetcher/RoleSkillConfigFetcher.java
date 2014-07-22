package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.RoleSkillCache;
import com.h13.slg.config.co.RoleSkillCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 *
 */
@Service
public class RoleSkillConfigFetcher implements BasicFetcher<RoleSkillCO> {

    @Autowired
    RoleSkillCache cache;

    @Override
    public RoleSkillCO get(String key) {
        return cache.get(key);
    }

    public RoleSkillCO random(String key) {
        long size = cache.getSize(key);
        Random random = new Random();
        long index = random.nextInt(new Integer(size + ""));
        RoleSkillCO roleSkillCO = cache.getInList(key, index);
        return roleSkillCO;
    }


}
