package com.h13.slg.inventory.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-24
 * Time: 下午10:50
 * To change this template use File | Settings | File Templates.
 */
public interface InventoryService {


    /**
     * {"code":0,"desc":"","mod":"inventory","act":"buy","args":{"num":10,"id":41},
     * "data":{"cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]},"serverTime":1393253390}
     */
    public SlgData buy(SlgRequestDTO request) throws RequestErrorException;

}
