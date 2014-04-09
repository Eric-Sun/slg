package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.AccessoryXMLCO;
import com.h13.slg.config.co.SkillConfigCO;
import com.h13.slg.config.co.SkillConfigXMLCO;
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
public class SkillCache extends BasicCache<SkillConfigCO> {

    private static final String KEY = "slg:sys:skill:";

    @Resource(name = "skillTemplate")
    private RedisTemplate<String, SkillConfigCO> skillTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<SkillConfigXMLCO> parser = new XmlParser<SkillConfigXMLCO>(SkillConfigXMLCO.class,
                filename);
        SkillConfigXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            skillTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public SkillConfigCO get(String id) {
        return skillTemplate.opsForValue().get(KEY + id);
    }
}
