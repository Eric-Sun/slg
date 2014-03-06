package com.h13.slg.core;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SlgResponseDTO {
    private String act;
    private Map<String, Object> args;
    private int code = Constants.ResponseStatus.SUCCESS;
    private Map<String, Object> data;
    private String desc;
    private String mod;
    private long serverTime = System.currentTimeMillis();

    public SlgResponseDTO(SlgRequestDTO request, Map<String, Object> data) {
        this.act = request.getAct();
        this.mod = request.getMod();
        this.args = request.getArgs();
        this.data = data;

    }

    public SlgResponseDTO(SlgRequestDTO request, int code, String desc) {
        this.act = request.getAct();
        this.mod = request.getMod();
        this.args = request.getArgs();
        this.code = code;
        this.desc = desc;
    }


    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
}

