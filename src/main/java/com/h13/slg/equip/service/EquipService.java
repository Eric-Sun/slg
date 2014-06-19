package com.h13.slg.equip.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-24
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */
public interface EquipService {

    /**
     * {"code":0,"desc":"","mod":"equip","act":"strengthen",
     * "args":{"eid":13,"erid":6},"data":{"strength":2,"gold":-100},
     * "daily_task":"5","serverTime":1393318100}
     * <p/>
     * <p/>
     * erid 是roleId 48是刘乐乐，6是黄忠
     * eid 是这个用户装备对应的id
     * 刘乐乐 鹿皮开一
     * {"code":0,"desc":"","mod":"equip","act":"strengthen",
     * "args":{"eid":16,"erid":48},
     * "data":{"strength":2,"gold":-100},"daily_task":"5","serverTime":1393318430}
     * <p/>
     * 刘乐乐雌雄双剑
     * {"code":0,"desc":"","mod":"equip","act":"strengthen","args":{"eid":14,"erid":48},
     * "data":{"strength":3,"gold":-112},"daily_task":"5","serverTime":1393318473}
     * <p/>
     * 黄忠 鹿皮开一
     * {"code":0,"desc":"","mod":"equip","act":"strengthen","args":{"eid":18,"erid":6},
     * data":{"strength":2,"gold":-100},"daily_task":"5","serverTime":1393318526}
     * <p/>
     * <p/>
     * {"code":0,"desc":"","mod":"equip","act":"strengthen","args":
     * {"eid":19,"erid":236},"data":{"strength":2,"gold":-100},"daily_task":"5","serverTime":1393318742}
     * <p/>
     * <p/>
     * {"code":0,"desc":"","mod":"equip","act":"strengthen","args":{"eid":20,"erid":236},
     * "data":{"strength":2,"gold":-100},"daily_task":"5","serverTime":1393318776}
     *
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData strengthen(SlgRequestDTO request) throws RequestErrorException;


    /**
     * {"code":0,"desc":"","mod":"equip","act":"make","args":{"lucky":0,"erid":5,"eid":9},
     * "data":{"level":3,"material":{"13":-15,"28":-1}},"serverTime":1393253548}
     */
    public SlgData make(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 获取装备的列表，在穿戴之前显示列表
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData equipList(SlgRequestDTO request) throws RequestErrorException;


    public SlgData equip(SlgRequestDTO reqeust) throws RequestErrorException;
}
