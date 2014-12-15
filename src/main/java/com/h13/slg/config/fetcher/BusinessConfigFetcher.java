package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.ArmorCache;
import com.h13.slg.config.cache.BusinessCache;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.BusinessCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BusinessConfigFetcher implements BasicFetcher<BusinessCO> {

    @Autowired
    BusinessCache cache;

    @Override
    public BusinessCO get(String key) {
        return cache.get(key);
    }

}
