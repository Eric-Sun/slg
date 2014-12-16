package com.h13.slg.fight;

import com.alibaba.fastjson.JSON;
import com.h13.slg.fight.log.FightLog;

import java.util.List;

/**
 * 战斗的返回对象
 */
public class FResponse {

    private String win;

    private List<List<FightLog>> logList;

    public List<List<FightLog>> getLogList() {
        return logList;
    }

    public void setLogList(List<List<FightLog>> logList) {
        this.logList = logList;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String printLog() {
        return JSON.toJSONString(logList);
    }
}
