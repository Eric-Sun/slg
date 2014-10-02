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
import java.util.List;

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
    private static final String KEY_ZHAOXIAN = "slg:sys:role:zhaoxian";
    private static final String KEY_FUBEN = "slg:sys:role:fuben";
    private static final String KEY_HUODONG = "slg:sys:role:huodong";
    private static final String KEY_DUIHUAN = "slg:sys:role:duihuan";

    @Resource(name = "roleTemplate")
    private RedisTemplate<String, RoleCO> roleTemplate;

    @Resource(name = "rolePartitionTemplate")
    private RedisTemplate<String, Integer> rolePartitionTemplate;


    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<RoleXMLCO> parser = new XmlParser<RoleXMLCO>(RoleXMLCO.class,
                filename);
        RoleXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            roleTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }


        // 需要导入招贤馆等需要的随机卡牌库
        // 按照卡牌的颜色进行匹配，分出给招贤馆的，或者是给活动的等等
        // ReceiveMode （1招贤馆、2副本、3活动、4兑换）
        for (String id : obj.getMap().keySet()) {
            RoleCO roleCO = obj.getMap().get(id);
            if (roleCO.getReceiveMode() == 1) {
                rolePartitionTemplate.opsForList().leftPush(KEY_ZHAOXIAN + "_" + roleCO.getQuantity()
                        , roleCO.getId());
            } else if (roleCO.getReceiveMode() == 2) {
                rolePartitionTemplate.opsForList().leftPush(KEY_FUBEN + "_" + roleCO.getQuantity()
                        , roleCO.getId());
            } else if (roleCO.getReceiveMode() == 3) {
                rolePartitionTemplate.opsForList().leftPush(KEY_HUODONG + "_" + roleCO.getQuantity()
                        , roleCO.getId());
            } else {
                rolePartitionTemplate.opsForList().leftPush(KEY_DUIHUAN + "_" + roleCO.getQuantity()
                        , roleCO.getId());
            }
        }

    }


    @Override
    public RoleCO get(String id) {
        return roleTemplate.opsForValue().get(KEY + id);
    }


    public int getFromZhaoxian(int index, String quality) {
        return Integer.parseInt(rolePartitionTemplate.opsForList().index(KEY_ZHAOXIAN + "_" + quality, index) + "");
    }

    public int getFromFuben(int index, String quality) {
        return Integer.parseInt(rolePartitionTemplate.opsForList().index(KEY_FUBEN + "_" + quality, index) + "");
    }

    public int getFromHuodong(int index, String quality) {
        return Integer.parseInt(rolePartitionTemplate.opsForList().index(KEY_HUODONG + "_" + quality, index) + "");
    }

    public int getFromDuihuan(int index, String quality) {
        return Integer.parseInt(rolePartitionTemplate.opsForList().index(KEY_DUIHUAN + "_" + quality, index) + "");
    }


    public long getZhaoxianSize(String quality) {
        return rolePartitionTemplate.opsForList().size(KEY_ZHAOXIAN + "_" + quality);
    }

}
