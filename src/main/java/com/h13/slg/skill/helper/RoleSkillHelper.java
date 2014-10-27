package com.h13.slg.skill.helper;

import com.google.common.collect.Lists;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.skill.cache.UserRoleSkillCache;
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
    @Autowired
    UserRoleSkillCache userRoleSkillCache;


    /**
     * 经过rid，uid的验证，获取用户角色技能信息
     * @param uid
     * @param rid
     * @param ursid
     * @return
     * @throws RequestUnexpectedException
     */
    public UserRoleSkillCO get(int uid, int rid, int ursid) throws RequestUnexpectedException {
        UserRoleSkillCO userRoleSkillCO = userRoleSkillCache.get(ursid);
        if (userRoleSkillCO == null) {
            userRoleSkillCO = userRoleSkillDAO.get(uid, rid, ursid);
            userRoleSkillCache.set(userRoleSkillCO);
            return userRoleSkillCO;
        } else {
            if (userRoleSkillCO.getUid() != uid || userRoleSkillCO.getRid() != rid) {
                throw new RequestUnexpectedException(CodeConstants.User.UID_USER_ROLE_SKILL_ID_NOT_MATCH);
            }
            return userRoleSkillCO;

        }
    }

    /**
     * 内部方法
     * 在不经过过多验证的情况下，获取用户角色技能的信息
     * @param uid
     * @param ursid
     * @return
     * @throws RequestUnexpectedException
     */
    private UserRoleSkillCO get(int uid, int ursid) throws RequestUnexpectedException {
        UserRoleSkillCO userRoleSkillCO = userRoleSkillCache.get(ursid);
        if (userRoleSkillCO == null) {
            userRoleSkillCO = userRoleSkillDAO.get(uid, ursid);
            userRoleSkillCache.set(userRoleSkillCO);
            return userRoleSkillCO;
        } else {
            if (userRoleSkillCO.getUid() != uid) {
                throw new RequestUnexpectedException(CodeConstants.User.UID_USER_ROLE_SKILL_ID_NOT_MATCH);
            }
            return userRoleSkillCO;

        }
    }

    /**
     * 获取用户所有的技能列表
     *
     * @param uid 用户id
     * @return
     * @throws RequestUnexpectedException
     */
    public List<UserRoleSkillCO> list(int uid) throws RequestUnexpectedException {
        List<Integer> idList = userRoleSkillDAO.getAllIdListByUid(uid);
        List<UserRoleSkillCO> returnList = Lists.newLinkedList();
        for (Integer id : idList) {
            returnList.add(get(uid, id));
        }
        return returnList;
    }

    /**
     * 获得某一个userRole的天赋技能信息
     *
     * @param uid
     * @param urid
     * @return
     * @throws RequestUnexpectedException
     */
    public UserRoleSkillCO getTianfu(int uid, int urid) throws RequestUnexpectedException {
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
        int ursid = userRoleCO.getTianfuSkillId();
        return get(uid, urid, ursid);
    }

    /**
     * 获得某一个userrole的普通技能信息
     *
     * @param uid
     * @param urid
     * @return
     * @throws RequestUnexpectedException
     */
    public UserRoleSkillCO getPutong(int uid, int urid) throws RequestUnexpectedException {
        UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
        int ursid = userRoleCO.getPutongSkillId();
        return get(uid, urid, ursid);
    }

    /**
     * 更新用户角色技能对象，并更新cache
     *
     * @param userRoleSkillCO
     */
    public void update(UserRoleSkillCO userRoleSkillCO) {
        userRoleSkillDAO.update(userRoleSkillCO);
        userRoleSkillCache.set(userRoleSkillCO);
    }

    /**
     * 插入新的用户角色技能对象，并且更新cache
     *
     * @param userRoleSkillCO
     */
    public void insert(UserRoleSkillCO userRoleSkillCO) {
        userRoleSkillDAO.insert(userRoleSkillCO);
        userRoleSkillCache.set(userRoleSkillCO);
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



}
