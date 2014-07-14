package com.h13.slg.user.service;

import com.h13.slg.user.co.AuthCO;
import com.h13.slg.user.hepler.AuthHelper;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.core.*;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.user.UserRequestKeyConstants;
import com.h13.slg.user.UserResponseKeyConstants;
import com.h13.slg.user.cache.UserStatusCache;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.dao.UserDAO;
import com.h13.slg.user.dao.UserStatusDAO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.hepler.FarmHelper;
import com.h13.slg.user.hepler.UserHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import com.h13.slg.user.vo.UserStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserHelper userHelper;
    @Autowired
    AuthHelper authHelper;

    @Override
    public SlgData login(SlgRequestDTO request) throws RequestErrorException {
        String name = (String) request.getArgs().get(UserRequestKeyConstants.REQUEST_NAME);
        String password = (String) request.getArgs().get(UserRequestKeyConstants.REQEUST_PASSWORD);

        // 尝试登陆
        int uid = userHelper.login(name, password);

        // 获取用户的相关信息
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        AuthCO authCO = authHelper.createAuth(uid);
        long farmTimer = farmHelper.getFarmInfo(uid).getTimer();
        long castleTimer = castleHelper.getCastleInfo(uid).getTimer();

        UserStatusVO userStatusVO = new UserStatusVO();
        SlgBeanUtils.copyProperties(userStatusVO, userStatusCO);
        userStatusVO.setCastleTimer(castleTimer);
        userStatusVO.setFarmTimer(farmTimer);
        userStatusVO.setUid(uid);
        userStatusVO.setName(name);

        SlgData slgData = SlgData.getData()
                .add(UserResponseKeyConstants.USER_STATUS, userStatusVO)
                .add(UserResponseKeyConstants.AUTH_TIME, authCO.getAuthTime())
                .add(UserResponseKeyConstants.AUTH_KEY, authCO.getAuthKey());
        return slgData;

    }

    @Override
    public SlgData register(SlgRequestDTO request) throws RequestErrorException {

        String name = request.getArgs().get(UserRequestKeyConstants.REQUEST_NAME).toString();
        String password = request.getArgs().get(UserRequestKeyConstants.REQEUST_PASSWORD).toString();

        int uid = userHelper.register(name, password);

        SlgData slgData = SlgData.getData()
                .add(UserResponseKeyConstants.ID, uid);
        return slgData;
    }

    @Override
    public SlgData getInfo(SlgRequestDTO request) throws RequestErrorException {
        int uid = request.getUid();
        UserStatusCO userStatusCO = userStatusHelper.getUserStatus(uid);
        long farmTimer = farmHelper.getFarmInfo(uid).getTimer();
        long castleTimer = castleHelper.getCastleInfo(uid).getTimer();


        UserStatusVO userStatusVO = new UserStatusVO();
        SlgBeanUtils.copyProperties(userStatusVO, userStatusCO);
        userStatusVO.setCastleTimer(castleTimer);
        userStatusVO.setFarmTimer(farmTimer);
        userStatusVO.setUid(uid);

        SlgData slgData = SlgData.getData()
                .add(UserResponseKeyConstants.USER_STATUS, userStatusVO);
        return slgData;
    }


}
