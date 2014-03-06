package com.h13.slg.battle.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:22
 * To change this template use File | Settings | File Templates.
 */
public interface BattleService {

    public SlgData saveTeam(SlgRequestDTO requestDTO) throws RequestErrorException;
}
