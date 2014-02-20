package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.GlobalCO;
import com.h13.slg.config.co.GlobalXMLCO;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.RoleXMLCO;
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
public class GlobalCache extends BasicCache<GlobalCO> {

    private static final String KEY = "slg:sys:global:";

    @Resource(name = "globalTemplate")
    private RedisTemplate<String, GlobalCO> globalTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<GlobalXMLCO> parser = new XmlParser<GlobalXMLCO>(GlobalXMLCO.class,
                filename);
        GlobalXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            globalTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public GlobalCO get(String id) {
        return globalTemplate.opsForValue().get(KEY + id);
    }
}
