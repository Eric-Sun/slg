package com.h13.slg.skill.service;

import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.skill.cache.UserRoleSkillCache;
import com.h13.slg.skill.co.UserRoleSkillCO;
import com.h13.slg.skill.helper.RoleSkillHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Service("SkillService")
public class SkillServiceImpl implements SkillService {

    @Autowired
    RoleSkillHelper roleSkillHelper;
    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    UserRoleSkillCache userRoleSkillCache;

    @Override
    public SlgData setSkill(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException {

        int uid = requestDTO.getUid();
        int urid = new Integer(requestDTO.getArgs().get("urid") + "");
        int rsid = new Integer(requestDTO.getArgs().get("rsid") + "");
        String type = new String(requestDTO.getArgs().get("type") + "");

        // 检查这个role是不是你的
        boolean b = userRoleHelper.checkUserRole(uid, urid);
        if (!b) {
            throw new RequestFatalException(CodeConstants.Role.DONT_HAVE_THIS_USER_ROLE,
                    String.format("uid=%s,urid=%s", uid, urid));
        }

        // 检测这个技能是不是在包裹中
        int usCount = userPackageHelper.getSkillCount(uid, rsid);
        if (usCount == 0)
            throw new RequestFatalException(CodeConstants.RoleSkill.PACKAGE_DONT_HAVE_THIS_SKILL, "");

        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsid + "");

        // 检测当前 将领是否已经有技能
        UserRoleSkillCO oldUserRoleSkillCO = null;
        if (type.equals(SlgConstants.RoleSkillConstants.SkillType.JIANGLING))
            oldUserRoleSkillCO = roleSkillHelper.getPutong(uid, urid);
        else
            oldUserRoleSkillCO = roleSkillHelper.getTianfu(uid, urid);

        if (oldUserRoleSkillCO != null) {
            roleSkillHelper.deleteUserRoleSkill(uid, urid, oldUserRoleSkillCO.getId());
        }

        UserRoleSkillCO userRoleSkillCO = new UserRoleSkillCO();
        userRoleSkillCO.setType(roleSkillCO.getType());
        userRoleSkillCO.setRid(urid);
        userRoleSkillCO.setRsid(rsid);
        userRoleSkillCO.setUid(uid);
        userRoleSkillCO.setName(roleSkillCO.getName());

        roleSkillHelper.insert(userRoleSkillCO);

        // 去掉包裹中的对应的技能
        userPackageHelper.subtractSkill(uid, rsid, 1);

        return SlgData.getData();
    }

    @Override
    public SlgData upgrade(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException {

        int uid = requestDTO.getUid();
        int ursid = new Integer(requestDTO.getArgs().get("ursid") + "");
        int urid = new Integer(requestDTO.getArgs().get("urid") + "");

        UserRoleSkillCO userRoleSkillCO = roleSkillHelper.get(uid, urid, ursid);
        int curLevel = userRoleSkillCO.getLevel();
        int rsid = userRoleSkillCO.getRsid();

        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsid + "");

        int materialId = roleSkillCO.getMaterial();
        int nextLevel = curLevel++;

        int needCount = new Integer(SlgBeanUtils.getProperty(roleSkillCO, "materialNum" + nextLevel));

        // 检查材料数量够不够
        boolean isEnough = userPackageHelper.checkMaterialEnough(uid, materialId, needCount);
        if (!isEnough) {
            throw new RequestFatalException(CodeConstants.Package.MATERIAL_IS_NOT_ENOUGH,
                    String.format("materialId=%s,needCount=%s", materialId, needCount));
        }

        userPackageHelper.subtractMaterial(uid, materialId, needCount);

        userRoleSkillCO.setLevel(nextLevel);
        roleSkillHelper.update(userRoleSkillCO);
        return SlgData.getData();
    }
}
