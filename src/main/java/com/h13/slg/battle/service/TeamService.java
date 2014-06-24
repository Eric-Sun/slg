package com.h13.slg.battle.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-24
 * Time: 下午11:21
 * To change this template use File | Settings | File Templates.
 */
public interface TeamService {

    public SlgData getTeam(SlgRequestDTO request) throws RequestErrorException;


    public SlgData getUserRoleList(SlgRequestDTO request) throws RequestErrorException;

    public SlgData updatePos(SlgRequestDTO request) throws RequestErrorException;

    public SlgData deletePos(SlgRequestDTO request) throws RequestErrorException;
}
