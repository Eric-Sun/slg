package com.h13.slg.task;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-19
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public interface TaskHandler {
    /**
     * 计算这个任务完成的结果
     * @param args
     * @return
     */
    public String handle(String args);
}
