package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.BusinessCO;
import com.h13.slg.config.co.BusinessXMLCO;
import com.h13.slg.config.co.GemCO;
import com.h13.slg.config.co.GemXMLCO;
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
public class BusinessCache extends BasicCache<BusinessCO> {

    private static final String KEY = "slg:sys:business:";

    @Resource(name = "businessTemplate")
    private RedisTemplate<String, BusinessCO> businessTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<BusinessXMLCO> parser = new XmlParser<BusinessXMLCO>(BusinessXMLCO.class,
                filename);
        BusinessXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            businessTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public BusinessCO get(String id) {
        return businessTemplate.opsForValue().get(KEY + id);
    }
}
