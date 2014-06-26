package com.h13.slg.config.cache;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.util.List;
import java.util.Map;

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
    private static final String KEY2 = "slg:sys:battleMap";

    @Resource(name = "battleTemplate")
    private RedisTemplate<String, BattleCO> battleTemplate;
    @Resource(name = "battleTemplate2")
    private RedisTemplate<String, String> battleTemplate2;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<BattleXMLCO> parser = new XmlParser<BattleXMLCO>(BattleXMLCO.class,
                filename);
        BattleXMLCO obj = parser.parse();

        Map<String, Map<String, List<Integer>>> battleMap = Maps.newHashMap();

        for (String id : obj.getMap().keySet()) {
            BattleCO battle = obj.getMap().get(id);
            battleTemplate.opsForValue().set(KEY + id, battle);

            Map<String, List<Integer>> castleMap = battleMap.get(battle.getBattle() + "");
            if (castleMap == null) {
                castleMap = Maps.newHashMap();
                battleMap.put(battle.getBattle() + "", castleMap);
            } else {
                castleMap.get(battle.getBattle());
            }

            List<Integer> idList = castleMap.get(battle.getCastle());
            if (idList == null) {
                idList = Lists.newLinkedList();
                idList.add(battle.getId());
                castleMap.put(battle.getCastle(), idList);
            } else {
                idList.add(battle.getId());
            }

        }
        battleTemplate2.opsForValue().set(KEY2, JSON.toJSONString(battleMap));
    }


    @Override
    public BattleCO get(String id) {
        return battleTemplate.opsForValue().get(KEY + id);
    }


    public Map<String, Map<String, List<Integer>>> getMap() {
        return JSON.parseObject(battleTemplate2.opsForValue().get(KEY2), Map.class);
    }
}
