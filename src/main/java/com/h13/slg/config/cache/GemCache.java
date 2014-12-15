package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.AccessoryXMLCO;
import com.h13.slg.config.co.GemCO;
import com.h13.slg.config.co.GemXMLCO;
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
public class GemCache extends BasicCache<GemCO> {

    private static final String KEY = "slg:sys:gem:";

    @Resource(name = "gemTemplate")
    private RedisTemplate<String, GemCO> gemTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<GemXMLCO> parser = new XmlParser<GemXMLCO>(GemXMLCO.class,
                filename);
        GemXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            gemTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public GemCO get(String id) {
        return gemTemplate.opsForValue().get(KEY + id);
    }
}
