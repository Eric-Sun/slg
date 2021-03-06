package com.h13.slg.battle.helper;

import com.alibaba.fastjson.JSON;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.battle.dao.UserTeamDAO;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TeamHelper {

    @Autowired
    UserTeamDAO userTeamDAO;

    @Autowired
    UserRoleHelper userRoleHelper;


    public UserTeamCO get(long uid) {
        return userTeamDAO.get(uid);
    }


    public void updateTeam(UserTeamCO userTeamCO) {
        userTeamDAO.update(userTeamCO.getId(), userTeamCO.getData(), userTeamCO.getLeader());
    }


    public void createANewTeam(long uid) {
        userTeamDAO.insert(uid, new LinkedList<Integer>() {{
            for (int i = 0; i < 9; i++) {
                add(SlgConstants.Role.NO_ROLE);
            }
        }});
    }

    /**
     * 保存前端的team内容
     *
     * @param uid
     * @param team
     */
    public void saveTeam(int uid, String team) throws RequestFatalException, RequestUnexpectedException {

        final Map teamDataMap = JSON.parseObject(team, Map.class);
        List<Integer> teamDataList = new LinkedList<Integer>() {{
            for (int i = 0; i < 9; i++) {
                add(new Integer(teamDataMap.get(i + "") + ""));
            }
        }};

        UserTeamCO userTeamCO = new UserTeamCO();
        userTeamCO.setData(teamDataList);
        userTeamCO.setId(uid);
        for (Integer urid : teamDataList) {
            if (urid == 0)
                continue;
            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
            if (userRoleCO.getUid() != uid) {
                SlgLogger.error(SlgLoggerEntity.p("team", "saveTeam", uid, "urid is not yours")
                        .addParam("urid", urid)
                        .addParam("urid-uid", userRoleCO.getUid()));
                throw new RequestFatalException(CodeConstants.SYSTEM.COMMON_ERROR, "");
            }
        }
        updateTeam(userTeamCO);
        SlgLogger.info(SlgLoggerEntity.p("team", "saveTeam", uid, "ok")
                .addParam("teamInfo", team));
    }


    public void updatePos(int uid, int pos, int urid) throws RequestFatalException {
        // 检查urid是否是你自己的
        boolean b = userRoleHelper.checkUserRole(uid, urid);
        if (!b)
            throw new RequestFatalException(CodeConstants.Role.DONT_HAVE_THIS_USER_ROLE, "");
        UserTeamCO userTeamCO = get(uid);
        // 已经存在在team中
        if (userTeamCO.getData().contains(urid)) {
            throw new RequestFatalException(CodeConstants.Team.USER_ROLE_HAVE_IN_TEAM, "");
        }
        // 对应的位置已经有userrole
        if (userTeamCO.getData().get(pos) != SlgConstants.Role.NO_ROLE) {
            throw new RequestFatalException(CodeConstants.Team.POS_HAVE_USER_ROLE, "");
        }

        userTeamCO.getData().set(pos, urid);
        updateTeam(userTeamCO);
    }

    public void deletePos(long uid, int pos) throws RequestFatalException {
        UserTeamCO userTeamCO = get(uid);
        // 对应的位置已经有userrole
        if (userTeamCO.getData().get(pos) == SlgConstants.Role.NO_ROLE) {
            throw new RequestFatalException(CodeConstants.Team.POST_IS_NO_USER_ROLE, "");
        }
        userTeamCO.getData().set(pos, SlgConstants.Role.NO_ROLE);
        updateTeam(userTeamCO);
    }


    public void updateLeader(int uid, int urid) throws RequestFatalException {


        boolean b = userRoleHelper.checkUserRole(uid, urid);
        if (!b)
            throw new RequestFatalException(CodeConstants.Role.DONT_HAVE_THIS_USER_ROLE,
                    String.format("uid=%s,urid=%s", uid, urid));

        UserTeamCO userTeamCO = get(uid);
        userTeamCO.setLeader(urid);
        updateTeam(userTeamCO);

    }
}
