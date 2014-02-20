package com.h13.slg.user.hepler;

import com.h13.slg.config.cache.LevelCache;
import com.h13.slg.config.co.LevelCO;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LevelHelper {

    @Autowired
    LevelCache levelCache;

    public LevelCO getLevelInfo(int level) {
        return levelCache.get(level + "");
    }
}
