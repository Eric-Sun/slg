package com.h13.slg.task.vo;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-19
 * Time: 上午12:15
 * To change this template use File | Settings | File Templates.
 */
public class TaskMainVO {
    private long id;
    private Map<String,Integer> progress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, Integer> getProgress() {
        return progress;
    }

    public void setProgress(Map<String, Integer> progress) {
        this.progress = progress;
    }
}
