package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.AccessoryCO;
import com.h13.slg.config.co.AccessoryXMLCO;
import com.h13.slg.config.co.SkilleffectConfigCO;
import com.h13.slg.config.co.SkilleffectConfigXMLCO;
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
public class SkilleffectCache extends BasicCache<SkilleffectConfigCO> {

    private static final String KEY = "slg:sys:skilleffect:";

    @Resource(name = "skilleffectTemplate")
    private RedisTemplate<String, SkilleffectConfigCO> skilleffectTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<SkilleffectConfigXMLCO> parser = new XmlParser<SkilleffectConfigXMLCO>(SkilleffectConfigXMLCO.class,
                filename);
        SkilleffectConfigXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            skilleffectTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public SkilleffectConfigCO get(String id) {
        return skilleffectTemplate.opsForValue().get(KEY + id);
    }
}
