package com.h13.slg.user.service;

import com.google.common.collect.Lists;
import com.h13.slg.battle.TeamConstants;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.config.fetcher.LevelConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.user.co.AuthCO;
import com.h13.slg.user.hepler.AuthHelper;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.core.*;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.task.helper.UserTaskHelper;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.hepler.FarmHelper;
import com.h13.slg.user.hepler.UserHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import com.h13.slg.user.vo.UserRoleSimpleVO;
import com.h13.slg.user.vo.UserStatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;

    @Autowired
    LevelConfigFetcher levelConfigFetcher;

    @Override
    public SlgData login(SlgRequestDTO request) throws RequestErrorException {
        String name = (String) request.getArgs().get("name");
        String password = (String) request.getArgs().get("password");

        // 尝试登陆
        int uid = userHelper.login(name, password);
        AuthCO authCO = authHelper.createAuth(uid);

        SlgData slgData = SlgData.getData()
                .add("authTime", authCO.getAuthTime())
                .add("authKey", authCO.getAuthKey())
                .add("uid", uid);
        return slgData;

    }

    @Override
    public SlgData register(SlgRequestDTO request) throws RequestErrorException {

        String name = request.getArgs().get("name").toString();
        String password = request.getArgs().get("password").toString();

        int uid = userHelper.register(name, password);

        SlgData slgData = SlgData.getData()
                .add("id", uid);
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
        userStatusVO.setLevelUpXp(levelConfigFetcher.get(userStatusCO.getLevel() + "").getXp());
        UserTeamCO userTeamCO = teamHelper.get(uid);
        List<UserRoleSimpleVO> teamRoleList = Lists.newLinkedList();
        for (Integer urid : userTeamCO.getData()) {
            if (urid == TeamConstants.NO_ROLE_IN_TEAM)
                continue;
            UserRoleSimpleVO vo = new UserRoleSimpleVO();
            String roleName = userRoleHelper.getUserRole(uid, urid).getRoleName();
            vo.setName(roleName);
            vo.setId(urid);
            teamRoleList.add(vo);
        }

        SlgData slgData = SlgData.getData()
                .add("userStatus", userStatusVO)
                .add("teamRoleList", teamRoleList);
        return slgData;
    }


}
