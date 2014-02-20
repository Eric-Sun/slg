package com.h13.slg.user.service;

import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.SlgResponseDTO;

/**
 * 主城接口
 */
public interface CastleService {

    /**
     * 收获
     *
     * @return
     */
    public SlgData harvest(SlgRequestDTO request);


}
