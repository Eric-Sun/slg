package com.h13.slg.shop.service;

import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.exception.RequestUnexpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-18
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public interface ShopService {


    public SlgData buy(SlgRequestDTO request) throws RequestFatalException, RequestUnexpectedException;
}
