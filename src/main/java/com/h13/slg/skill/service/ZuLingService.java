package com.h13.slg.skill.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-21
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public interface ZuLingService {

    public SlgData summon(SlgRequestDTO request) throws RequestErrorException;


    public SlgData load(SlgRequestDTO request) throws RequestErrorException;

    public SlgData leave(SlgRequestDTO request) throws RequestErrorException;


    public SlgData getSkill(SlgRequestDTO request) throws RequestErrorException;

}
