package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.RoleLevelCO;
import com.h13.slg.config.co.RoleLevelXMLCO;
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
public class RolelevelCache extends BasicCache<RoleLevelCO> {

    private static final String KEY = "slg:sys:rolelevel:";

    @Resource(name = "roleLevelTemplate")
    private RedisTemplate<String, RoleLevelCO> roleLevelTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<RoleLevelXMLCO> parser = new XmlParser<RoleLevelXMLCO>(RoleLevelXMLCO.class,
                filename);
        RoleLevelXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            roleLevelTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public RoleLevelCO get(String id) {
        return roleLevelTemplate.opsForValue().get(KEY + id);
    }
}
