package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.co.RoleSkillXMLCO;
import com.h13.slg.skill.RoleSkillConstants;
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
public class RoleSkillCache extends BasicCache<RoleSkillCO> {

    private static final String KEY = "slg:sys:roleSkill:";
    private static final String TIAN = "slg:sys:roleSkill:tian";
    private static final String DI = "slg:sys:roleSkill:di";
    private static final String XUAN = "slg:sys:roleSkill:xuan";
    private static final String HUANG = "slg:sys:roleSkill:huang";


    @Resource(name = "roleSkillTemplate")
    private RedisTemplate<String, RoleSkillCO> roleSkillTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<RoleSkillXMLCO> parser = new XmlParser<RoleSkillXMLCO>(RoleSkillXMLCO.class,
                filename);
        RoleSkillXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            roleSkillTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));

            roleSkillTemplate.opsForList().getOperations().delete(HUANG);
            roleSkillTemplate.opsForList().getOperations().delete(XUAN);
            roleSkillTemplate.opsForList().getOperations().delete(DI);
            roleSkillTemplate.opsForList().getOperations().delete(TIAN);


            RoleSkillCO roleSkillCO = obj.getMap().get(id);
            if (roleSkillCO.getQuality().equals(RoleSkillConstants.HUANG)) {
                roleSkillTemplate.opsForList().leftPush(HUANG, roleSkillCO);
            } else if (roleSkillCO.getQuality().equals(RoleSkillConstants.XUAN)) {
                roleSkillTemplate.opsForList().leftPush(XUAN, roleSkillCO);
            } else if (roleSkillCO.getQuality().equals(RoleSkillConstants.DI)) {
                roleSkillTemplate.opsForList().leftPush(DI, roleSkillCO);
            } else {
                roleSkillTemplate.opsForList().leftPush(TIAN, roleSkillCO);
            }

        }

    }


    @Override
    public RoleSkillCO get(String id) {
        return roleSkillTemplate.opsForValue().get(KEY + id);
    }


    public long getSize(String key) {
        if (key.equals(RoleSkillConstants.HUANG)) {
            return roleSkillTemplate.opsForList().size(HUANG);
        } else if (key.equals(RoleSkillConstants.XUAN)) {
            return roleSkillTemplate.opsForList().size(XUAN);
        } else if (key.equals(RoleSkillConstants.DI)) {
            return roleSkillTemplate.opsForList().size(DI);
        } else {
            return roleSkillTemplate.opsForList().size(TIAN);
        }
    }


    public RoleSkillCO getInList(String key, long index) {
        if (key.equals(RoleSkillConstants.HUANG)) {
            return roleSkillTemplate.opsForList().index(HUANG, index);
        } else if (key.equals(RoleSkillConstants.XUAN)) {
            return roleSkillTemplate.opsForList().index(XUAN, index);
        } else if (key.equals(RoleSkillConstants.DI)) {
            return roleSkillTemplate.opsForList().index(DI, index);
        } else {
            return roleSkillTemplate.opsForList().index(TIAN, index);
        }

    }


}
