package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.LevelCO;
import com.h13.slg.config.co.LevelXMLCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LevelCache extends BasicCache<LevelCO> {

    private static final String KEY = "slg:sys:level:";

    @Resource(name = "levelTemplate")
    private RedisTemplate<String, LevelCO> levelTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<LevelXMLCO> parser = new XmlParser<LevelXMLCO>(LevelXMLCO.class,
                filename);
        LevelXMLCO levels = parser.parse();
        for (String id : levels.getMap().keySet()) {
            levelTemplate.opsForValue().set(KEY + id, levels.getMap().get(id));
        }
    }


    @Override
    public LevelCO get(String id) {
        return levelTemplate.opsForValue().get(KEY + id);
    }
}
