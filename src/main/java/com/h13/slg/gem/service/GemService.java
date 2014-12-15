package com.h13.slg.gem.service;

import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.gem.helper.GemHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-2
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
@Service("GemService")
public class GemService {

    @Autowired
    GemHelper gemHelper;


    public SlgData join(SlgRequestDTO request) throws RequestUnexpectedException {
        int uid = request.getUid();
        int gemId = new Integer(request.getArgs().get("gemId") + "");

        gemHelper.join(uid, gemId);

        return SlgData.getData();
    }


}
