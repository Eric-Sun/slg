package com.h13.slg.skill.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.skill.vo.RoleSkillVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
public interface SkillService {


    public SlgData setSkill(SlgRequestDTO requestDTO) throws RequestErrorException;

    public SlgData upgrade(SlgRequestDTO requestDTO) throws RequestErrorException;

}
