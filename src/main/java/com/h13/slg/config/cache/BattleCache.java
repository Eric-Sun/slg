package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.BattleCO;
import com.h13.slg.config.co.BattleXMLCO;
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
public class BattleCache extends BasicCache<BattleCO> {

    private static final String KEY = "slg:sys:battle:";

    @Resource(name = "battleTemplate")
    private RedisTemplate<String, BattleCO> battleTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<BattleXMLCO> parser = new XmlParser<BattleXMLCO>(BattleXMLCO.class,
                filename);
        BattleXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            battleTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public BattleCO get(String id) {
        return battleTemplate.opsForValue().get(KEY + id);
    }
}
