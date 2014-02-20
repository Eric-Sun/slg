package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.MaterialCO;
import com.h13.slg.config.co.MaterialXMLCO;
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
public class MaterialCache extends BasicCache<MaterialCO> {

    private static final String KEY = "slg:sys:material:";

    @Resource(name = "materialTemplate")
    private RedisTemplate<String, MaterialCO> materialTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<MaterialXMLCO> parser = new XmlParser<MaterialXMLCO>(MaterialXMLCO.class,
                filename);
        MaterialXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            materialTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public MaterialCO get(String id) {
        return materialTemplate.opsForValue().get(KEY + id);
    }
}
