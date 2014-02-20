package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.GlobalCO;
import com.h13.slg.config.co.GlobalXMLCO;
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
public class ArmorCache extends BasicCache<ArmorCO> {

    private static final String KEY = "slg:sys:armor:";

    @Resource(name = "armorTemplate")
    private RedisTemplate<String, ArmorCO> armorTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<ArmorXMLCO> parser = new XmlParser<ArmorXMLCO>(ArmorXMLCO.class,
                filename);
        ArmorXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            armorTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public ArmorCO get(String id) {
        return armorTemplate.opsForValue().get(KEY + id);
    }
}
