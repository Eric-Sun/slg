package com.h13.slg.role.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

public interface SkillService {


    /**
     * 升级
     * @param slgRequestDTO
     * @return
     */
    public SlgData upgrade(SlgRequestDTO slgRequestDTO) throws RequestErrorException;


}
