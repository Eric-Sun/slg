package com.h13.slg.pkg.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-19
 * Time: 下午6:19
 * To change this template use File | Settings | File Templates.
 */
public interface PackageService {

    public SlgData get(SlgRequestDTO request) throws RequestErrorException;
}
