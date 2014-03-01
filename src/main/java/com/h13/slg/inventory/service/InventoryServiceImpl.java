package com.h13.slg.inventory.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.role.helper.InventoryHelper;
import com.h13.slg.role.vo.InventoryBuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
@Service("InventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryHelper inventoryHelper;

    @Override
    public SlgData buy(SlgRequestDTO request) throws RequestErrorException {
        // "num":10,"id":41
        long uid = request.getUid();
        int num = new Integer(request.getArgs().get("num"));
        int id = new Integer(request.getArgs().get("id"));

        InventoryBuyVO inventoryBuyVO = inventoryHelper.buy(uid, num, id);
        //{"cost_type":"honor","cost_num":-1000,"awards":[["material",13,10]]}
        return SlgData.getData().add("cost_type", inventoryBuyVO.getCostType())
                .add("cost_num", inventoryBuyVO.getCostNum())
                .add("adwards", inventoryBuyVO.getAwards());
    }
}
