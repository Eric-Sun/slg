package com.h13.slg.user.service;

import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.user.hepler.CastleHelper;
import com.h13.slg.user.vo.CastleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
@Service("CastleService")
public class CastleServiceImpl implements CastleService {

    @Autowired
    CastleHelper castleHelper;

    /**
     * 收获金币
     * <p/>
     * 计算上一次收获的时间戳和这次时间戳之间的增量，然后把金币加入到user信息中，并且更新收获时间戳
     * </P>
     *
     * @param request
     * @return
     */
    @Override
    public SlgData harvest(SlgRequestDTO request) {
        int uid = request.getUid();

        CastleVO castleVO = castleHelper.harvest(uid);
        return SlgData.getData().add("gold", castleVO.getGold())
                .add("timer", castleVO.getTimer());
    }
}
