package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.StrengthenCO;
import com.h13.slg.config.co.StrengthenXMLCO;
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
public class StrengthenCache extends BasicCache<StrengthenCO> {

    private static final String KEY = "slg:sys:accessory:";

    @Resource(name = "strengthenTemplate")
    private RedisTemplate<String, StrengthenCO> strengthenTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<StrengthenXMLCO> parser = new XmlParser<StrengthenXMLCO>(StrengthenXMLCO.class,
                filename);
        StrengthenXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            strengthenTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public StrengthenCO get(String id) {
        return strengthenTemplate.opsForValue().get(KEY + id);
    }
}
