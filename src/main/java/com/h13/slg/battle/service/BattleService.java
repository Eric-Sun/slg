package com.h13.slg.battle.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:22
 * To change this template use File | Settings | File Templates.
 */
public interface BattleService {

    public SlgData saveTeam(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

    public SlgData pve(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

    public SlgData pveTeam(SlgRequestDTO request) throws RequestFatalException;

}
