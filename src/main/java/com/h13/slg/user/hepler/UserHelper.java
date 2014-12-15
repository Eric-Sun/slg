package com.h13.slg.user.hepler;

import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MD5Util;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserDAO;
import com.h13.slg.user.dao.UserStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-5-13
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserHelper {

    @Autowired
    private UserStatusCache userStatusCache;
    @Autowired
    private UserRoleHelper userRoleHelper;
    @Autowired
    private TavernHelper tavernHelper;
    @Autowired
    private UserEquipHelper userEquipHelper;
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserStatusDAO userStatusDAO;

    @Autowired
    FarmHelper farmHelper;
    @Autowired
    UserTaskHelper userTaskHelper;

    @Autowired
    CastleHelper castleHelper;

    @Autowired
    UserPackageHelper userPackageHelper;

    @Autowired
    UserStatusHelper userStatusHelper;

    @Autowired
    TeamHelper teamHelper;
    @Autowired
    AuthHelper authHelper;



    /**
     * 注册，如果成功的话返回uid
     *
     * @return
     * @throws com.h13.slg.core.exception.RequestFatalException
     */
    public int register(int aid, String name) throws RequestFatalException {
        // check name exists?
        if (!userDAO.check(name)) {
            SlgLogger.info(SlgLoggerEntity.p("user", "register", -1, "name exists").addParam("name", name));
            throw new RequestFatalException(CodeConstants.User.NAME_EXISTS, "");
        }

        // register user
        int uid = userDAO.insert(aid, name);
        UserStatusCO userStatusCO = userStatusHelper.initUserStatus(uid, name);
        userStatusDAO.insert(
                userStatusCO.getId(),
                userStatusCO.getName(),
                userStatusCO.getGold(),
                userStatusCO.getFood(),
                userStatusCO.getCash(),
                userStatusCO.getLevel(),
                userStatusCO.getXp(),
                userStatusCO.getFightForce());

        // init farm castle timer
        farmHelper.create(uid);
        castleHelper.create(uid);
        userPackageHelper.createDefaultPackage(uid);
        // 初始化任务数据
        userTaskHelper.create(uid);
        // 创建第一个人物
        userRoleHelper.addDefaultRole(uid);
        // 在包裹中放入一些基础的物品
        userEquipHelper.addUserEquipForRegister(uid);
        // 创建tavern
        tavernHelper.create(uid);
        // 创建新的team
        teamHelper.createANewTeam(uid);

        userStatusCache.set(userStatusCO);

        return uid;
    }

    public int getInfoByAid(int aid) throws RequestUnexpectedException {
        try {
            return userDAO.getInfoByAid(aid);
        } catch (DataAccessException e) {
            throw new RequestUnexpectedException(CodeConstants.SYSTEM.SHOULD_REGISTER_FIRST);
        }

    }
}
