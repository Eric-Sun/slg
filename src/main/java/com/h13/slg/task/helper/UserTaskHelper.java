package com.h13.slg.task.helper;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.co.TaskCO;
import com.h13.slg.config.fetcher.TaskConfigFetcher;
import com.h13.slg.core.SlgData;
import com.h13.slg.event.EventHandler;
import com.h13.slg.event.co.UserEventCO;
import com.h13.slg.task.cache.UserTaskCache;
import com.h13.slg.task.co.UserSmallTaskCO;
import com.h13.slg.task.co.UserTaskCO;
import com.h13.slg.task.dao.UserTaskDAO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private ApplicationContext applicationContext;

    /**
     * 创建一个新的用户任务对象
     *
     * @param uid
     */
    public void create(long uid) {
        userTaskDAO.insert(uid, 1, "{}");
        UserTaskCO userTaskCO = new UserTaskCO();
        userTaskCO.setId(uid);
        userTaskCO.setProgess("{}");
        userTaskCO.setTaskId(1);
        userTaskCache.set(userTaskCO);
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
    public void handleEvents(long uid, List<UserEventCO> evtList, SlgData slgData) {


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
            evtHandler.handleEvent(evt, t1, slgData);
        }

        UserSmallTaskCO t2 = new UserSmallTaskCO();
        t2.setOrder(2);
        t2.setTaskArgs(taskCO.getTaskArgs2());
        t2.setTaskTarget(taskCO.getTaskTarget2());
        t2.setTaskType(taskCO.getTaskType2());

        for (UserEventCO evt : evtList) {
            evtHandler.handleEvent(evt, t2, slgData);
        }


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
