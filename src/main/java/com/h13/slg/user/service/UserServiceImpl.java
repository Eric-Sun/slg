package com.h13.slg.user.service;

import com.h13.slg.config.GlobalKeyConstants;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.core.*;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MD5Util;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.user.RequestKeyConstants;
import com.h13.slg.user.ResponseKeyConstants;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserDAO;
import com.h13.slg.user.dao.UserStatusDAO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.hepler.FarmHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import com.h13.slg.user.vo.CastleVO;
import com.h13.slg.user.vo.FarmVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-13
 * Time: 下午8:05
 * To change this template use File | Settings | File Templates.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private GlobalConfigFetcher globalConfigFetcher;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserStatusDAO userStatusDAO;
    @Autowired
    private UserStatusCache userStatusCache;
    @Autowired
    private UserRoleHelper userRoleHelper;

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

    @Override
    public SlgData login(SlgRequestDTO request) throws RequestErrorException {
        String name = (String)request.getArgs().get(RequestKeyConstants.REQUEST_NAME);
        String password = (String)request.getArgs().get(RequestKeyConstants.REQEUST_PASSWORD);

        int userId = userDAO.login(name, MD5Util.getMD5String(password));

        if (userId == -1) {
            // 登陆失败,用户密码错误
            throw new RequestErrorException(ErrorCodeConstants.User.NAME_OR_PASSWORD_ERROR, "");
        }

        // 获得farm,castle 的上一次收获的时间
        long farmTimer = farmHelper.getFarmInfo(userId).getTimer();
        long castleTimer = castleHelper.getCastleInfo(userId).getTimer();
        FarmVO farmVO = new FarmVO(farmTimer);
        CastleVO castleVO = new CastleVO(castleTimer);

        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(userId);
        SlgData slgData = SlgData.getData()
                .add(ResponseKeyConstants.RESPONSE_USER_STATUS, userStatusCO)
                .add(ResponseKeyConstants.RESPONSE_USER_FARM, farmVO)
                .add(ResponseKeyConstants.RESPONSE_USER_CASTLE, castleVO);
        return slgData;

    }

    @Override
    public SlgData register(SlgRequestDTO request) throws RequestErrorException {

        String name = request.getArgs().get(RequestKeyConstants.REQUEST_NAME).toString();
        String password = request.getArgs().get(RequestKeyConstants.REQEUST_PASSWORD).toString();

        // check name exists?
        if (!userDAO.check(name)) {
            SlgLogger.info(SlgLoggerEntity.p(MOD, "register", -1, "name exists").addParam("name", name));
            throw new RequestErrorException(ErrorCodeConstants.User.NAME_EXISTS, "");
        }


        // register user
        int userId = userDAO.insert(name, MD5Util.getMD5String(password));
        UserStatusCO userStatusCO = initUserStatus(userId, name);
        userStatusDAO.insert(
                userStatusCO.getId(),
                userStatusCO.getName(),
                userStatusCO.getGold(),
                userStatusCO.getFood(),
                userStatusCO.getCash(),
                userStatusCO.getHonor(),
                userStatusCO.getLevel(),
                userStatusCO.getXp(),
                userStatusCO.getSoul());

        // init farm castle timer
        farmHelper.create(userId);
        castleHelper.create(userId);
        userPackageHelper.create(userId);
        // 初始化任务数据
        userTaskHelper.create(userId);
        // 创建第一个人物
        userRoleHelper.addRoleForRegister(userId);
        // 在包裹中放入一些基础的物品
        userPackageHelper.addSomeEquipForRegister(userId);

        userStatusCache.set(userStatusCO);
        SlgLogger.info(SlgLoggerEntity.p(MOD, "register", -1, "name not exists").addParam("name", name));
        SlgData slgData = SlgData.getData()
                .add(ResponseKeyConstants.RESPONSE_ID, userId);
        return slgData;
    }


    private UserStatusCO initUserStatus(long userId, String name) {

        UserStatusCO userStatusCO = new UserStatusCO();
        userStatusCO.setName(name);
        userStatusCO.setCash(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_CASH));
        userStatusCO.setFood(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_FOOD));
        userStatusCO.setGold(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_GOLD));
        userStatusCO.setHonor(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_HORNOR));
        userStatusCO.setLevel(globalConfigFetcher.getIntValue(GlobalKeyConstants.DEFAULT_NEW_USER_LEVEL));

        userStatusCO.setFood(500000);
        userStatusCO.setGold(50000000);
        userStatusCO.setHonor(500000);
        userStatusCO.setCash(50000);

        userStatusCO.setId(userId);
        userStatusCO.setXp(0);
        userStatusCO.setSoul(0);
        return userStatusCO;
    }
}
