package com.h13.slg.role.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.cache.RoleCache;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.fetcher.EquipConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.event.EventType;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.user.vo.UserRoleVO;
import com.h13.slg.skill.helper.RoleSkillHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-5
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    UserRoleHelper userRoleHelper;
    @Autowired
    UserEventHelper userEventHelper;

    @Autowired
    RoleCache roleCache;
    @Autowired
    EquipConfigFetcher equipConfigFetcher;
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    RoleSkillHelper roleSkillHelper;


    @Override
    public SlgData wear(SlgRequestDTO requestDTO) throws RequestErrorException {
        int uid = requestDTO.getUid();
        int ueid = new Integer(requestDTO.getArgs().get("ueid").toString());
        int urid = new Integer(requestDTO.getArgs().get("urid").toString());

        userRoleHelper.wear(uid, urid, ueid);

        userEventHelper.addEvent(uid, EventType.WearEquip, null);

        return SlgData.getData();
    }

    @Override
    public SlgData takeOff(SlgRequestDTO requestDTO) throws RequestErrorException {
        long uid = requestDTO.getUid();
        long ueid = new Long(requestDTO.getArgs().get("ueid").toString());
        long urid = new Long(requestDTO.getArgs().get("urid").toString());
        userRoleHelper.takeOff(uid, urid, ueid);
        return SlgData.getData();
    }

}
