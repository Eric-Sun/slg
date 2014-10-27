package com.h13.slg.skill.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-21
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public interface ZuLingService {

    public SlgData summon(SlgRequestDTO request) throws RequestFatalException;

    public SlgData leave(SlgRequestDTO request) throws RequestFatalException;

    public SlgData getSkill(SlgRequestDTO request) throws RequestFatalException;

}
