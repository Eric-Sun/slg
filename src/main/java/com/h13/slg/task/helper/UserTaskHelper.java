package com.h13.slg.task.helper;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.co.TaskCO;
import com.h13.slg.config.fetcher.TaskConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.event.helper.UserEventHelper;
import com.h13.slg.pkg.PackageConstants;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.task.cache.UserTaskCache;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.dao.UserTaskDAO;
import com.h13.slg.task.vo.FinishedPerTaskVO;
import com.h13.slg.task.vo.FinishedTaskVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserTaskHelper implements ApplicationContextAware {

    @Autowired
    UserTaskDAO userTaskDAO;
    @Autowired
    UserTaskCache userTaskCache;
    @Autowired
    TaskConfigFetcher taskConfigFetcher;
    @Autowired
    UserEventHelper userEventHelper;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    UserPackageHelper userPackageHelper;

    @Autowired
    UserEquipHelper userEquipHelper;
    private ApplicationContext applicationContext;

    /**
     * 创建一个新的用户任务对象
     *
     * @param uid
     */
    public void create(long uid) {
        userTaskDAO.insert(uid, 1, "{1:0,2:0}");
        UserTaskCO userTaskCO = new UserTaskCO();
        userTaskCO.setId(uid);
        userTaskCO.setProgess("{1:0,2:0}");
        userTaskCO.setTaskId(1);
        userTaskCache.set(userTaskCO);
        SlgLogger.info(SlgLoggerEntity.p("task","create",uid,"add a new task."));
    }


    public void updateProgress(UserTaskCO userTaskCO) {
        userTaskDAO.updateProgess(userTaskCO.getId(), userTaskCO.getTaskId(),
                JSON.toJSONString(userTaskCO.getProgess()));
        userTaskCache.set(userTaskCO);
    }

    public void setNewTask(UserTaskCO userTaskCO) {
        userTaskDAO.updateProgess(userTaskCO.getId(), userTaskCO.getTaskId(),
                JSON.toJSONString(userTaskCO.getProgess()));
        userTaskCache.set(userTaskCO);
    }

    /**
     * 获得当前用户的任务
     *
     * @param uid
     * @return
     */
    public UserTaskCO getTask(long uid) {
        UserTaskCO userTaskCO = userTaskCache.get(uid);
        if (userTaskCO == null)
            userTaskCO = userTaskDAO.get(uid);
        return userTaskCO;
    }


    /**
     * 处理这个人的任务事件
     *
     * @param evtList
     */
    public boolean handleEvents(long uid, List<UserEventCO> evtList, SlgData slgData, FinishedTaskVO ftVO) throws RequestErrorException {


        UserTaskCO userTaskCO = getTask(uid);
        TaskCO taskCO = taskConfigFetcher.get(userTaskCO.getId() + "");
        String serviceName = null;
        // 需要把是任务的信息
        UserSmallTaskCO t1 = new UserSmallTaskCO();
        t1.setOrder(1);
        t1.setTaskArgs(taskCO.getTaskArgs1());
        t1.setTaskTarget(taskCO.getTaskTarget1());
        t1.setTaskType(taskCO.getTaskType1());
        serviceName = t1.getTaskType() + "Service";
        EventHandler evtHandler = (EventHandler) applicationContext.getBean(serviceName);

        for (UserEventCO evt : evtList) {
            evtHandler.handleEvent(evt, t1);
        }

        UserSmallTaskCO t2 = new UserSmallTaskCO();
        t2.setOrder(2);
        t2.setTaskArgs(taskCO.getTaskArgs2());
        t2.setTaskTarget(taskCO.getTaskTarget2());
        t2.setTaskType(taskCO.getTaskType2());

        for (UserEventCO evt : evtList) {
            evtHandler.handleEvent(evt, t2);
        }
        // 多次完成任务的情况
        // 检查任务是否完成
        Map<String, Integer> data = userTaskCO.getProgessMap();
        boolean finished = false;
        if (data.get("1").toString().equals(taskCO.getTaskTarget1())
                &&
                data.get("2").toString().equals(taskCO.getTaskTarget2())) {
            finished = true;
        }

        if (finished == true) {

            List<FinishedPerTaskVO> perList = ftVO.getList();

            FinishedPerTaskVO perVO = new FinishedPerTaskVO();
            perVO.setId(new Integer(taskCO.getId()));
            perVO.setLevel(new Integer(taskCO.getLevel()));
            perVO.setXp(new Integer(taskCO.getXp()));
            perVO.setGold(new Integer(taskCO.getGold()));

            // 如果award存在的话，需要解析
            if (taskCO.getAward1() != null) {
                List<List<String>> awardList = new LinkedList<List<String>>();
                List<String> awardPerList = new LinkedList<String>();
                String awardInfo = taskCO.getAward1();
                if (awardInfo.contains("equip")) {
                    // 装备类的奖励
                    String awardCount = awardInfo.split(":")[1];
                    String tp1 = awardInfo.split(":")[0].split(".")[0];
                    String tp2 = awardInfo.split(":")[0].split(".")[1];
                    String eid = awardInfo.split(":")[0].split(".")[2];
                    awardPerList.add(tp1);
                    awardPerList.add(tp2);
                    awardPerList.add(eid);
                    awardPerList.add(awardCount);
                } else {
                    // 材料类奖励
                    awardPerList.add(taskCO.getAward1().split(".")[0]);
                    awardPerList.add(taskCO.getAward1().split(".")[1].split(".")[0]);
                    awardPerList.add(taskCO.getAward1().split(".")[1].split(".")[1]);
                    awardList.add(awardPerList);
                }
            }
            perList.add(perVO);
            ftVO.setList(perList);
            // handleAwards
            handleAwards(uid, perVO);
        }
        return finished;
    }

    private void handleAwards(long uid, FinishedPerTaskVO perVO) throws RequestErrorException {
        int gold = perVO.getGold();
        int xp = perVO.getXp();
        List<List<String>> awards = perVO.getAwards();
        userStatusHelper.addGold(uid, gold);
        userStatusHelper.addXp(uid, xp);

        for (List<String> perAward : awards) {
            if (perAward.size() == 3) {
                // 材料类奖励，给钱的
                String type = perAward.get(0);
                String id = perAward.get(1);
                int num = new Integer(perAward.get(2));
                userStatusHelper.addCash(uid, num);
            } else {
                // 给用户装备
                int eid = new Integer(perAward.get(2));
                String tp2 = perAward.get(1);
                int count = new Integer(perAward.get(3));
                for (int i = 0; i < count; i++) {
                    userEquipHelper.getANewUserEquip(uid, eid, tp2);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
