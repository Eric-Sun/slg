package com.h13.slg.battle.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.battle.helper.FightHelper;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.battle.vo.UserRoleInTeamVO;
import com.h13.slg.battle.vo.UserTeamVO;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-24
 * Time: 下午11:21
 * To change this template use File | Settings | File Templates.
 */
@Service("TeamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamHelper teamHelper;
    @Autowired
    FightHelper fightHelper;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;


    @Override
    public SlgData getTeam(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();

        UserTeamCO userTeamCO = teamHelper.get(uid);
        UserTeamVO userTeamVO = new UserTeamVO();
        for (Integer id : userTeamCO.getData()) {
            UserRoleInTeamVO userRoleInTeamVO = new UserRoleInTeamVO();
            if (id == 0) {
                userRoleInTeamVO.setName("");
                userRoleInTeamVO.setUrid(id);
                userTeamVO.getData().add(userRoleInTeamVO);
                continue;
            }

            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, id);
            userRoleInTeamVO.setName(roleConfigFetcher.get(userRoleCO.getRoleId() + "").getName());
            userRoleInTeamVO.setUrid(id);
            userTeamVO.getData().add(userRoleInTeamVO);
        }
        userTeamVO.setLeader(userTeamCO.getLeader());
        return SlgData.getData().add("userTeam", userTeamVO);
    }

    @Override
    public SlgData getUserRoleList(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        UserTeamCO userTeamCO = teamHelper.get(uid);
        List<Integer> teamSet = Lists.newArrayList(userTeamCO.getData());
        List<UserRoleCO> roleList = userRoleHelper.getUserRoleList(uid);
        Set<UserRoleCO> userRoleSet = Sets.newHashSet();
        for (UserRoleCO userRoleCO : roleList) {
            if (!teamSet.contains(new Integer(userRoleCO.getId() + ""))) {
                userRoleCO.setRoleName(roleConfigFetcher.get(userRoleCO.getRoleId() + "").getName());
                userRoleSet.add(userRoleCO);
            }
        }
        return SlgData.getData().add("userRoleList", userRoleSet);

    }

    @Override
    public SlgData updatePos(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        int pos = new Integer(request.getArgs().get("pos").toString());
        int urid = new Integer(request.getArgs().get("urid").toString());
        teamHelper.updatePos(uid, pos, urid);
        return SlgData.getData();
    }

    @Override
    public SlgData deletePos(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        int pos = new Integer(request.getArgs().get("pos").toString());
        teamHelper.deletePos(uid, pos);
        return SlgData.getData();
    }


    @Override
    public SlgData updateLeader(SlgRequestDTO request) throws RequestErrorException {

        int uid = request.getUid();
        int urid = new Integer(request.getArgs().get("urid") + "");
        teamHelper.updateLeader(uid, urid);
        return SlgData.getData();
    }
}
