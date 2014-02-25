package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.WeaponCO;
import com.h13.slg.config.co.WeaponXMLCO;
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
public class WeaponCache extends BasicCache<WeaponCO> {

    private static final String KEY = "slg:sys:weapon:";

    @Resource(name = "weaponTemplate")
    private RedisTemplate<String, WeaponCO> weaponTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<WeaponXMLCO> parser = new XmlParser<WeaponXMLCO>(WeaponXMLCO.class,
                filename);
        WeaponXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            weaponTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public WeaponCO get(String id) {
        return weaponTemplate.opsForValue().get(KEY + id);
    }
}
