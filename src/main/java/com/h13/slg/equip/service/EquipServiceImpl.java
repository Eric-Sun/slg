package com.h13.slg.equip.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.RoleRequestKeyConstants;
import com.h13.slg.role.RoleResponseKeyConstants;
import com.h13.slg.equip.help.EquipHelper;
import com.h13.slg.role.vo.EquipMakeVO;
import com.h13.slg.role.vo.EquipStrengthenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
@Service("EquipService")
public class EquipServiceImpl implements EquipService {

    @Autowired
    EquipHelper equipHelper;

    @Override
    public SlgData strengthen(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        long ueId = new Long(request.getArgs().get(RoleRequestKeyConstants.UEID));

        EquipStrengthenVO vo = equipHelper.strengthen(uid, ueId);
        return SlgData.getData().add(RoleResponseKeyConstants.STRENGTH, vo.getStrength())
                .add(RoleResponseKeyConstants.GOLD, vo.getGold());

    }

    @Override
    public SlgData make(SlgRequestDTO request) throws RequestErrorException {

        long uid = request.getUid();
        long ueId = new Long(request.getArgs().get(RoleRequestKeyConstants.UEID));
        int lucky = new Integer(request.getArgs().get(RoleRequestKeyConstants.LUCKY));
        EquipMakeVO vo = equipHelper.make(uid, lucky, ueId);

        return SlgData.getData().add(RoleResponseKeyConstants.LEVEL, vo.getLevel())
                .add(RoleResponseKeyConstants.MATERIAL, vo.getMap());
    }
}
