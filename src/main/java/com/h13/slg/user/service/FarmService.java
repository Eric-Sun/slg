package com.h13.slg.user.service;

import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;

/**
 * 农场接口
 */
public interface FarmService {

    /**
     * 收获
     * @return
     */
    public SlgData harvest(SlgRequestDTO request);

}
