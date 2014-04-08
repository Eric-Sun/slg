package com.h13.slg.user.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-4-2
 * Time: 下午7:06
 * To change this template use File | Settings | File Templates.
 */
public class FarmForLoginVO {
    private long timer;

    public FarmForLoginVO(long timer) {
        this.timer = timer;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }
}
