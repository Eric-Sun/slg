package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.AccessoryXMLCO;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
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
public class AccessoryCache extends BasicCache<AccessoryCO> {

    private static final String KEY = "slg:sys:accessory:";

    @Resource(name = "accessoryTemplate")
    private RedisTemplate<String, AccessoryCO> accessoryTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<AccessoryXMLCO> parser = new XmlParser<AccessoryXMLCO>(AccessoryXMLCO.class,
                filename);
        AccessoryXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            accessoryTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public AccessoryCO get(String id) {
        return accessoryTemplate.opsForValue().get(KEY + id);
    }
}
