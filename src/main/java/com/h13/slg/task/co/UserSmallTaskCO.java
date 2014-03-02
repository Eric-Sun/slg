package com.h13.slg.task.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-2
 * Time: 上午2:06
 * To change this template use File | Settings | File Templates.
 */
public class UserSmallTaskCO {
    private int order;
    private String taskType;
    private String taskArgs;
    private String taskTarget;


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskArgs() {
        return taskArgs;
    }

    public void setTaskArgs(String taskArgs) {
        this.taskArgs = taskArgs;
    }

    public String getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(String taskTarget) {
        this.taskTarget = taskTarget;
    }
}
