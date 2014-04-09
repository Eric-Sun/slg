package com.h13.slg.tavern.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.tavern.helper.TavernHelper;
import com.h13.slg.tavern.vo.EnrollUserRoleVO;
import com.h13.slg.tavern.vo.InviteTavernVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public SlgData leave(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        int soul = tavernHelper.leave(uid);
        return SlgData.getData().add("soul", soul);
    }

    @Override
    public SlgData invite(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        InviteTavernVO vo = tavernHelper.invite(uid);
        return SlgData.getData().add("process", vo);
    }

    @Override
    public SlgData enroll(SlgRequestDTO request) throws RequestErrorException {
        long uid = request.getUid();
        int pos = new Integer(request.getArgs().get("pos") + "");
        EnrollUserRoleVO vo = tavernHelper.enroll(uid, pos);
        return SlgData.getData().add("role", vo);
    }
}
