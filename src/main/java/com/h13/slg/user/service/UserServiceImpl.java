package com.h13.slg.user.service;

import com.google.common.collect.Lists;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.config.fetcher.LevelConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.equip.co.UserEquipCO;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.skill.co.UserRoleSkillCO;
import com.h13.slg.skill.helper.RoleSkillHelper;
import com.h13.slg.skill.helper.UserZuLingHelper;
import com.h13.slg.skill.vo.RoleSkillVO;
import com.h13.slg.skill.vo.UserRoleSkillVO;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.tavern.vo.TavernRoleVO;
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
import com.h13.slg.user.vo.UserEquipVO;
import com.h13.slg.user.vo.UserRoleVO;
import com.h13.slg.user.vo.UserStatusVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    TavernHelper tavernHelper;
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
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    RoleSkillHelper roleSkillHelper;
    @Autowired
    UserZuLingHelper userZuLingHelper;

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

        // team list info
        UserTeamCO userTeamCO = teamHelper.get(uid);
        List<Integer> teamRoleList = Lists.newLinkedList();
        for (Integer urid : userTeamCO.getData()) {
            if (urid == SlgConstants.Role.NO_ROLE) {
                teamRoleList.add(SlgConstants.Role.NO_ROLE);
                continue;
            }
            teamRoleList.add(urid);
        }

        // role list info
        List<UserRoleCO> userRoleList = userRoleHelper.getUserRoleList(uid);
        List<UserRoleVO> userRoleVOList = Lists.newLinkedList();
        for (UserRoleCO ur : userRoleList) {
            UserRoleVO vo = new UserRoleVO();
            SlgBeanUtils.copyProperties(vo, ur);
            userRoleVOList.add(vo);
        }

        // equip list
        List<UserEquipCO> userEquipList = userEquipHelper.getUserEquips(uid);
        List<UserEquipVO> userEquipVOList = Lists.newLinkedList();
        for (UserEquipCO ue : userEquipList) {
            UserEquipVO vo = new UserEquipVO();
            SlgBeanUtils.copyProperties(vo, ue);
            userEquipVOList.add(vo);
        }

        // equip list package (no wear equip)
        UserPackageCO userPackageCO = userPackageHelper.get(uid);
        List<Integer> equipListPackage = userPackageCO.getEquip();
        Map<String, Integer> materialMapPackage = userPackageCO.getMaterial();
        Map<String, Integer> skillMapPackage = userPackageCO.getSkill();


        List<UserRoleSkillCO> roleSkillList = roleSkillHelper.get(uid);
        List<UserRoleSkillVO> roleSkillVOList = Lists.newLinkedList();
        for (UserRoleSkillCO co : roleSkillList) {
            UserRoleSkillVO vo = new UserRoleSkillVO();
            SlgBeanUtils.copyProperties(vo, co);
            roleSkillVOList.add(vo);
        }

        // tavern
        TavernCO tavernCO = tavernHelper.get(uid);
        List<TavernRoleVO> list = Lists.newArrayList();
        for (TavernRoleCO tavernRoleCO : tavernCO.getRoleList()) {
            TavernRoleVO tavernRoleVO = new TavernRoleVO();
            try {
                BeanUtils.copyProperties(tavernRoleVO, tavernRoleCO);
            } catch (Exception e) {
                throw new RequestErrorException(e);
            }
            tavernRoleVO.setRoleName(roleConfigFetcher.get(tavernRoleCO.getId() + "").getName());
            list.add(tavernRoleVO);
        }

        // zuling
        List<RoleSkillVO> zulingRoleSkillList  = userZuLingHelper.load(uid);

        SlgData slgData = SlgData.getData()
                .add("userStatus", userStatusVO)
                .add("teamList", teamRoleList)
                .add("userRoleList", userRoleVOList)
                .add("userEquipList", userEquipVOList)
                .add("userRoleSkillList", roleSkillVOList)
                .add("equipListPackage", equipListPackage)
                .add("materialMapPackage", materialMapPackage)
                .add("skillMapPackage", skillMapPackage)
                .add("zulingRoleSkillList",zulingRoleSkillList)
                .add("tavernList", list);
        return slgData;
    }


}
