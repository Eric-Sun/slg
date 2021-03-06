package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-18
 * Time: 下午6:13
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "taskList/task")
public class TaskCO {
    @BeanPropertySetter(pattern = "taskList/task/id")
    private int id;
    @BeanPropertySetter(pattern = "taskList/task/title")
    private String title;
    @BeanPropertySetter(pattern = "taskList/task/taskType1")
    private String taskType1;
    @BeanPropertySetter(pattern = "taskList/task/level")
    private String level;
    @BeanPropertySetter(pattern = "taskList/task/taskArg1")
    private String taskArg1;
    @BeanPropertySetter(pattern = "taskList/task/taskTarget1")
    private String taskTarget1;
    @BeanPropertySetter(pattern = "taskList/task/taskType2")
    private String taskType2;
    @BeanPropertySetter(pattern = "taskList/task/taskArg2")
    private String taskArg2;
    @BeanPropertySetter(pattern = "taskList/task/taskTarget2")
    private String taskTarget2;
    @BeanPropertySetter(pattern = "taskList/task/taskName1")
    private String taskName1;
    @BeanPropertySetter(pattern = "taskList/task/taskName2")
    private String taskName2;
    @BeanPropertySetter(pattern = "taskList/task/xp")
    private int xp;
    @BeanPropertySetter(pattern = "taskList/task/gold")
    private int gold;
    @BeanPropertySetter(pattern = "taskList/task/award1")
    private String award1;
    @BeanPropertySetter(pattern = "taskList/task/desc")
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskType1() {
        return taskType1;
    }

    public void setTaskType1(String taskType1) {
        this.taskType1 = taskType1;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTaskArg1() {
        return taskArg1;
    }

    public void setTaskArg1(String taskArg1) {
        this.taskArg1 = taskArg1;
    }

    public String getTaskTarget1() {
        return taskTarget1;
    }

    public void setTaskTarget1(String taskTarget1) {
        this.taskTarget1 = taskTarget1;
    }

    public String getTaskType2() {
        return taskType2;
    }

    public void setTaskType2(String taskType2) {
        this.taskType2 = taskType2;
    }

    public String getTaskArg2() {
        return taskArg2;
    }

    public void setTaskArg2(String taskArg2) {
        this.taskArg2 = taskArg2;
    }

    public String getTaskTarget2() {
        return taskTarget2;
    }

    public void setTaskTarget2(String taskTarget2) {
        this.taskTarget2 = taskTarget2;
    }

    public String getTaskName1() {
        return taskName1;
    }

    public void setTaskName1(String taskName1) {
        this.taskName1 = taskName1;
    }

    public String getTaskName2() {
        return taskName2;
    }

    public void setTaskName2(String taskName2) {
        this.taskName2 = taskName2;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getAward1() {
        return award1;
    }

    public void setAward1(String award1) {
        this.award1 = award1;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
