package com.h13.slg.config.cache;

import com.google.common.collect.Lists;
import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.ArmorCO;
import com.h13.slg.config.co.ArmorXMLCO;
import com.h13.slg.config.co.ShopCO;
import com.h13.slg.config.co.ShopXMLCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ShopCache extends BasicCache<ShopCO> {

    private static final String KEY = "slg:sys:shop:";

    @Resource(name = "shopTemplate")
    private RedisTemplate<String, ShopCO> shopTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<ShopXMLCO> parser = new XmlParser<ShopXMLCO>(ShopXMLCO.class,
                filename);
        ShopXMLCO obj = parser.parse();
        shopTemplate.opsForList().getOperations().delete(KEY + "*");
        for (String id : obj.getMap().keySet()) {
            shopTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }

    @Override
    public ShopCO get(String id) {
        return shopTemplate.opsForValue().get(KEY + id);
    }


    public List<ShopCO> getAll() {
        Set<String> set = shopTemplate.opsForValue().getOperations().keys(KEY + "*");
        return shopTemplate.opsForValue().multiGet(set);
    }

}
