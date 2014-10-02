package com.h13.slg.tavern.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.tavern.vo.EnrollUserRoleVO;
import com.h13.slg.tavern.vo.InviteTavernVO;
import com.h13.slg.tavern.vo.TavernRoleVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
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

    @Override
    public SlgData leave(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        int gold = tavernHelper.leave(uid);
        return SlgData.getData().add("gold", gold);
    }

    @Override
    public SlgData invite(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        List<TavernRoleVO> vo = tavernHelper.invite(uid);
        return SlgData.getData().add("process", vo);
    }


    @Override
    public SlgData enroll(SlgRequestDTO request) throws RequestErrorException {
        int uid = request.getUid();
        int pos = new Integer(request.getArgs().get("pos") + "");
        EnrollUserRoleVO vo = tavernHelper.enroll(uid, pos);
        return SlgData.getData().add("role", vo);
    }

    @Override
    public SlgData get(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
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
        return SlgData.getData().add("list", list);
    }
}
