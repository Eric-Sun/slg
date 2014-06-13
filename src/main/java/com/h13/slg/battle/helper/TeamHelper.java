package com.h13.slg.battle.helper;

import com.alibaba.fastjson.JSON;
import com.h13.slg.battle.TeamConstants;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.battle.dao.UserTeamDAO;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        userTeamDAO.update(userTeamCO.getId(), userTeamCO.getData());
    }


    public void createANewTeam(long uid) {
        userTeamDAO.insert(uid, new LinkedList<Long>() {{
            for (int i = 0; i < 9; i++) {
                add(TeamConstants.NO_ROLE_IN_TEAM);
            }
        }});
    }

    /**
     * 保存前端的team内容
     *
     * @param uid
     * @param team
     */
    public void saveTeam(long uid, String team) throws RequestErrorException {

        final Map teamDataMap = JSON.parseObject(team, Map.class);
        List<Long> teamDataList = new LinkedList<Long>() {{
            for (int i = 0; i < 9; i++) {
                add(new Long(teamDataMap.get(i + "") + ""));
            }
        }};

        UserTeamCO userTeamCO = new UserTeamCO();
        userTeamCO.setData(teamDataList);
        userTeamCO.setId(uid);
        for (Long urid : teamDataList) {
            if (urid == 0)
                continue;
            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
            if (userRoleCO.getUid() != uid) {
                SlgLogger.error(SlgLoggerEntity.p("team", "saveTeam", uid, "urid is not yours")
                        .addParam("urid", urid)
                        .addParam("urid-uid", userRoleCO.getUid()));
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
        }
        updateTeam(userTeamCO);
        SlgLogger.info(SlgLoggerEntity.p("team", "saveTeam", uid, "ok")
                .addParam("teamInfo", team));
    }


}
