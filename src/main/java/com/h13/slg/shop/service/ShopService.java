package com.h13.slg.shop.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-18
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public interface ShopService {


    public SlgData shopList(SlgRequestDTO request);

    public SlgData buy(SlgRequestDTO request) throws RequestErrorException;
}
