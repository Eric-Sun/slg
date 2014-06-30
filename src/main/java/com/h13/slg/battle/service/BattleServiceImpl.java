package com.h13.slg.battle.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.h13.slg.battle.fight.FightResult;
import com.h13.slg.battle.helper.FightHelper;
import com.h13.slg.battle.helper.TeamHelper;
import com.h13.slg.battle.vo.BattleCastleInfoVO;
import com.h13.slg.battle.vo.BattleRoleInfoVO;
import com.h13.slg.battle.vo.PVERoleInTeamVO;
import com.h13.slg.battle.vo.PVETeamVO;
import com.h13.slg.config.co.BattleCO;
import com.h13.slg.config.fetcher.BattleConfigFetcher;
import com.h13.slg.config.fetcher.MonsterConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.role.helper.UserRoleHelper;
import org.apache.commons.beanutils.BeanUtils;
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
    public SlgData saveTeam(SlgRequestDTO requestDTO) throws RequestErrorException {
        long uid = requestDTO.getUid();
        String team = JSON.toJSONString(requestDTO.getArgs().get("team"));
        teamHelper.saveTeam(uid, team);
        return SlgData.getData();
    }


    @Override
    public SlgData pve(SlgRequestDTO requestDTO) throws RequestErrorException {
        long uid = requestDTO.getUid();
        long battleId = new Long(requestDTO.getArgs().get("battleId") + "");
        FightResult fightResult = fightHelper.pve(uid, battleId);
        return SlgData.getData().add("battle", fightResult);
    }

    @Override
    public SlgData battleList(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        List<String> list = Lists.newLinkedList();
        list.add("黄巾之乱");
        list.add("袁绍挥师");
        list.add("董卓之乱");
        list.add("袁术称帝");
        list.add("东吴霸主");
        list.add("荆州夺主");
        list.add("南蛮入侵");
        list.add("蜀中之王");
        list.add("西凉铁骑");


        return SlgData.getData().add("data", list);
    }

    @Override
    public SlgData castleList(SlgRequestDTO request) throws RequestErrorException {
        int battleId = new Integer(request.getArgs().get("battleId") + "");

        Map<String, Map<String, List<Integer>>> map = battleConfigFetcher.getMap();
        Set<String> castleList = map.get(battleId + "").keySet();

        List<BattleCastleInfoVO> list = Lists.newArrayList();
        for (String castle : castleList) {
            BattleCastleInfoVO battleCastleInfoVO = new BattleCastleInfoVO();
            battleCastleInfoVO.setName(castle);
            List<Integer> idList = map.get(battleId + "").get(castle);
            List<BattleRoleInfoVO> roleList = Lists.newLinkedList();
            for (Integer id : idList) {
                String name = battleConfigFetcher.get(id + "").getName();
                BattleRoleInfoVO battleRoleInfoVO = new BattleRoleInfoVO();
                battleRoleInfoVO.setId(id);
                battleRoleInfoVO.setName(name);
                roleList.add(battleRoleInfoVO);
            }
            Collections.sort(roleList, new Comparator<BattleRoleInfoVO>() {
                @Override
                public int compare(BattleRoleInfoVO battleRoleInfoVO, BattleRoleInfoVO battleRoleInfoVO2) {
                    if (battleRoleInfoVO.getId() > battleRoleInfoVO2.getId())
                        return 1;
                    else
                        return -1;
                }
            });
            battleCastleInfoVO.setList(roleList);
            list.add(battleCastleInfoVO);
        }
        Collections.sort(list, new Comparator<BattleCastleInfoVO>() {
            @Override
            public int compare(BattleCastleInfoVO tmp, BattleCastleInfoVO tmp2) {
                if (tmp.getList().get(0).getId() > tmp2.getList().get(0).getId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        return SlgData.getData().add("castleList", list);
    }

    @Override
    public SlgData pveTeam(SlgRequestDTO request) throws RequestErrorException {
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
        return SlgData.getData().add("pveTeam", pveTeamVO);
    }


}

