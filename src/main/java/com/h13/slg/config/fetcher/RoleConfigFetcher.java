package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.RoleCache;
import com.h13.slg.config.co.RoleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RoleConfigFetcher implements BasicFetcher<RoleCO> {

    @Autowired
    RoleCache cache;

    @Override
    public RoleCO get(String key) {
        return cache.get(key);
    }

}
