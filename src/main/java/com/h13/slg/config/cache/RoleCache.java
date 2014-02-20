package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.LevelXMLCO;
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
public class RoleCache extends BasicCache<RoleCO> {

    private static final String KEY = "slg:sys:role:";

    @Resource(name = "roleTemplate")
    private RedisTemplate<String, RoleCO> roleTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<RoleXMLCO> parser = new XmlParser<RoleXMLCO>(RoleXMLCO.class,
                filename);
        RoleXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            roleTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public RoleCO get(String id) {
        return roleTemplate.opsForValue().get(KEY + id);
    }
}
