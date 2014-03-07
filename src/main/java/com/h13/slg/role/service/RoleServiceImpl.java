package com.h13.slg.role.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.event.EventType;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.role.helper.UserRoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
