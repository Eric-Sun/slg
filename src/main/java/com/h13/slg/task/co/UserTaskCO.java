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

    private int id;
    private int taskId;
    private Map<String,Integer> progress;

    public Map<String, Integer> getProgress() {
        return progress;
    }

    public void setProgress(Map<String, Integer> progress) {
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
