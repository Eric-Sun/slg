package com.h13.slg.task.helper;

import com.h13.slg.config.co.TaskCO;
import com.h13.slg.config.fetcher.TaskConfigFetcher;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.cache.UserTaskCache;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.dao.UserTaskDAO;
import com.h13.slg.task.vo.FinishedPerTaskVO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
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
    UserStatusHelper userStatusHelper;
    @Autowired
    UserEquipHelper userEquipHelper;

    private ApplicationContext applicationContext;

    /**
     * 创建一个新的用户任务对象
     *
     * @param uid
     */
    public void create(int uid) {
        Map<String, Integer> newTaskObject = newTaskObject();
        userTaskDAO.insert(uid, 1, newTaskObject);
        UserTaskCO userTaskCO = new UserTaskCO();
        userTaskCO.setId(uid);
        userTaskCO.setProgress(newTaskObject);
        userTaskCO.setTaskId(1);
        userTaskCache.set(userTaskCO);
        SlgLogger.info(SlgLoggerEntity.p("task", "create", uid, "add a new task."));
    }

    private Map<String, Integer> newTaskObject() {
        return new HashMap<String, Integer>() {{
            put("1", 0);
            put("2", 0);
        }};
    }

    public void updateProgress(UserTaskCO userTaskCO) {
        userTaskDAO.updateProgess(userTaskCO.getId(), userTaskCO.getTaskId(),
                userTaskCO.getProgress());
        userTaskCache.set(userTaskCO);
    }

    public void setNewTask(long uid, int taskId) {
        UserTaskCO userTaskCO = new UserTaskCO();
        userTaskCO.setId(uid);
        userTaskCO.setTaskId(taskId);
        userTaskCO.setProgress(newTaskObject());
        userTaskDAO.updateTask(userTaskCO.getId(), userTaskCO.getTaskId(),
                newTaskObject());
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
    public boolean handleEvents(int uid, List<UserEventCO> evtList, SlgData slgData, List<FinishedPerTaskVO> finishedTaskList) throws RequestFatalException {


        UserTaskCO userTaskCO = getTask(uid);
        TaskCO taskCO = taskConfigFetcher.get(userTaskCO.getTaskId() + "");
        String serviceName = null;
        // 需要把是任务的信息
        UserSmallTaskCO t1 = new UserSmallTaskCO();
        t1.setOrder(1);
        t1.setTaskArgs(taskCO.getTaskArg1());
        t1.setTaskTarget(taskCO.getTaskTarget1());
        t1.setTaskType(taskCO.getTaskType1());
        serviceName = t1.getTaskType() + "Handler";
        EventHandler evtHandler = (EventHandler) applicationContext.getBean(serviceName);


        for (UserEventCO evt : evtList) {

            UserTaskHolder holder = new UserTaskHolder();
            holder.setSmallTask(t1);
            holder.setHandler(evtHandler);
            holder.doHandler(evt);
        }

        if (!taskCO.getTaskType2().equals("")) {
            UserSmallTaskCO t2 = new UserSmallTaskCO();
            t2.setOrder(2);
            t2.setTaskArgs(taskCO.getTaskArg2());
            t2.setTaskTarget(taskCO.getTaskTarget2());
            t2.setTaskType(taskCO.getTaskType2());

            serviceName = t2.getTaskType() + "Handler";
            evtHandler = (EventHandler) applicationContext.getBean(serviceName);

            for (UserEventCO evt : evtList) {
                UserTaskHolder holder = new UserTaskHolder();
                holder.setSmallTask(t2);
                holder.setHandler(evtHandler);
                holder.doHandler(evt);
            }
        }
        // 多次完成任务的情况
        // 检查任务是否完成
        userTaskCO = getTask(uid);
        Map<String, Integer> data = userTaskCO.getProgress();
        boolean finished = false;
        if (data.get("1").toString().equals(taskCO.getTaskTarget1()) && "".equals(taskCO.getTaskTarget2())) {
            // 第一个任务完成，没有第二个任务
            finished = true;
        } else if (data.get("1").toString().equals(taskCO.getTaskTarget1()) &&
                data.get("2").toString().equals(taskCO.getTaskTarget2())) {
            // 两个任务全部完成
            finished = true;
        }

        if (finished == true) {

            FinishedPerTaskVO perVO = new FinishedPerTaskVO();
            perVO.setId(new Integer(taskCO.getId()));
            perVO.setLevel(new Integer(taskCO.getLevel()));
            perVO.setXp(new Integer(taskCO.getXp()));
            perVO.setGold(new Integer(taskCO.getGold()));
            List<List<String>> awardList = new LinkedList<List<String>>();
            // 如果award存在的话，需要解析
            if (!taskCO.getAward1().equals("")) {

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
            perVO.setAwards(awardList);
            finishedTaskList.add(perVO);
            // handleAwards
            handleAwards(uid, perVO);

            // 任务设置为下一个任务
            int nextId = userTaskCO.getTaskId() + 1;
            setNewTask(uid, nextId);

            slgData.add("finished_task", finishedTaskList);
        }
        return finished;
    }

    private void handleAwards(int uid, FinishedPerTaskVO perVO) throws RequestFatalException {
        int gold = perVO.getGold();
        int xp = perVO.getXp();
        List<List<String>> awards = perVO.getAwards();
        userStatusHelper.addGold(uid, gold);
        userStatusHelper.addXp(uid, xp);
        if (awards.size() == 0)
            return;

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
                    userEquipHelper.addUserEquip(uid, tp2);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
