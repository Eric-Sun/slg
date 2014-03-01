package com.h13.slg.task.co;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class UserTaskCO {

    private long id;
    private int taskId;
    private String progess;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Map<String, Integer> getProgess() {
        return JSON.parseObject(progess, Map.class);
    }

    public void setProgess(String progess) {
        this.progess = progess;
    }
}
