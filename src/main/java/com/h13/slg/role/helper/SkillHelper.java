package com.h13.slg.role.helper;

import com.h13.slg.config.co.SkillConfigCO;
import com.h13.slg.config.fetcher.SkillConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.co.UserRoleCO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-4-9
 * Time: 下午12:09
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SkillHelper {
    @Autowired
    SkillConfigFetcher skillConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    UserPackageHelper userPackageHelper;

    /**
     * 升级某一个技能
     *
     * @param uid   用户id
     * @param urid  用户roleId
     * @param skill 技能排序id
     * @return 下一等级号
     */
    public int upgrade(long uid, long urid, int skill) throws RequestErrorException {
        try {
            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
            int soldier = userRoleCO.getSoldier();
            SkillConfigCO skillConfigCO = skillConfigFetcher.get(soldier, skill);
            Map<String, Integer> skillLevels = userRoleCO.getSkillLevels();
            if (!skillLevels.containsKey(skill)) {
                // 这个技能没有被开启
                SlgLogger.error(SlgLoggerEntity.p("skill", "update", uid, "skill not exists")
                        .addParam("uid", uid)
                        .addParam("urid", urid)
                        .addParam("skill", skill));
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "skill not exists");
            }
            int skillLevel = skillLevels.get(skill);
            if (skillLevel == 10) {
                SlgLogger.error(SlgLoggerEntity.p("skill", "update", uid, "skill level is 10!")
                        .addParam("uid", uid)
                        .addParam("urid", urid)
                );
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "skill level is 10!");
            }
            int nextSkillLevel = skillLevel + 1;
            int materialId = skillConfigCO.getMaterial();
            int materialNum = new Integer(BeanUtils.getSimpleProperty(skillConfigCO, "materialNum" + nextSkillLevel));


            // 查看背包中是否有这些资源
            if (!userPackageHelper.checkMaterialEnough(uid, materialId, materialNum))
                throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "material is not enough");
            // 减掉这些资源来进行升级
            userPackageHelper.subtractMaterial(uid, materialId, materialNum);
            skillLevels.put(skill + "", nextSkillLevel);
            userRoleHelper.updateUserRole(userRoleCO);
            return nextSkillLevel;
        } catch (Exception e) {
            SlgLogger.error(SlgLoggerEntity.p("skill", "upgrade", uid, ""), e);
            throw new RequestErrorException(CodeConstants.SYSTEM.COMMON_ERROR, "");
        }
    }

}
