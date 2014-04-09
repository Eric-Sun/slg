package com.h13.slg.role.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.helper.SkillHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-4-9
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */
@Service("SkillService")
public class SkillSerivceImpl implements SkillService {
    @Autowired
    SkillHelper skillHelper;

    @Override
    public SlgData upgrade(SlgRequestDTO slgRequestDTO) throws RequestErrorException {
        long uid = slgRequestDTO.getUid();
        long urid = new Long(slgRequestDTO.getArgs().get("urid") + "");
        int skill = new Integer(slgRequestDTO.getArgs().get("skill") + "");
        int nextSkillLevel = skillHelper.upgrade(uid, urid, skill);
        return SlgData.getData().add("nextSkillLevel", nextSkillLevel);
    }
}
