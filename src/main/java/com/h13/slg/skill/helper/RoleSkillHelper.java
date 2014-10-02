package com.h13.slg.skill.helper;

import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.skill.co.UserRoleSkillCO;
import com.h13.slg.skill.dao.UserRoleSkillDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午5:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RoleSkillHelper {

    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    UserRoleSkillDAO userRoleSkillDAO;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;


    public UserRoleSkillCO get(int uid, int rid, int ursid) {
        return userRoleSkillDAO.get(uid, rid, ursid);
    }

    public List<UserRoleSkillCO> get(int uid) {
        return userRoleSkillDAO.get(uid);
    }

    public UserRoleSkillCO getTianfu(int uid, int urid) {
        return userRoleSkillDAO.getTianfu(uid, urid);
    }

    public UserRoleSkillCO getJiangling(int uid, int urid) {
        return userRoleSkillDAO.getJiangling(uid, urid);
    }

    public void update(UserRoleSkillCO userRoleSkillCO) {
        userRoleSkillDAO.update(userRoleSkillCO);
    }

    public void insert(UserRoleSkillCO userRoleSkillCO) {
        userRoleSkillDAO.insert(userRoleSkillCO);
    }


    /**
     * 将领学习将领技能
     *
     * @param urid
     * @param rsid
     */
    public void setRoleSkillToRole(int uid, int urid, int rsid, String type) throws RequestErrorException {

        // 检查这个role是不是你的
        boolean b = userRoleHelper.checkUserRole(uid, urid);
        if (!b) {
            throw new RequestErrorException(CodeConstants.Role.DONT_HAVE_THIS_USER_ROLE);
        }

        // 检测这个技能是不是在包裹中
        int usCount = userPackageHelper.getSkillCount(uid, rsid);
        if (usCount == 0)
            throw new RequestErrorException(CodeConstants.RoleSkill.PACKAGE_DONT_HAVE_THIS_SKILL, "");

        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsid + "");

        // 检测当前 将领是否已经有技能
        UserRoleSkillCO oldUserRoleSkillCO = null;
        if (type.equals(SlgConstants.RoleSkillConstants.SkillType.JIANGLING))
            oldUserRoleSkillCO = getJiangling(uid, urid);
        else
            oldUserRoleSkillCO = getTianfu(uid, urid);

        if (oldUserRoleSkillCO != null) {
            deleteUserRoleSkill(uid, urid, oldUserRoleSkillCO.getId());
        }

        UserRoleSkillCO userRoleSkillCO = new UserRoleSkillCO();
        userRoleSkillCO.setType(roleSkillCO.getType());
        userRoleSkillCO.setRid(urid);
        userRoleSkillCO.setRsid(rsid);
        userRoleSkillCO.setUid(uid);
        userRoleSkillCO.setName(roleSkillCO.getName());

        insert(userRoleSkillCO);

        // 去掉包裹中的对应的技能
        userPackageHelper.subtractSkill(uid, rsid, 1);

    }


    /**
     * 将领技能从将领中清除
     *
     * @param urid
     * @param ursid 需要删除的老的将领技能
     */
    public void deleteUserRoleSkill(int uid, int urid, int ursid) {
        userRoleSkillDAO.delete(uid, urid, ursid);
    }


    /**
     * 升级用户技能
     *
     * @param uid
     * @param ursid
     */
    public void upgrade(int uid, int urid, int ursid) throws RequestErrorException {

        UserRoleSkillCO userRoleSkillCO = get(uid, urid, ursid);
        int curLevel = userRoleSkillCO.getLevel();
        int rsid = userRoleSkillCO.getRsid();

        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsid + "");

        int materialId = roleSkillCO.getMaterial();
        int nextLevel = curLevel++;

        int needCount = new Integer(SlgBeanUtils.getProperty(roleSkillCO, "materialNum" + nextLevel));

        // 检查材料数量够不够
        boolean isEnough = userPackageHelper.checkMaterialEnough(uid, materialId, needCount);
        if (!isEnough) {
            throw new RequestErrorException(CodeConstants.Package.MATERIAL_IS_NOT_ENOUGH);
        }

        userPackageHelper.subtractMaterial(uid, materialId, needCount);

        userRoleSkillCO.setLevel(nextLevel);
        update(userRoleSkillCO);

    }
}
