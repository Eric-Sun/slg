package com.h13.slg.task.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-3
 * Time: 下午3:03
 * To change this template use File | Settings | File Templates.
 */
public class FinishedTaskVO {
    private List<FinishedPerTaskVO> list = new LinkedList<FinishedPerTaskVO>();

    public List<FinishedPerTaskVO> getList() {
        return list;
    }

    public void setList(List<FinishedPerTaskVO> list) {
        this.list = list;
    }
}
