package com.h13.slg.skill.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
public interface SkillService {


    public SlgData setSkill(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

    public SlgData upgrade(SlgRequestDTO requestDTO) throws RequestFatalException, RequestUnexpectedException;

}
