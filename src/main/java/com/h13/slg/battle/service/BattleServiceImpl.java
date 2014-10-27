package com.h13.slg.battle.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.h13.slg.battle.fight.FightResult;
import com.h13.slg.battle.helper.FightHelper;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.battle.vo.*;
import com.h13.slg.config.co.BattleCO;
import com.h13.slg.config.fetcher.BattleConfigFetcher;
import com.h13.slg.config.fetcher.MonsterConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:23
 * To change this template use File | Settings | File Templates.
 */
@Service("BattleService")
public class BattleServiceImpl implements BattleService {
    @Autowired
    TeamHelper teamHelper;
    @Autowired
    FightHelper fightHelper;
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;
    @Autowired
    BattleConfigFetcher battleConfigFetcher;
    @Autowired
    MonsterConfigFetcher monsterConfigFetcher;

    @Override
    public SlgData saveTeam(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException {
        int uid = requestDTO.getUid();
        String team = JSON.toJSONString(requestDTO.getArgs().get("team"));
        teamHelper.saveTeam(uid, team);
        return SlgData.getData();
    }


    @Override
    public SlgData pve(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException {
        int uid = requestDTO.getUid();
        long battleId = new Long(requestDTO.getArgs().get("battleId") + "");
        FightResult fightResult = fightHelper.pve(uid, battleId);
        return SlgData.getData().add("battle", fightResult);
    }

    @Override
    public SlgData pveTeam(SlgRequestDTO request) throws RequestFatalException {
        int battleId = new Integer(request.getArgs().get("battleId").toString());
        PVETeamVO pveTeamVO = new PVETeamVO();
        List<PVERoleInTeamVO> roleList = Lists.newLinkedList();
        BattleCO battleCO = battleConfigFetcher.get(battleId + "");
        for (int i = 0; i < 9; i++) {
            PVERoleInTeamVO pveRoleInTeamVO = null;
            String p = SlgBeanUtils.getProperty(battleCO, "pos" + i);
            if (Strings.isNullOrEmpty(p)) {
                pveRoleInTeamVO = PVERoleInTeamVO.EmptyObject();
            } else {
                pveRoleInTeamVO = new PVERoleInTeamVO();
                pveRoleInTeamVO.setRid(new Integer(p));
                String name = monsterConfigFetcher.get(p).getName();
                pveRoleInTeamVO.setName(name);
            }
            roleList.add(pveRoleInTeamVO);
        }
        pveTeamVO.setData(roleList);

        // 需要获得award
        BattleAwardVO battleAwardVO = new BattleAwardVO();
        SlgBeanUtils.copyProperties(battleAwardVO, battleCO);

        return SlgData.getData().add("pveTeam", pveTeamVO).add("award", battleAwardVO);
    }


}

