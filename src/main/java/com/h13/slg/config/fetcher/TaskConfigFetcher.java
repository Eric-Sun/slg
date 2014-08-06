package com.h13.slg.config.fetcher;

import com.h13.slg.config.BasicFetcher;
import com.h13.slg.config.cache.TaskCache;
import com.h13.slg.config.co.TaskCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TaskConfigFetcher implements BasicFetcher<TaskCO> {

    @Autowired
    TaskCache cache;

    @Override
    public TaskCO get(String key) {
        return cache.get(key);
    }


}
