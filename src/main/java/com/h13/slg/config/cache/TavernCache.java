package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.TaskCO;
import com.h13.slg.config.co.TaskXMLCO;
import com.h13.slg.config.co.TavernConfigCO;
import com.h13.slg.config.co.TavernXMLCO;
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
public class TavernCache extends BasicCache<TavernConfigCO> {

    private static final String KEY = "slg:sys:tavernConfig:";

    @Resource(name = "tavernConfigTemplate")
    private RedisTemplate<String, TavernConfigCO> tavernConfigTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<TavernXMLCO> parser = new XmlParser<TavernXMLCO>(TavernXMLCO.class,
                filename);
        TavernXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            tavernConfigTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public TavernConfigCO get(String id) {
        return tavernConfigTemplate.opsForValue().get(KEY + id);
    }
}
