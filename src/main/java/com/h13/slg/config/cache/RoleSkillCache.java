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
    private static final String ZHE = "slg:sys:roleSkill:zhe";
    private static final String SHI = "slg:sys:roleSkill:shi";
    private static final String WANG = "slg:sys:roleSkill:wang";
    private static final String SHENG = "slg:sys:roleSkill:sheng";


    @Resource(name = "roleSkillTemplate")
    private RedisTemplate<String, RoleSkillCO> roleSkillTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<RoleSkillXMLCO> parser = new XmlParser<RoleSkillXMLCO>(RoleSkillXMLCO.class,
                filename);
        RoleSkillXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            roleSkillTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));

            RoleSkillCO roleSkillCO = obj.getMap().get(id);
            if (roleSkillCO.getQuality().equals(RoleSkillConstants.ZHE)) {
                roleSkillTemplate.opsForList().leftPush(ZHE, roleSkillCO);
            } else if (roleSkillCO.getQuality().equals(RoleSkillConstants.SHI)) {
                roleSkillTemplate.opsForList().leftPush(SHI, roleSkillCO);
            } else if (roleSkillCO.getQuality().equals(RoleSkillConstants.WANG)) {
                roleSkillTemplate.opsForList().leftPush(WANG, roleSkillCO);
            } else {
                roleSkillTemplate.opsForList().leftPush(SHENG, roleSkillCO);
            }

        }

    }


    @Override
    public RoleSkillCO get(String id) {
        return roleSkillTemplate.opsForValue().get(KEY + id);
    }


    public long getSize(String key) {
        return roleSkillTemplate.opsForList().size(key);
    }


    public RoleSkillCO getInList(String key, long index) {
        return roleSkillTemplate.opsForList().index(key, index);
    }


}
