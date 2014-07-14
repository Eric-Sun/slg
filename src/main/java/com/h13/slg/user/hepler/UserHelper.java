package com.h13.slg.user.hepler;

import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MD5Util;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserDAO;
import com.h13.slg.user.dao.UserStatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 登陆接口
     *
     * @param name
     * @param password
     * @return 如果返回-1，则为登陆失败，如果成功的话，返回正常的uid
     * @throws RequestErrorException 用户密码错误
     */
    public int login(String name, String password) throws RequestErrorException {
        int userId = userDAO.login(name, MD5Util.getMD5String(password));
        if (userId == -1) {
            // 登陆失败,用户密码错误
            throw new RequestErrorException(CodeConstants.User.NAME_OR_PASSWORD_ERROR, "");
        }
        return userId;
    }


    /**
     * 注册，如果成功的话返回uid
     * @param name
     * @param password
     * @return
     * @throws RequestErrorException
     */
    public int register(String name, String password) throws RequestErrorException {
        // check name exists?
        if (!userDAO.check(name)) {
            SlgLogger.info(SlgLoggerEntity.p("user", "register", -1, "name exists").addParam("name", name));
            throw new RequestErrorException(CodeConstants.User.NAME_EXISTS, "");
        }

        // register user
        int uid = userDAO.insert(name, MD5Util.getMD5String(password));
        UserStatusCO userStatusCO = userStatusHelper.initUserStatus(uid, name);
        userStatusDAO.insert(
                userStatusCO.getId(),
                userStatusCO.getName(),
                userStatusCO.getGold(),
                userStatusCO.getFood(),
                userStatusCO.getCash(),
                userStatusCO.getHonor(),
                userStatusCO.getLevel(),
                userStatusCO.getXp(),
                userStatusCO.getSoul(),
                userStatusCO.getFightForce());

        // init farm castle timer
        farmHelper.create(uid);
        castleHelper.create(uid);
        userPackageHelper.createDefaultPackage(uid);
        // 初始化任务数据
        userTaskHelper.create(uid);
        // 创建第一个人物
        userRoleHelper.addRoleForRegister(uid);
        // 在包裹中放入一些基础的物品
        userPackageHelper.addSomeEquipForRegister(uid);
        // 创建tavern
        tavernHelper.create(uid);
        // 创建新的team
        teamHelper.createANewTeam(uid);

        userStatusCache.set(userStatusCO);

        return uid;
    }

}
