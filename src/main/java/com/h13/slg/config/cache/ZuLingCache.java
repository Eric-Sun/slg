package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.AccessoryXMLCO;
import com.h13.slg.config.co.ZuLingCO;
import com.h13.slg.config.co.ZuLingXMLCO;
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
public class ZuLingCache extends BasicCache<ZuLingCO> {

    private static final String KEY = "slg:sys:zuLing:";

    @Resource(name = "zuLingTemplate")
    private RedisTemplate<String, ZuLingCO> zuLingTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<ZuLingXMLCO> parser = new XmlParser<ZuLingXMLCO>(ZuLingXMLCO.class,
                filename);
        ZuLingXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            zuLingTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public ZuLingCO get(String id) {
        return zuLingTemplate.opsForValue().get(KEY + id);
    }
}
