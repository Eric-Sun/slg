package com.h13.slg.tavern.service;

import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-11
 * Time: 上午12:07
 * To change this template use File | Settings | File Templates.
 */
public interface TavernService {

    /**
     * {"code":0,"desc":"","mod":"tavern",
     * "act":"leave","args":{},"data":{"soul":28},"serverTime":1394437290}
     *
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData leave(SlgRequestDTO request) throws RequestErrorException;


    /**
     * {"code":0,"desc":"","mod":"tavern","act":"invite","args":{"all":1,"cash":0},
     * "data":{"process":[[-9000,4,237],[-12000,1,245],[-1500,2,264],[-4000,1,203],
     * [-1500,2,258],[-4000,3,181],[-9000,1,248],[-1500,1,232],[-1500,1,252],
     * -1500,2,192]]},"serverTime":1394451424}
     *
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData invite(SlgRequestDTO request) throws RequestErrorException;


    /**
     * {"code":0,"desc":"","mod":"tavern","act":"enroll","args":{"pos":3},
     * "data":{"role":{"id":228,"xp":0,"level":1,"soldier_level":1,"grouth":30,
     * "attack":0,"defence":0,"mdefence":0,"weapon":0,"armor":0,"accessory":0,
     * "skill":1,"skill_levels":{"1":1},"soul":0,"fight_force":56,"god":0,"soul_break":0,"grouth_break":0}},"serverTime":1394437348}
     *
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData enroll(SlgRequestDTO request) throws RequestErrorException;


    /**
     * 获取上次的酒馆内容
     * @param request
     * @return
     * @throws RequestErrorException
     */
    public SlgData get(SlgRequestDTO request) throws RequestErrorException;
}
