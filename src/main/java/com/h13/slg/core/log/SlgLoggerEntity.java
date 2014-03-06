package com.h13.slg.core.log;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志的主体
 */
public class SlgLoggerEntity {

    public static String REQUEST_LOG = "request";
    public static String PROGRAM_LOG = "program";
    private String type;
    private String mod;
    private String act;
    private String content;
    private long uid;
    private long t;
    private Map<String, Object> params = new HashMap<String, Object>();

    public static SlgLoggerEntity p(String mod, String act, long uid, String content) {
        return new SlgLoggerEntity(PROGRAM_LOG, mod, act, uid, content);
    }

    public static SlgLoggerEntity r(String mod, String act, long uid, String content) {
        return new SlgLoggerEntity(REQUEST_LOG, mod, act, uid, content);
    }

    private SlgLoggerEntity(String type, String mod, String act, long uid, String content) {
        this.type = type;
        this.mod = mod;
        this.act = act;
        this.uid = uid;
        this.content = content;
    }

    public static String getREQUEST_LOG() {
        return REQUEST_LOG;
    }

    public static void setREQUEST_LOG(String REQUEST_LOG) {
        SlgLoggerEntity.REQUEST_LOG = REQUEST_LOG;
    }

    public static String getPROGRAM_LOG() {
        return PROGRAM_LOG;
    }

    public static void setPROGRAM_LOG(String PROGRAM_LOG) {
        SlgLoggerEntity.PROGRAM_LOG = PROGRAM_LOG;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SlgLoggerEntity addParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
