package com.h13.slg.core;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午7:48
 * To change this template use File | Settings | File Templates.
 */
public class SlgRequestDTO {
    private long uid;
    private int seq;
    private Map<String, String> args;
    private String mod;
    private String act;

    public SlgRequestDTO(String mod, String act, long uid, int seq, Map<String, String> args) {
        this.mod = mod;
        this.act = act;
        this.uid = uid;
        this.seq = seq;
        this.args = args;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
