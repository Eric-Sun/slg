package com.h13.slg.battle.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-24
 * Time: 下午11:21
 * To change this template use File | Settings | File Templates.
 */
public interface TeamService {

    public SlgData getTeam(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;


    public SlgData getUserRoleList(SlgRequestDTO request) throws RequestFatalException;

    public SlgData updatePos(SlgRequestDTO request) throws RequestFatalException;

    public SlgData deletePos(SlgRequestDTO request) throws RequestFatalException;


    public SlgData updateLeader(SlgRequestDTO request) throws RequestFatalException;


}
