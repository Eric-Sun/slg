package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "taskList")
public class TaskXMLCO {

    private Map<String, TaskCO> map = new HashMap<String, TaskCO>();

    @SetNext
    public void addTask(TaskCO co) {
        map.put(co.getId() + "", co);
    }

    public Map<String, TaskCO> getMap() {
        return map;
    }

    public void setMap(Map<String, TaskCO> map) {
        this.map = map;
    }
}
