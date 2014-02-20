package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.EquipCO;
import com.h13.slg.config.co.EquipXMLCO;
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
public class EquipCache extends BasicCache<EquipCO> {

    private static final String KEY = "slg:sys:armor:";

    @Resource(name = "equipTemplate")
    private RedisTemplate<String, EquipCO> equipTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<EquipXMLCO> parser = new XmlParser<EquipXMLCO>(EquipXMLCO.class,
                filename);
        EquipXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            equipTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public EquipCO get(String id) {
        return equipTemplate.opsForValue().get(KEY + id);
    }
}
