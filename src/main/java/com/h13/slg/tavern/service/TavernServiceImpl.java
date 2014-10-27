package com.h13.slg.tavern.service;

import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.TavernConfigCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.config.fetcher.TavernConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.tavern.vo.EnrollUserRoleVO;
import com.h13.slg.tavern.vo.InviteTavernVO;
import com.h13.slg.tavern.vo.TavernRoleVO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-12
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
@Service("TavernService")
public class TavernServiceImpl implements TavernService {
    @Autowired
    TavernHelper tavernHelper;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;
    @Autowired
    GlobalConfigFetcher globalConfigFetcher;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    TavernConfigFetcher tavernConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;


    @Override
    public SlgData leave(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {
        int uid = request.getUid();
        TavernCO co = tavernHelper.get(uid);
        if (co.getRoleList().size() == 0) {
            throw new RequestUnexpectedException(CodeConstants.Tavern.TAVERN_IS_EMPTY, "size=0");
        }
        int gold = 0;
        List<TavernRoleCO> list = co.getRoleList();
        for (TavernRoleCO tavernRoleCO : list) {
            long roleId = tavernRoleCO.getId();
            RoleCO roleCO = roleConfigFetcher.get(roleId + "");
            String quality = roleCO.getQuantity();
            if (quality.equals("orange")) {
                gold += globalConfigFetcher.getIntValue("RoleToSoulorange");
            } else if (quality.equals("purple")) {
                gold += globalConfigFetcher.getIntValue("RoleToSoulpurple");
            } else if (quality.equals("blue")) {
                gold += globalConfigFetcher.getIntValue("RoleToSoulblue");
            } else {
                gold += globalConfigFetcher.getIntValue("RoleToSoulwhite");
            }
        }
        co.setRoleList(new LinkedList<TavernRoleCO>());
        tavernHelper.update(co);
        // add gold
        userStatusHelper.subtractGold(uid, gold);
        return SlgData.getData().add("gold", gold);
    }

    @Override
    public SlgData invite(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException {
        int uid = request.getUid();
        if (!tavernHelper.checkTavernIsReady(uid)) {
            throw new RequestUnexpectedException(CodeConstants.Tavern.TAVERN_IS_FULL, "tavern is full");
        }

        int cost = 0;
        InviteTavernVO vo = new InviteTavernVO();
        List<TavernRoleCO> tList = new LinkedList<TavernRoleCO>();
        List<TavernRoleVO> list = new LinkedList<TavernRoleVO>();
        // 一共邀请10个人
        int level = 1;
        for (int i = 0; i < 10; i++) {
            List<Object> person = new LinkedList<Object>();
            TavernConfigCO tavernConfigCO = tavernConfigFetcher.get(level + "");
            int gold = tavernHelper.inviteGold(tavernConfigCO);
            cost += gold;
            String quality = tavernHelper.randomQuality(tavernConfigCO);
            level = tavernHelper.randomNextLevel(level, tavernConfigCO);
            long size = roleConfigFetcher.getZhaoxianSize(quality);
            int roleId = roleConfigFetcher.getFromZhaoxian(tavernHelper.randomId(size), quality);
            String roleName = roleConfigFetcher.get(roleId + "").getName();

            TavernRoleVO tavernRoleVO = new TavernRoleVO();
            tavernRoleVO.setGold(gold);
            tavernRoleVO.setId(roleId);
            tavernRoleVO.setRoleName(roleName);

            list.add(tavernRoleVO);
            TavernRoleCO tco = new TavernRoleCO();
            tco.setId(roleId);
            tco.setStatus(SlgConstants.TavernConstants.DEFAULT);
            tList.add(tco);
        }
        TavernCO tavern = new TavernCO();
        tavern.setId(uid);
        tavern.setRoleList(tList);
        tavernHelper.update(tavern);

        // 减掉cost的金币
        userStatusHelper.subtractGold(uid, cost);
        return SlgData.getData().add("process", vo);
    }


    @Override
    public SlgData enroll(SlgRequestDTO request) throws RequestFatalException {
        int uid = request.getUid();
        int pos = new Integer(request.getArgs().get("pos") + "");
        EnrollUserRoleVO vo = new EnrollUserRoleVO();
        // enroll
        TavernCO tavernCO = tavernHelper.get(uid);
        TavernRoleCO tavernRoleCO = tavernCO.getRoleList().get(pos);
        tavernRoleCO.setStatus(1);
        tavernHelper.update(tavernCO);
        long roleId = tavernRoleCO.getId();
        RoleCO roleCO = roleConfigFetcher.get(roleId + "");
        UserRoleCO userRoleCO = userRoleHelper.addUserRole(uid, roleCO.getId());
        SlgBeanUtils.copyProperties(vo, userRoleCO);
        return SlgData.getData().add("role", vo);
    }

}
