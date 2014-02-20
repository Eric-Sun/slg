package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.MonsterCO;
import com.h13.slg.config.co.MonsterXMLCO;
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
public class MonsterCache extends BasicCache<MonsterCO> {

    private static final String KEY = "slg:sys:armor:";

    @Resource(name = "monsterTemplate")
    private RedisTemplate<String, MonsterCO> monsterTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<MonsterXMLCO> parser = new XmlParser<MonsterXMLCO>(MonsterXMLCO.class,
                filename);
        MonsterXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            monsterTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public MonsterCO get(String id) {
        return monsterTemplate.opsForValue().get(KEY + id);
    }
}
